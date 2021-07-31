package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public class TrainingCardViewModel extends BaseObservable {
    public final ObservableField<String> trainingName = new ObservableField<>();
}