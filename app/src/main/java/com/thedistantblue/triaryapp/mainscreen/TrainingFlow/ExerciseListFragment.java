package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.DatesWithExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.ExerciseItemCardBinding;
import com.thedistantblue.triaryapp.databinding.ExerciseListFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;
import com.thedistantblue.triaryapp.mainscreen.ItemTouchHelperAdapter;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.mainscreen.SimpleItemTouchHelperCallback;
import com.thedistantblue.triaryapp.mainscreen.TitledFragment;
import com.thedistantblue.triaryapp.viewmodels.ExerciseCardViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import lombok.Data;

public class ExerciseListFragment extends TitledFragment {

    private static class CallbackThread implements Runnable {

        private final CountDownLatch latch;
        private final ExerciseListFragment exerciseListFragment;
        private final Activity activity;

        public CallbackThread(CountDownLatch latch,
                              ExerciseListFragment exerciseListFragment,
                              Activity activity) {
            this.latch = latch;
            this.exerciseListFragment = exerciseListFragment;
            this.activity = activity;
            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            try {
                latch.await();
                activity.runOnUiThread(exerciseListFragment::showExerciseFragment);
            } catch (Exception ex) {
                Log.e("", ex.toString());
            }
        }
    }

    @Data
    private static class ExerciseExerciseSetListPair {
        private final Exercise exercise;
        private final List<ExerciseSet> exerciseSets;
    }

    private static final String DATES_KEY = "dates";

    private volatile boolean isWaitingDone;

    private ExerciseAdapter exerciseAdapter;
    private DatesWithExerciseDao datesWithExerciseDao;
    private ExerciseDao exerciseDao;
    private ExerciseSetDao exerciseSetDao;
    private ExerciseWithExerciseSetDao exerciseWithExerciseSetDao;
    private Dates dates;
    private List<Exercise> exerciseList = new ArrayList<>();
    private ExerciseListFragmentLayoutBinding binding;

    private Exercise newExercise;
    private final ArrayList<ExerciseSet> newExerciseSets = new ArrayList<>();

    public static ExerciseListFragment newInstance(Dates dates) {
        Bundle args = new Bundle();
        args.putSerializable(DATES_KEY, dates);

        ExerciseListFragment fragment = new ExerciseListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getTitle() {
        return R.string.exercises_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        init();
        binding = DataBindingUtil.inflate(inflater, R.layout.exercise_list_fragment_layout, parent, false);

        exerciseAdapter = new ExerciseAdapter(exerciseList, getActivity());
        binding.exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.exerciseRecyclerView.setAdapter(exerciseAdapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback((ExerciseAdapter) binding.exerciseRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.exerciseRecyclerView);

        binding.exerciseRecyclerView.getAdapter().notifyDataSetChanged();
        binding.exerciseAddButton.setOnClickListener(view -> {
            createNewExerciseWithSets();
        });

        return binding.getRoot();
    }

    private void createNewExerciseWithSets() {
        newExercise = new Exercise(dates.getDatesUUID());
        newExerciseSets.clear();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        new CallbackThread(countDownLatch, this, getActivity());

        exerciseDao.create(newExercise).subscribe(() -> {
            for (int i = 1; i <= 5; i++) {
                ExerciseSet exerciseSet = new ExerciseSet(newExercise.getExerciseUUID());
                exerciseSet.setNumber(i);
                exerciseSetDao.create(exerciseSet).subscribe(() -> {
                    newExerciseSets.add(exerciseSet);
                    countDownLatch.countDown();
                });
            }
        });
    }

    private void showExerciseFragment() {
        ((MainScreenActivityCallback)getActivity())
                .switchFragment(ExerciseFragment.newInstance(newExercise, newExerciseSets));
    }

    private void init() {
        initDaos();
        dates = (Dates) getArguments().getSerializable(DATES_KEY);
        datesWithExerciseDao.findById(dates.getDatesUUID().toString())
                            .subscribe(datesWithExercise -> {
                                exerciseList = datesWithExercise.getExerciseList();
                                exerciseAdapter.setExerciseList(exerciseList);
                                exerciseAdapter.notifyDataSetChanged();
                            });
    }

    private void initDaos() {
        exerciseDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                          .exerciseDao();
        exerciseSetDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                             .exerciseSetDao();
        datesWithExerciseDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                                   .datesWithExerciseDao();
        exerciseWithExerciseSetDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                                         .exerciseWithExerciseSetDao();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainScreenActivityCallback) getActivity()).setTitle(R.string.training_exercises_fragment_name);
        datesWithExerciseDao.findById(dates.getDatesUUID().toString())
                            .subscribe(datesWithExercise -> {
                                exerciseList = datesWithExercise.getExerciseList();
                                exerciseAdapter.setExerciseList(exerciseList);
                                exerciseAdapter.notifyDataSetChanged();
                            });
    }

    private class ExerciseHolder extends RecyclerView.ViewHolder {
        private ExerciseItemCardBinding exerciseItemCardBinding;

        public ExerciseHolder(ExerciseItemCardBinding exerciseItemCardBinding) {
            super(exerciseItemCardBinding.getRoot());
            this.exerciseItemCardBinding = exerciseItemCardBinding;
            this.exerciseItemCardBinding.setViewModel(new ExerciseCardViewModel());
        }

        public void bind(final Exercise exercise) {
            exerciseWithExerciseSetDao.findById(exercise.getExerciseUUID().toString())
                                      .subscribe(exerciseWithExerciseSet -> {
                                          this.exerciseItemCardBinding.executePendingBindings();
                                          this.exerciseItemCardBinding.getViewModel().exerciseName.set(exerciseWithExerciseSet.getExercise().getExerciseName());
                                          this.exerciseItemCardBinding.exerciseCard.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  ((MainScreenActivityCallback)getActivity())
                                                          .switchFragment(ExerciseFragment.newInstance(exerciseWithExerciseSet.getExercise(), new ArrayList<>(exerciseWithExerciseSet.getExerciseSetList())));
                                              }
                                          });
                                      });
        }
    }

    private class ExerciseAdapter extends RecyclerView.Adapter<ExerciseHolder> implements ItemTouchHelperAdapter {
        List<Exercise> exerciseList;
        Context context;

        public ExerciseAdapter(List<Exercise> exerciseList, Context context) {
            this.exerciseList = exerciseList;
            this.context = context;
        }

        public Context getContext() {
            return this.context;
        }

        public void setExerciseList(List<Exercise> exerciseList) {
            this.exerciseList = exerciseList;
        }

        @Override
        public ExerciseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(getActivity());

             ExerciseItemCardBinding exerciseItemCardBinding =
                    DataBindingUtil.inflate(inflate, R.layout.exercise_item_card, parent, false);

            return new ExerciseHolder(exerciseItemCardBinding);
        }

        @Override
        public void onBindViewHolder(ExerciseHolder exerciseHolder, int position) {
            Exercise exercise = exerciseList.get(position);
            //Log.d("exercise id in adapter", exercise.getId().toString());
            exerciseHolder.bind(exercise);
        }

        @Override
        public int getItemCount() {
            return exerciseList.size();
        }

        @Override
        public void onItemDismiss(int position) {
            exerciseDao.delete(exerciseList.get(position))
                       .subscribe(() -> {
                           exerciseList.remove(position);
                           notifyItemRemoved(position);
                       });
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(exerciseList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(exerciseList, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onRefresh(int position) {
            this.notifyItemChanged(position);
        }
    }
}
