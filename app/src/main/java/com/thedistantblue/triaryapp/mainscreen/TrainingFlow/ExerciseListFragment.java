package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.databinding.ExerciseItemCardBinding;
import com.thedistantblue.triaryapp.databinding.ExerciseListFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.Dates;
import com.thedistantblue.triaryapp.entities.Exercise;
import com.thedistantblue.triaryapp.mainscreen.ItemTouchHelperAdapter;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.mainscreen.SimpleItemTouchHelperCallback;
import com.thedistantblue.triaryapp.utils.ActionEnum;
import com.thedistantblue.triaryapp.viewmodels.ExerciseViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExerciseListFragment extends Fragment {

    private static final String DATES_KEY = "dates";

    private DAO dao;
    private Dates dates;
    private List<Exercise> exerciseList;
    private ExerciseListFragmentLayoutBinding binding;

    public static ExerciseListFragment newInstance(Dates dates) {
        Bundle args = new Bundle();
        args.putSerializable(DATES_KEY, dates);

        ExerciseListFragment fragment = new ExerciseListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        init();

        binding =
                DataBindingUtil.inflate(inflater, R.layout.exercise_list_fragment_layout, parent, false);

        binding.exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.exerciseRecyclerView.setAdapter(new ExerciseAdapter(exerciseList, getActivity()));

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback((ExerciseAdapter) binding.exerciseRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.exerciseRecyclerView);

        binding.exerciseRecyclerView.getAdapter().notifyDataSetChanged();
        binding.exerciseAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreenActivityCallback)getActivity())
                        .manageFragments(ExerciseFragment.newInstance(dates, null, ActionEnum.CREATE), R.string.create_exercise_fragment_name);
            }
        });

        return binding.getRoot();
    }

    private void init() {
        dao = DAO.get(getActivity());
        dates = (Dates) getArguments().getSerializable(DATES_KEY);
        try {
            exerciseList = dao.getExercisesList(dates);
        } catch (NullPointerException exc) {
            exerciseList = new ArrayList<>();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainScreenActivityCallback) getActivity()).setTitle(R.string.training_exercises_fragment_name);
        ((ExerciseAdapter)binding.exerciseRecyclerView.getAdapter()).setExerciseList(dao.getExercisesList(dates));
    }

    private class ExerciseHolder extends RecyclerView.ViewHolder {
        private ExerciseItemCardBinding exerciseItemCardBinding;

        public ExerciseHolder(ExerciseItemCardBinding exerciseItemCardBinding) {
            super(exerciseItemCardBinding.getRoot());
            this.exerciseItemCardBinding = exerciseItemCardBinding;
            this.exerciseItemCardBinding.setViewModel(new ExerciseViewModel());
        }

        public void bind(final Exercise exercise) {

            this.exerciseItemCardBinding.getViewModel().setExercise(exercise);
            this.exerciseItemCardBinding.executePendingBindings();
            exerciseItemCardBinding.getViewModel().setExerciseSets(exercise.getExerciseSets());


            this.exerciseItemCardBinding.exerciseCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainScreenActivityCallback)getActivity())
                            .manageFragments(ExerciseFragment.newInstance(dates, exercise, ActionEnum.UPDATE), R.string.update_exercise_fragment_name);
                }
            });
        }
    }

    private class ExerciseAdapter extends RecyclerView.Adapter<ExerciseHolder>
    implements ItemTouchHelperAdapter {
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
            dao.deleteExercise(exerciseList.get(position));
            exerciseList.remove(position);
            //dao.deleteTraining(trainingList.get(position));
            notifyItemRemoved(position);
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
