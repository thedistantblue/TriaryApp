package com.thedistantblue.triaryapp.viewmodels;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.entities.Training;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainingViewModel extends BaseObservable {
    private Training training;
    private DAO dao;
    String actionString;

    public void setActionString(String actionString) {
        this.actionString = actionString;
    }

    public String getActionString(){
        return actionString;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
        notifyChange(); // ВНИМАНИЕ - ЭТО ИСПРАВИЛО ТО, ЧТО ПОСЛЕ СВАЙПА ОДНОГО ЭЛЕМЕНТА
                        // И ПОСЛЕДУЮЩЕГО СВАЙПА ДРУГОГО (ВСЕ ЭТО С ОТМЕНАМИ УДАЛЕНИЯ)
                        // ДРУГОЙ ИТЕМ БЫЛ С ДАННЫМИ ПЕРВОГО
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }


    @Bindable
    public String getTrainingDate() {
        return "test";
        //return training.getTrainingDate().toString();
    }

    /*
    public void setTrainingDate(Date date) {
        training.setTrainingDate(date.getTime());
        notifyChange();
    }
    */


    public void setTrainingName(String name) {
        training.setTrainingName(name);
        notifyChange();
    }

    @Bindable
    public String getTrainingName() {
        return training.getTrainingName();
    }

    public void action() {
        if (actionString.equals("create")) {
            this.save();
        } else {
            dao.updateTraining(training);
        }
    }

    public void save() {
        dao.addTraining(training);
    }
}
