package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "running_table")
public class Running implements Serializable {
    private UUID id;
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

    public Running(UUID id, long userId) {
        this.id = id;
        this.userId = userId;
    }
}