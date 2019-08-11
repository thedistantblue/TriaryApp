package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.entities.Running;

import java.util.Date;

public class RunningViewModel extends BaseObservable {
    private Running running;
    private DAO dao;
    private String actionString;

    public Running getRunning() {
        return running;
    }

    public void setRunning(Running running) {
        this.running = running;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public void setActionString(String actionString) {
        this.actionString = actionString;
    }

    public void setRunningName(String name) {
        running.setRunningName(name);
        notifyChange();
    }

    @Bindable
    public String getRunningName() {
        return running.getRunningName();
    }

    public void setRunningDate(Date date) {
        running.setDate(date);
        notifyChange();
    }

    @Bindable
    public String getRunningDate() {
        return running.getDate().toString();
    }

    public void setRunningDistance(String distance) {
        if (!distance.equals("")) {
            running.setDistance(Double.parseDouble(distance));
        }
        notifyChange();
    }

    @Bindable
    public String getRunningDistance() {
        return String.valueOf(running.getDistance());
    }

    public void setRunningSpeed(String speed) {
        running.setSpeed(Double.parseDouble(speed));
        notifyChange();
    }

    @Bindable
    public String getRunningSpeed() {
        return String.valueOf(running.getSpeed());
    }

    public void setRunningTime(String time) {
        running.setTime(Double.parseDouble(time));
        notifyChange();
    }

    @Bindable
    public String getRunningTime() {
        return String.valueOf(running.getTime());
    }

    public void setRunningCalories(String calories) {
        running.setCalories(Double.parseDouble(calories));
        notifyChange();
    }

    @Bindable
    public String getRunningCalories() {
        return String.valueOf(running.getCalories());
    }

    public void setRunningComments(String comments) {
        running.setComments(comments);
        notifyChange();
    }

    @Bindable
    public String getRunningComments() {
        return running.getComments();
    }

    public void save() {
        if (actionString.equals("create")) {
            dao.addRunning(running);
        } else {
            update();
        }
    }

    public void update() {
        dao.updateRunning(running);
    }
}
