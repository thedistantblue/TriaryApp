package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.entities.Exercise;
import com.thedistantblue.triaryapp.entities.Set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ExerciseViewModel extends BaseObservable {
    private Exercise exercise;
    private DAO dao;
    private String actionString;

    public void setEmptyExerciseSets() {
        exercise.setExerciseSets(new ArrayList<Set>(5));
        for (int i = 0; i < 5; i++) {
            exercise.getExerciseSets().add(new Set(exercise.getId()));
        }
    }

    public void setActionString(String actionString) {
        this.actionString = actionString;
    }

    public String getActionString() {
        return actionString;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public void setExerciseName(String name) {
        exercise.setExerciseName(name);
        notifyChange();
    }

    @Bindable
    public String getExerciseName() {
        return exercise.getExerciseName();
    }

    public void setExerciseComments(String comments) {
        exercise.setExerciseComments(comments);
        notifyChange();
    }

    @Bindable
    public String getExerciseComments() {
        return exercise.getExerciseComments();
    }

    //1 set
    public void setFirstSetNumber(String number) {
        exercise.getExerciseSets().get(1).setSetNumber(number);
        notifyChange();
    }

    @Bindable
    public String getFirstSetNumber() {
        return exercise.getExerciseSets().get(1).getSetNumber();
    }

    //2 set
    public void setSecondSetNumber(String number) {
        exercise.getExerciseSets().get(2).setSetNumber(number);
        notifyChange();
    }

    @Bindable
    public String getSecondSetNumber() {
        return exercise.getExerciseSets().get(2).getSetNumber();
    }

    //3 set
    public void setThirdSetNumber(String number) {
        exercise.getExerciseSets().get(3).setSetNumber(number);
        notifyChange();
    }

    @Bindable
    public String getThirdSetNumber() {
        return exercise.getExerciseSets().get(3).getSetNumber();
    }

    //4 set
    public void setFourthSetNumber(String number) {
        exercise.getExerciseSets().get(4).setSetNumber(number);
        notifyChange();
    }

    @Bindable
    public String getFourthSetNumber() {
        return exercise.getExerciseSets().get(4).getSetNumber();
    }

    //5 set
    public void setFifthSetNumber(String number) {
        exercise.getExerciseSets().get(5).setSetNumber(number);
        notifyChange();
    }

    @Bindable
    public String getFifthSetNumber() {
        return exercise.getExerciseSets().get(5).getSetNumber();
    }

    public void action() {
        if (actionString.equals("create")) {save();}
        else {update();}
    }

    private void save() {
        //
    }

    private void update() {
        //
    }
}
