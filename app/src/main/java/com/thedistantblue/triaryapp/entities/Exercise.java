package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "exercise_table")
public class Exercise implements Serializable {
    private UUID id;
    private UUID datesId;
    private long exerciseDate;
    private String exerciseName;
    private String exerciseComments;
    private List<Set> exerciseSets;

    public Exercise(UUID id, UUID datesId) {
        this.id = id;
        this.datesId = datesId;
    }

    public Exercise(UUID datesId) {
        this(UUID.randomUUID(), datesId);
    }
}