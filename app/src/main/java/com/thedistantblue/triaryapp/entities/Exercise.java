package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
@Entity(tableName = "exercise_table",
        foreignKeys = @ForeignKey(entity = Dates.class,
                                  parentColumns = "datesUUID",
                                  childColumns = "datesId",
                                  onDelete = ForeignKey.CASCADE))
public class Exercise implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private UUID exerciseUUID;
    private UUID datesId;
    private long exerciseDate;
    private String exerciseName;
    private String exerciseComments;

    @Relation(parentColumn = "exerciseUUID", entityColumn = "exerciseId")
    private List<ExerciseSet> exerciseExerciseSets;

    public Exercise(UUID exerciseUUID, UUID datesId) {
        this.exerciseUUID = exerciseUUID;
        this.datesId = datesId;
    }

    public Exercise(UUID datesId) {
        this(UUID.randomUUID(), datesId);
    }
}