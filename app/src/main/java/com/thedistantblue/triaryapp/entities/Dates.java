package com.thedistantblue.triaryapp.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Dates implements Serializable {
    private long datesDate;
    private UUID id;
    private UUID datesTrainingUUID;
    private List<Exercise> datesExerciseList;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Exercise> getDatesExerciseList() {
        return datesExerciseList;
    }

    public void setDatesExerciseList(List<Exercise> datesExerciseList) {
        this.datesExerciseList = datesExerciseList;
    }

    public long getDatesDate() {
        return datesDate;
    }

    public void setDatesDate(long datesDate) {
        this.datesDate = datesDate;
    }

    public UUID getDatesTrainingUUID() {
        return datesTrainingUUID;
    }

    public void setDatesTrainingUUID(UUID datesTrainingUUID) {
        this.datesTrainingUUID = datesTrainingUUID;
    }
}
