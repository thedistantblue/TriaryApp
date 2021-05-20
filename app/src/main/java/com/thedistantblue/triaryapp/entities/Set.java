package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "set_table")
public class Set implements Serializable {
    private UUID id;
    private UUID exerciseId;
    private int setNumber;
    private int setRepetitions;
    private double setWeight;

    public Set(UUID exerciseId) {
        this(UUID.randomUUID(), exerciseId);
    }

    public Set(UUID id, UUID exerciseId) {
        this.id = id;
        this.exerciseId = exerciseId;
    }
}