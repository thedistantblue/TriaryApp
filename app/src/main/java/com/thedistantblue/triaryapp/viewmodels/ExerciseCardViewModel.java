package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public class ExerciseCardViewModel extends BaseObservable {
    public final ObservableField<String> exerciseName = new ObservableField<>();
}