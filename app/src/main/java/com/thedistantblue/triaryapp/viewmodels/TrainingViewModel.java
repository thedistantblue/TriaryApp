package com.thedistantblue.triaryapp.viewmodels;

import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.mainscreen.TrainingFlow.DateFragment;

public class TrainingViewModel {
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
}
