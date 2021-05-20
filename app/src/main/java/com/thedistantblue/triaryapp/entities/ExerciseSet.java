package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "exercise_set_table")
public class ExerciseSet implements Serializable {
    @PrimaryKey
    private int id;
    private UUID setUUID;
    private UUID exerciseId;
    private int setNumber;
    private int setRepetitions;
    private double setWeight;

    public ExerciseSet(UUID exerciseId) {
        this(UUID.randomUUID(), exerciseId);
    }

    public ExerciseSet(UUID setUUID, UUID exerciseId) {
        this.setUUID = setUUID;
        this.exerciseId = exerciseId;
    }
}