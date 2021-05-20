package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "training_table")
public class Training implements Serializable {
    private UUID id;
    private long userId;
    private String trainingName;
    private List<Dates> trainingDates;

    public Training(UUID id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Training (long userId) {
        this(UUID.randomUUID(), userId);
    }
}