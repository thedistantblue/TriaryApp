package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.entities.base.Running;

import java.text.SimpleDateFormat;

import lombok.Getter;

public class RunningViewModel extends BaseObservable {

    @Getter
    private final Running running;
    private String distance;
    private String speed;
    private String time;
    private String calories;

    public RunningViewModel(Running running) {
        this.running = running;
        this.distance = String.valueOf(running.getDistance());
        this.speed = String.valueOf(running.getSpeed());
        this.time = String.valueOf(running.getTime());
        this.calories = String.valueOf(running.getCalories());
    }

    public void setRunningName(String name) {
        running.setRunningName(name);
        notifyChange();
    }

    @Bindable
    public String getRunningName() {
        return running.getRunningName();
    }

    public void setRunningDate(long date) {
        running.setDate(date);
        notifyChange();
    }

    @Bindable
    public String getRunningDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(running.getDate());
    }

    public void setRunningDistance(String distance) {
        this.distance = distance;
        if (!this.distance.equals("")) {
            running.setDistance(Double.parseDouble(this.distance));
        }
        notifyChange();
    }

    @Bindable
    public String getRunningDistance() {
        return distance;
    }

    public void setRunningSpeed(String speed) {
        this.speed = speed;
        if (!this.speed.equals("")) {
            running.setSpeed(Double.parseDouble(speed));
        }
        notifyChange();
    }

    @Bindable
    public String getRunningSpeed() {
        return speed;
    }

    public void setRunningTime(String time) {
        this.time = time;
        if (!this.time.equals("")) {
            running.setTime(Double.parseDouble(time));
        }
        notifyChange();
    }

    @Bindable
    public String getRunningTime() {
        return time;
    }

    public void setRunningCalories(String calories) {
        this.calories = calories;
        if (!this.calories.equals("")) {
            running.setCalories(Double.parseDouble(calories));
        }
        notifyChange();
    }

    @Bindable
    public String getRunningCalories() {
        return calories;
    }

    public void setRunningComments(String comments) {
        running.setComments(comments);
        notifyChange();
    }

    @Bindable
    public String getRunningComments() {
        return running.getComments();
    }

}
