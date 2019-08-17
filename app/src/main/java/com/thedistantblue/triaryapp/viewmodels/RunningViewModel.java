package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.entities.Running;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RunningViewModel extends BaseObservable {
    private Running running;
    private DAO dao;
    private String actionString;
    String distance = "";
    String speed = "";
    String time = "";
    String calories = "";

    public Running getRunning() {
        return running;
    }

    public void setRunning(Running running)
    {
        this.running = running;
        distance = String.valueOf(running.getDistance());
        speed = String.valueOf(running.getSpeed());
        time = String.valueOf(running.getTime());
        calories = String.valueOf(running.getCalories());
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public void setActionString(String actionString) {
        this.actionString = actionString;
    }

    public String getActionString() {
        return actionString;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(running.getDate());
        //return running.getDate().toString();
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
        String methodSpeed;
        if (!this.distance.equals("") && !this.time.equals("")) {
            methodSpeed = String.valueOf(Double.parseDouble(this.distance) / (Double.parseDouble(this.time) / 60));
            running.setSpeed(Double.parseDouble(methodSpeed));
            return methodSpeed;
        }
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
