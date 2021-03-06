package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.utils.ActionEnum;

import lombok.Getter;
import lombok.Setter;

public class TrainingViewModel extends BaseObservable {
    private Training training;

    @Setter
    private DAO dao;

    @Getter
    @Setter
    private ActionEnum action;

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
        notifyChange(); // ВНИМАНИЕ - ЭТО ИСПРАВИЛО ТО, ЧТО ПОСЛЕ СВАЙПА ОДНОГО ЭЛЕМЕНТА
                        // И ПОСЛЕДУЮЩЕГО СВАЙПА ДРУГОГО (ВСЕ ЭТО С ОТМЕНАМИ УДАЛЕНИЯ)
                        // ДРУГОЙ ИТЕМ БЫЛ С ДАННЫМИ ПЕРВОГО
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
        switch (action) {
            case CREATE: {
                this.save();
                break;
            }
            case UPDATE: {
                dao.updateTraining(training);
                break;
            }
        }
    }

    public void save() {
        dao.addTraining(training);
    }
}