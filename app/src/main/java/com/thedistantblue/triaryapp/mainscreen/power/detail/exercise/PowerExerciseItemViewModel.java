package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails;

public class PowerExerciseItemViewModel extends ViewModel {
    public final ObservableField<String> exerciseName = new ObservableField<>();

    public PowerExerciseItemViewModel(ExerciseDetails exercise) {
        exerciseName.set(exercise.getExercise().getName());
    }
}