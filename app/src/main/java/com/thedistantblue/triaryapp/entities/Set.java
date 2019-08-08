package com.thedistantblue.triaryapp.entities;

import java.util.UUID;

public class Set {
    private UUID id;
    private UUID exerciseId;
    private int setNumber;
    private int setRepetitions;
    private int setWeight;

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

    public UUID getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(UUID exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getSetRepetitions() {
        return setRepetitions;
    }

    public void setSetRepetitions(int setRepetitions) {
        this.setRepetitions = setRepetitions;
    }

    public int getSetWeight() {
        return setWeight;
    }

    public void setSetWeight(int setWeight) {
        this.setWeight = setWeight;
    }
}
