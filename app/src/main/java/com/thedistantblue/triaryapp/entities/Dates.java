package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "dates_table")
public class Dates implements Serializable {
    @PrimaryKey
    private int id;
    private UUID datesUUID;
    private UUID datesTrainingUUID;
    private long datesDate;
    private List<Exercise> datesExerciseList;

    public Dates(UUID datesUUID, UUID datesTrainingUUID) {
        this.datesUUID = datesUUID;
        this.datesTrainingUUID = datesTrainingUUID;
    }

    public Dates(UUID trainingId) {
        this(UUID.randomUUID(), trainingId);
    }
}