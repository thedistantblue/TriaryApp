package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "dates_table")
public class Dates implements Serializable {
    private UUID id;
    private UUID datesTrainingUUID;
    private long datesDate;
    private List<Exercise> datesExerciseList;

    public Dates(UUID id, UUID datesTrainingUUID) {
        this.id = id;
        this.datesTrainingUUID = datesTrainingUUID;
    }

    public Dates(UUID trainingId) {
        this(UUID.randomUUID(), trainingId);
    }
}