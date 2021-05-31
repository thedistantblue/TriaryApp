package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.utils.ActionEnum;

import lombok.Getter;
import lombok.Setter;

public class TrainingViewModel extends BaseObservable {
    private Training training;

    @Setter
    private TrainingDao dao;

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
                dao.create(training).subscribe();
                break;
            }
            case UPDATE: {
                dao.save(training).subscribe();
                break;
            }
        }
    }

    public void save() {
        dao.create(training).subscribe();
    }
}