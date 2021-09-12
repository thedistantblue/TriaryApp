package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.entities.base.Training;

import lombok.Getter;

public class TrainingViewModel extends BaseObservable {

    @Getter
    private Training training;

    public TrainingViewModel(Training training) {
        this.training = training;
    }

    public void setTrainingName(String name) {
        training.setTrainingName(name);
        notifyChange();
    }

    @Bindable
    public String getTrainingName() {
        return training.getTrainingName();
    }

}