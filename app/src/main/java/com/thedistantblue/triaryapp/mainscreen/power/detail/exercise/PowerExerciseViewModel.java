package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.Training;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PowerExerciseViewModel extends ViewModel {

    private final Training training;
    private final ExerciseDao exerciseDao;

    public final ObservableField<String> powerExerciseName = new ObservableField<>();

    public void createExercise() {
        Exercise exercise = new Exercise();
    }
}