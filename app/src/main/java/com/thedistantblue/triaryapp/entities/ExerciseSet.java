package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Entity(tableName = DatabaseConstants.EXERCISE_SET_TABLE,
        foreignKeys = @ForeignKey(entity = Exercise.class,
                                  parentColumns = Exercise.Fields.exerciseUUID,
                                  childColumns = ExerciseSet.Fields.exerciseId,
                                  onDelete = ForeignKey.CASCADE))
public class ExerciseSet implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private UUID exerciseSetUUID;
    private UUID exerciseId;
    private int setNumber;
    private int setRepetitions;
    private double setWeight;

    public ExerciseSet(UUID exerciseId) {
        this(UUID.randomUUID(), exerciseId);
    }

    public ExerciseSet(UUID exerciseSetUUID, UUID exerciseId) {
        this.exerciseSetUUID = exerciseSetUUID;
        this.exerciseId = exerciseId;
    }
}