package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.thedistantblue.triaryapp.entities.base.Exercise;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PowerExerciseViewModel extends ViewModel {

    private final PowerExerciseFragment powerExerciseFragment;
    public final ObservableField<String> powerExerciseName = new ObservableField<>();

    public void createExercise() {
        Exercise.Builder exerciseBuilder = new Exercise.Builder().setName(powerExerciseName.get());
        powerExerciseFragment.createExercise(exerciseBuilder);
    }

}