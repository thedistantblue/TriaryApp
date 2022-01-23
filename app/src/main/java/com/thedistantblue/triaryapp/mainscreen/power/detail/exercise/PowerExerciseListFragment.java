package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise;

import static com.thedistantblue.triaryapp.utils.BundleKeyConstants.TRAINING_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.TrainingDetailExerciseListItemLayoutBinding;
import com.thedistantblue.triaryapp.databinding.TrainingDetailExerciseListLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails;
import com.thedistantblue.triaryapp.entities.composite.details.TrainingDetails;
import com.thedistantblue.triaryapp.mainscreen.AutoDisposableFragment;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.ListItemAdapter;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.ListItemHolder;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.touch.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PowerExerciseListFragment extends AutoDisposableFragment {

    private final Collection<ExerciseDetails> exerciseDetails = new ArrayList<>();

    private ExerciseDao exerciseDao;
    private TrainingDetails training;
    private ExerciseDetailsDao exerciseDetailsDao;
    private PowerExerciseListAdapter powerExerciseListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        training = (TrainingDetails) requireArguments().getSerializable(TRAINING_KEY);
        exerciseDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).exerciseDao();
        exerciseDetailsDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).exerciseDetailsDao();
        init();
    }

    private void init() {
        Collection<String> exercisesIds = training.getExerciseList().stream()
                                                .map(exercise -> exercise.getExerciseId().toString())
                                                .collect(Collectors.toSet());
        withAutoDispose(exerciseDetailsDao.findAllById(exercisesIds)
                                          .subscribe(details -> {
                                              exerciseDetails.clear();
                                              exerciseDetails.addAll(details);
                                          }));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TrainingDetailExerciseListLayoutBinding binding = DataBindingUtil.inflate(inflater,
                                                                                  R.layout.training_detail_exercise_list_layout,
                                                                                  container,
                                                                                  false);

        powerExerciseListAdapter = new PowerExerciseListAdapter(exerciseDao, this, training.getExerciseList());
        // Надо ли?
        powerExerciseListAdapter.notifyDataSetChanged();

        binding.trainingDetailExerciseListRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.trainingDetailExerciseListRv.setAdapter(powerExerciseListAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(powerExerciseListAdapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.trainingDetailExerciseListRv);

        return binding.getRoot();
    }

    private class PowerExerciseListAdapter extends ListItemAdapter<Exercise, PowerExerciseHolder, ExerciseDao> {

        public PowerExerciseListAdapter(ExerciseDao repository, AutoDisposableFragment fragment, List<Exercise> objectsList) {
            super(repository, fragment, objectsList);
        }

        @NonNull
        @Override
        public PowerExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }
    }

    private class PowerExerciseHolder extends ListItemHolder<Exercise, TrainingDetailExerciseListItemLayoutBinding> {
        private final TrainingDetailExerciseListItemLayoutBinding binding;

        public PowerExerciseHolder(@NonNull TrainingDetailExerciseListItemLayoutBinding view) {
            super(view);
            this.binding = view;
            this.binding.setViewModel(new PowerExerciseItemViewModel(null));
        }

        @Override
        public void bind(@NonNull Exercise object) {

        }
    }


}