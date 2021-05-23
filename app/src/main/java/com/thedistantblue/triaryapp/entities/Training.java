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
@Entity(tableName = DatabaseConstants.TRAINING_TABLE,
        foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = User.Fields.userID,
                                  childColumns = Training.Fields.userId,
                                  onDelete = ForeignKey.CASCADE))
public class Training implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private UUID trainingUUID;
    private long userId;
    private String trainingName;

    @Relation(parentColumn = Fields.trainingUUID, entityColumn = Dates.Fields.datesTrainingUUID)
    private List<Dates> trainingDates;

    public Training(UUID trainingUUID, long userId) {
        this.trainingUUID = trainingUUID;
        this.userId = userId;
    }

    public Training(long userId) {
        this(UUID.randomUUID(), userId);
    }
}