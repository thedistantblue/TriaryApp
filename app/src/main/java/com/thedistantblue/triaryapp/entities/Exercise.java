package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Entity(tableName = DatabaseConstants.EXERCISE_TABLE,
        foreignKeys = @ForeignKey(entity = Dates.class,
                                  parentColumns = Dates.Fields.datesUUID,
                                  childColumns = Exercise.Fields.datesId,
                                  onDelete = ForeignKey.CASCADE))
public class Exercise implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private UUID exerciseUUID;
    private UUID datesId;
    private long exerciseDate;
    private String exerciseName;
    private String exerciseComments;

    @Relation(parentColumn = Fields.exerciseUUID, entityColumn = ExerciseSet.Fields.exerciseId)
    private List<ExerciseSet> exerciseExerciseSets;

    public Exercise(UUID exerciseUUID, UUID datesId) {
        this.exerciseUUID = exerciseUUID;
        this.datesId = datesId;
    }

    public Exercise(UUID datesId) {
        this(UUID.randomUUID(), datesId);
    }
}