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
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Entity(tableName = DatabaseConstants.DATES_TABLE,
        foreignKeys = @ForeignKey(entity = Training.class,
                                  parentColumns = Training.Fields.trainingUUID,
                                  childColumns = Dates.Fields.datesTrainingUUID,
                                  onDelete = ForeignKey.CASCADE))
public class Dates implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private UUID datesUUID;
    private UUID datesTrainingUUID;
    private long datesDate;

    @Relation(parentColumn = Fields.datesUUID, entityColumn = Exercise.Fields.datesId)
    private List<Exercise> datesExerciseList;

    public Dates(UUID datesUUID, UUID datesTrainingUUID) {
        this.datesUUID = datesUUID;
        this.datesTrainingUUID = datesTrainingUUID;
    }

    public Dates(UUID trainingId) {
        this(UUID.randomUUID(), trainingId);
    }
}