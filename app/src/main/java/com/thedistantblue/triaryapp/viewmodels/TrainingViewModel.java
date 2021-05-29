package com.thedistantblue.triaryapp.viewmodels;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.database.DatabaseCaller;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.utils.ActionEnum;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;

public class TrainingViewModel extends BaseObservable {
    private Training training;

    @Setter
    private TrainingDao dao;
    @Setter
    private DatabaseCaller databaseCaller;

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
                databaseCaller.create(dao, training,
                                      () -> Log.d("TRAINING_CREATED_TAG", "action: training created"));
                break;
            }
            case UPDATE: {
                databaseCaller.save(dao, training,
                                    () -> Log.d("TRAINING_UPDATED_TAG", "action: training updated"));
                break;
            }
        }
    }

    public void save() {
        databaseCaller.create(dao, training,
                              () -> Log.d("TRAINING_CREATED_TAG", "action: training created"));
    }
}