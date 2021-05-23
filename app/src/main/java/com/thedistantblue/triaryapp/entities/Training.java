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
@Entity(tableName = "training_table",
        foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = "userID",
                                  childColumns = "userId",
                                  onDelete = ForeignKey.CASCADE))
public class Training implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private UUID trainingUUID;
    private long userId;
    private String trainingName;

    @Relation(parentColumn = "trainingUUID", entityColumn = "datesTrainingUUID")
    private List<Dates> trainingDates;

    public Training(UUID trainingUUID, long userId) {
        this.trainingUUID = trainingUUID;
        this.userId = userId;
    }

    public Training(long userId) {
        this(UUID.randomUUID(), userId);
    }
}