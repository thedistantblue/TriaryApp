package com.thedistantblue.triaryapp.viewmodels;

import android.util.Log;

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
    String weight1 = "";
    String weight2 = "";
    String weight3 = "";
    String weight4 = "";
    String weight5 = "";
    private List<Set> setList;

    public void setExerciseSets(List<Set> setList) {
        this.setList = setList;
        weight1 = String.valueOf(this.setList.get(0).getSetWeight());
        weight2 = String.valueOf(this.setList.get(1).getSetWeight());
        weight3 = String.valueOf(this.setList.get(2).getSetWeight());
        weight4 = String.valueOf(this.setList.get(3).getSetWeight());
        weight5 = String.valueOf(this.setList.get(4).getSetWeight());
        for (int i = 0; i < this.setList.size(); i++) {
            System.out.println(weight5);
        }
    }

    public void setEmptyExerciseSets() {
        exercise.setExerciseSets(new ArrayList<Set>());
        setList = exercise.getExerciseSets();
        for (int i = 0; i < 5; i++) {
            Set set = new Set(exercise.getId());
            set.setSetNumber(i);
            setList.add(set);
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
    public void setFirstSetReps(String number) {
        if (!number.equals("")) {
            exercise.getExerciseSets().get(0).setSetRepetitions(Integer.parseInt(number));
        }
        else
            exercise.getExerciseSets().get(0).setSetRepetitions(0);
        notifyChange();
    }

    @Bindable
    public String getFirstSetReps() {
        if (String.valueOf(exercise.getExerciseSets().get(0).getSetRepetitions()).equals("0")) return "";
        return String.valueOf(exercise.getExerciseSets().get(0).getSetRepetitions());
    }

    //2 set

    public void setSecondSetReps(String number) {
        if (!number.equals(""))
            exercise.getExerciseSets().get(1).setSetRepetitions(Integer.parseInt(number));
        else
            exercise.getExerciseSets().get(1).setSetRepetitions(0);
        notifyChange();
    }

    @Bindable
    public String getSecondSetReps() {
        if (String.valueOf(exercise.getExerciseSets().get(1).getSetRepetitions()).equals("0")) return "";
        return String.valueOf(exercise.getExerciseSets().get(1).getSetRepetitions());
    }

    //3 set
    public void setThirdSetReps(String number) {
        if (!number.equals(""))
            exercise.getExerciseSets().get(2).setSetRepetitions(Integer.parseInt(number));
        else
            exercise.getExerciseSets().get(2).setSetRepetitions(0);
        notifyChange();
    }

    @Bindable
    public String getThirdSetReps() {
        if (String.valueOf(exercise.getExerciseSets().get(2).getSetRepetitions()).equals("0")) return "";
        return String.valueOf(exercise.getExerciseSets().get(2).getSetRepetitions());
    }

    //4 set
    public void setFourthSetReps(String number) {
        if (!number.equals(""))
            exercise.getExerciseSets().get(3).setSetRepetitions(Integer.parseInt(number));
        else
            exercise.getExerciseSets().get(3).setSetRepetitions(0);
        notifyChange();
    }

    @Bindable
    public String getFourthSetReps() {
        if (String.valueOf(exercise.getExerciseSets().get(3).getSetRepetitions()).equals("0")) return "";
        return String.valueOf(exercise.getExerciseSets().get(3).getSetRepetitions());
    }

    //5 set
    public void setFifthSetReps(String number) {
        if (!number.equals(""))
            exercise.getExerciseSets().get(4).setSetRepetitions(Integer.parseInt(number));
        else
            exercise.getExerciseSets().get(4).setSetRepetitions(0);
        notifyChange();
    }

    @Bindable
    public String getFifthSetReps() {
        if (String.valueOf(exercise.getExerciseSets().get(4).getSetRepetitions()).equals("0")) return "";
        return String.valueOf(exercise.getExerciseSets().get(4).getSetRepetitions());
    }

    //----------------------------------------------------------------------------------------------

    //1 set
    public void setFirstSetWeight(String number) {
        this.weight1 = number;
        if (!this.weight1.equals(""))
            exercise.getExerciseSets().get(0).setSetWeight(Double.parseDouble(weight1));
        notifyChange();
    }

    @Bindable
    public String getFirstSetWeight() {
        if (!weight1.equals("0.0"))return weight1;
        else return "";
    }

    //2 set

    public void setSecondSetWeight(String number) {
        this.weight2 = number;
        if (!this.weight2.equals(""))
            exercise.getExerciseSets().get(1).setSetWeight(Double.parseDouble(weight2));
        notifyChange();
    }

    @Bindable
    public String getSecondSetWeight() {
        if (!weight2.equals("0.0"))return weight2;
        else return "";
    }

    //3 set
    public void setThirdSetWeight(String number) {
        this.weight3 = number;
        if (!this.weight3.equals(""))
            exercise.getExerciseSets().get(2).setSetWeight(Double.parseDouble(weight3));
        notifyChange();
    }

    @Bindable
    public String getThirdSetWeight() {
        if (!weight3.equals("0.0"))return weight3;
        else return "";
    }

    //4 set
    public void setFourthSetWeight(String number) {
        this.weight4 = number;
        if (!this.weight4.equals(""))
            exercise.getExerciseSets().get(3).setSetWeight(Double.parseDouble(weight4));
        notifyChange();
    }

    @Bindable
    public String getFourthSetWeight() {
        if (!weight4.equals("0.0"))return weight4;
        else return "";
    }

    //5 set
    public void setFifthSetWeight(String number) {
        this.weight5 = number;
        if (!this.weight5.equals(""))
            exercise.getExerciseSets().get(4).setSetWeight(Double.parseDouble(weight5));
        notifyChange();
    }

    @Bindable
    public String getFifthSetWeight() {
        if (!weight5.equals("0.0"))return weight5;
        else return "";
    }

    //----------------------------------------------------------------------------------------------

    public void action() {
        if (actionString.equals("create")) {
            save();
        }
        else {
            update();
        }
    }

    private void save() {
        if (exercise.getExerciseName() == null || exercise.getExerciseName().equals("")) {
            exercise.setExerciseName("exercise");
        }
        dao.addExercise(exercise);
    }

    private void update() {
        dao.updateExercise(exercise);
        for(Set set : exercise.getExerciseSets()) {
            dao.updateSet(set);
        }
    }
}
