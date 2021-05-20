package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "running_table")
public class Running implements Serializable {
    @PrimaryKey
    private int id;
    private UUID runningUUID;
    private long userId;
    private String runningName;
    private Date date;
    private double distance;
    private double speed;
    private double time;
    private double calories;
    private String comments;

    public Running(long userId) {
        this(UUID.randomUUID(), userId);
        this.date = new Date();
    }

    public Running(UUID runningUUID, long userId) {
        this.runningUUID = runningUUID;
        this.userId = userId;
    }
}