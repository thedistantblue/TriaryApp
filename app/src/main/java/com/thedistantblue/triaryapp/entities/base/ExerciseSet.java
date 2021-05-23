package com.thedistantblue.triaryapp.entities.base;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;
import com.thedistantblue.triaryapp.entities.EntityConstants;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = DatabaseConstants.EXERCISE_SET_TABLE,
        primaryKeys = {EntityConstants.PRIMARY_KEY_FIELD_NAME, ExerciseSet.UUID_FIELD_NAME},
        foreignKeys = @ForeignKey(entity = Exercise.class,
                                  parentColumns = Exercise.UUID_FIELD_NAME,
                                  childColumns = ExerciseSet.EXERCISE_UUID_FIELD,
                                  onDelete = ForeignKey.CASCADE))
public class ExerciseSet implements Serializable {

    public static final String UUID_FIELD_NAME = "exerciseSetUUID";
    public static final String EXERCISE_UUID_FIELD = "exerciseId";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private UUID exerciseSetUUID;
    private UUID exerciseId;
    private int setNumber;
    private int setRepetitions;
    private double setWeight;

    @Ignore
    public ExerciseSet(UUID exerciseId) {
        this(UUID.randomUUID(), exerciseId);
    }

    @Ignore
    public ExerciseSet(UUID exerciseSetUUID, UUID exerciseId) {
        this.exerciseSetUUID = exerciseSetUUID;
        this.exerciseId = exerciseId;
    }
}