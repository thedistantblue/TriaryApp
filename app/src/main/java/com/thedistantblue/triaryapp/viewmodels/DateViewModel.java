package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.entities.base.Dates;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateViewModel extends BaseObservable {

    private Dates date;
    private String dateName;

    public void setDate(Dates dates) {
        this.date = dates;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateName = format.format(dates.getDatesDate());
    }

    @Bindable
    public String getDate() {
        return dateName;
    }
}
