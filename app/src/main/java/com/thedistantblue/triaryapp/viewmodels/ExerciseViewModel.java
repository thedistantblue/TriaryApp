package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseSetDao;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;
import com.thedistantblue.triaryapp.entities.composite.ExerciseWithExerciseSet;
import com.thedistantblue.triaryapp.utils.ActionEnum;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;

public class ExerciseViewModel extends BaseObservable {
    private ExerciseWithExerciseSet exerciseWithExerciseSet;

    @Setter
    private ExerciseWithExerciseSetDao exerciseWithExerciseSetDao;

    @Setter
    private ExerciseDao exerciseDao;

    @Setter
    private ExerciseSetDao exerciseSetDao;

    @Getter
    @Setter
    private ActionEnum action;

    String weight1 = "";
    String weight2 = "";
    String weight3 = "";
    String weight4 = "";
    String weight5 = "";
    private List<ExerciseSet> exerciseSetList;

    public void setExerciseSets(List<ExerciseSet> exerciseSetList) {
        this.exerciseSetList = exerciseSetList;
        weight1 = String.valueOf(this.exerciseSetList.get(0).getSetWeight());
        weight2 = String.valueOf(this.exerciseSetList.get(1).getSetWeight());
        weight3 = String.valueOf(this.exerciseSetList.get(2).getSetWeight());
        weight4 = String.valueOf(this.exerciseSetList.get(3).getSetWeight());
        weight5 = String.valueOf(this.exerciseSetList.get(4).getSetWeight());
        for (int i = 0; i < this.exerciseSetList.size(); i++) {
            System.out.println(weight5);
        }
    }

    public void setEmptyExerciseSets() {
        exerciseWithExerciseSet.setExerciseSetList(new ArrayList<>());
        exerciseSetList = exerciseWithExerciseSet.getExerciseSetList();
        for (int i = 0; i < 5; i++) {
            ExerciseSet exerciseSet = new ExerciseSet(exerciseWithExerciseSet.getExercise().getExerciseUUID());
            exerciseSet.setSetNumber(i);
            exerciseSetList.add(exerciseSet);
        }
    }

    public void setExerciseWithExerciseSet(ExerciseWithExerciseSet exerciseWithExerciseSet) {
        this.exerciseWithExerciseSet = exerciseWithExerciseSet;
    }

    public ExerciseWithExerciseSet getExerciseWithExerciseSet() {
        return exerciseWithExerciseSet;
    }

    public void setExerciseName(String name) {
        exerciseWithExerciseSet.getExercise().setExerciseName(name);
        notifyChange();
    }

    @Bindable
    public String getExerciseName() {
        return exerciseWithExerciseSet.getExercise().getExerciseName();
    }

    public void setExerciseComments(String comments) {
        exerciseWithExerciseSet.getExercise().setExerciseComments(comments);
        notifyChange();
    }

    @Bindable
    public String getExerciseComments() {
        return exerciseWithExerciseSet.getExercise().getExerciseComments();
    }

    //1 set
    public void setFirstSetReps(String number) {
        if (!number.equals("")) {
            exerciseWithExerciseSet.getExerciseSetList().get(0).setSetRepetitions(Integer.parseInt(number));
        }
        else
            exerciseWithExerciseSet.getExerciseSetList().get(0).setSetRepetitions(0);
        notifyChange();
    }

    @Bindable
    public String getFirstSetReps() {
        if (String.valueOf(exerciseWithExerciseSet.getExerciseSetList().get(0).getSetRepetitions()).equals("0")) return "";
        return String.valueOf(exerciseWithExerciseSet.getExerciseSetList().get(0).getSetRepetitions());
    }

    //2 set

    public void setSecondSetReps(String number) {
        if (!number.equals(""))
            exerciseWithExerciseSet.getExerciseSetList().get(1).setSetRepetitions(Integer.parseInt(number));
        else
            exerciseWithExerciseSet.getExerciseSetList().get(1).setSetRepetitions(0);
        notifyChange();
    }

    @Bindable
    public String getSecondSetReps() {
        if (String.valueOf(exerciseWithExerciseSet.getExerciseSetList().get(1).getSetRepetitions()).equals("0")) return "";
        return String.valueOf(exerciseWithExerciseSet.getExerciseSetList().get(1).getSetRepetitions());
    }

    //3 set
    public void setThirdSetReps(String number) {
        if (!number.equals(""))
            exerciseWithExerciseSet.getExerciseSetList().get(2).setSetRepetitions(Integer.parseInt(number));
        else
            exerciseWithExerciseSet.getExerciseSetList().get(2).setSetRepetitions(0);
        notifyChange();
    }

    @Bindable
    public String getThirdSetReps() {
        if (String.valueOf(exerciseWithExerciseSet.getExerciseSetList().get(2).getSetRepetitions()).equals("0")) return "";
        return String.valueOf(exerciseWithExerciseSet.getExerciseSetList().get(2).getSetRepetitions());
    }

    //4 set
    public void setFourthSetReps(String number) {
        if (!number.equals(""))
            exerciseWithExerciseSet.getExerciseSetList().get(3).setSetRepetitions(Integer.parseInt(number));
        else
            exerciseWithExerciseSet.getExerciseSetList().get(3).setSetRepetitions(0);
        notifyChange();
    }

    @Bindable
    public String getFourthSetReps() {
        if (String.valueOf(exerciseWithExerciseSet.getExerciseSetList().get(3).getSetRepetitions()).equals("0")) return "";
        return String.valueOf(exerciseWithExerciseSet.getExerciseSetList().get(3).getSetRepetitions());
    }

    //5 set
    public void setFifthSetReps(String number) {
        if (!number.equals(""))
            exerciseWithExerciseSet.getExerciseSetList().get(4).setSetRepetitions(Integer.parseInt(number));
        else
            exerciseWithExerciseSet.getExerciseSetList().get(4).setSetRepetitions(0);
        notifyChange();
    }

    @Bindable
    public String getFifthSetReps() {
        if (String.valueOf(exerciseWithExerciseSet.getExerciseSetList().get(4).getSetRepetitions()).equals("0")) return "";
        return String.valueOf(exerciseWithExerciseSet.getExerciseSetList().get(4).getSetRepetitions());
    }

    //----------------------------------------------------------------------------------------------

    //1 set
    public void setFirstSetWeight(String number) {
        this.weight1 = number;
        if (!this.weight1.equals(""))
            exerciseWithExerciseSet.getExerciseSetList().get(0).setSetWeight(Double.parseDouble(weight1));
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
            exerciseWithExerciseSet.getExerciseSetList().get(1).setSetWeight(Double.parseDouble(weight2));
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
            exerciseWithExerciseSet.getExerciseSetList().get(2).setSetWeight(Double.parseDouble(weight3));
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
            exerciseWithExerciseSet.getExerciseSetList().get(3).setSetWeight(Double.parseDouble(weight4));
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
            exerciseWithExerciseSet.getExerciseSetList().get(4).setSetWeight(Double.parseDouble(weight5));
        notifyChange();
    }

    @Bindable
    public String getFifthSetWeight() {
        if (!weight5.equals("0.0"))return weight5;
        else return "";
    }

    //----------------------------------------------------------------------------------------------

    public void action() {
        switch (action) {
            case CREATE: {
                save();
                break;
            }
            case UPDATE: {
                update();
                break;
            }
        }
    }

    private void save() {
        if (exerciseWithExerciseSet.getExercise().getExerciseName() == null || exerciseWithExerciseSet.getExercise().getExerciseName().equals("")) {
            exerciseWithExerciseSet.getExercise().setExerciseName("exercise");
        }
        Exercise exercise = exerciseWithExerciseSet.getExercise();
        List<ExerciseSet> exerciseSets = exerciseWithExerciseSet.getExerciseSetList();

        exerciseDao.create(exercise)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(() -> {
                       exerciseSets.forEach(exerciseSet -> exerciseSetDao.create(exerciseSet));
                   });
    }

    private void update() {
        Exercise exercise = exerciseWithExerciseSet.getExercise();
        List<ExerciseSet> exerciseSets = exerciseWithExerciseSet.getExerciseSetList();

        exerciseDao.save(exercise)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(() -> exerciseSets.forEach(exerciseSet -> exerciseSetDao.save(exerciseSet)));
    }
}
