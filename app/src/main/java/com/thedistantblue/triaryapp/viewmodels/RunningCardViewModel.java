package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public class RunningCardViewModel extends BaseObservable {
    public final ObservableField<String> runningName = new ObservableField<>();
}