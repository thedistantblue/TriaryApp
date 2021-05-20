package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "exercise_table")
public class Exercise implements Serializable {
    @PrimaryKey
    private int id;
    private UUID exerciseUUID;
    private UUID datesId;
    private long exerciseDate;
    private String exerciseName;
    private String exerciseComments;
    private List<ExerciseSet> exerciseExerciseSets;

    public Exercise(UUID exerciseUUID, UUID datesId) {
        this.exerciseUUID = exerciseUUID;
        this.datesId = datesId;
    }

    public Exercise(UUID datesId) {
        this(UUID.randomUUID(), datesId);
    }
}