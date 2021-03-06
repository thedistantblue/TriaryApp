package com.thedistantblue.triaryapp.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Exercise implements Serializable {
    private UUID id;
    private UUID datesId;

    public long getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(long exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    private long exerciseDate;

    private String exerciseName;
    private String exerciseComments;
    private List<Set> exerciseSets;

    public Exercise(UUID id, UUID datesId) {
        this.id = id;
        this.datesId = datesId;
    }

    public Exercise(UUID datesId)
    {
        this(UUID.randomUUID(), datesId);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public List<Set> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<Set> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }

    public UUID getDatesId() {
        return datesId;
    }

    public void setDatesId(UUID datesId) {
        this.datesId = datesId;
    }

    public String getExerciseComments() {
        return exerciseComments;
    }

    public void setExerciseComments(String exerciseComments) {
        this.exerciseComments = exerciseComments;
    }
}
