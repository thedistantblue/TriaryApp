package com.thedistantblue.triaryapp.entities;

import java.util.UUID;

public class Set {
    private UUID id;
    private UUID exerciseId;
    private String setNumber;
    private String setRepetitions;
    private String setWeight;

    public Set(UUID exerciseId) {
        this(UUID.randomUUID(), exerciseId);
    }

    public Set(UUID id, UUID exerciseId) {
        this.id = id;
        this.exerciseId = exerciseId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public String getSetRepetitions() {
        return setRepetitions;
    }

    public void setSetRepetitions(String setRepetitions) {
        this.setRepetitions = setRepetitions;
    }

    public String getSetWeight() {
        return setWeight;
    }

    public void setSetWeight(String setWeight) {
        this.setWeight = setWeight;
    }

    public UUID getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(UUID exerciseId) {
        this.exerciseId = exerciseId;
    }
}
