package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.mainscreen.TrainingFlow.DateFragment;

import java.util.Date;

public class TrainingViewModel extends BaseObservable {
    private Training training;
    private DAO dao;

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    @Bindable
    public String getTrainingDate() {
        return training.getTrainingDate().toString();
    }

    public void setTrainingDate(Date date) {
        training.setTrainingDate(date);
        notifyChange();
    }
}
