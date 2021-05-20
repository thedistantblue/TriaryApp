package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "training_table")
public class Training implements Serializable {
    @PrimaryKey
    private int id;
    private UUID trainingUUID;
    private long userId;
    private String trainingName;
    private List<Dates> trainingDates;

    public Training(UUID trainingUUID, long userId) {
        this.trainingUUID = trainingUUID;
        this.userId = userId;
    }

    public Training (long userId) {
        this(UUID.randomUUID(), userId);
    }
}