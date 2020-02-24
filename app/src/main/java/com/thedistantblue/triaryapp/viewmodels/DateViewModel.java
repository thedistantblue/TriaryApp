package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.entities.Dates;

public class DateViewModel extends BaseObservable {

    private Dates date;
    private String dateName;

    public void setDate(Dates dates) {
        this.date = dates;
        dateName = String.valueOf(dates.getDatesDate());
    }

    @Bindable
    public String getDate() {
        return dateName;
    }
}
