package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise;

import static com.thedistantblue.triaryapp.utils.BundleKeyConstants.TRAINING_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.TrainingDetailExerciseLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.mainscreen.AutoDisposableFragment;
import com.thedistantblue.triaryapp.mainscreen.power.detail.PowerTrainingDetailActivity;

import java.util.UUID;

public class PowerExerciseFragment extends AutoDisposableFragment {

    private Training training;
    private ExerciseDao exerciseDao;
    private PowerTrainingDetailActivity powerTrainingDetailActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        training = (Training) requireArguments().getSerializable(TRAINING_KEY);
        exerciseDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).exerciseDao();
        powerTrainingDetailActivity = (PowerTrainingDetailActivity) requireActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TrainingDetailExerciseLayoutBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.training_detail_exercise_layout, container, false);
        PowerExerciseViewModel powerExerciseViewModel = new PowerExerciseViewModel(this);
        binding.setViewModel(powerExerciseViewModel);
        binding.notifyChange();
        return binding.getRoot();
    }

    public void createExercise(Exercise.Builder exerciseBuilder) {
        Exercise exercise = exerciseBuilder.setUuid(UUID.randomUUID())
                                           .setParentUuid(training.getUuid())
                                           .build();
        withAutoDispose(exerciseDao.create(exercise)
                                   .subscribe(() -> powerTrainingDetailActivity.onBackPressed()));
    }

}