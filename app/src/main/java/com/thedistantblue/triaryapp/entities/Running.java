package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Entity(tableName = DatabaseConstants.RUNNING_TABLE,
        foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = User.Fields.userID,
                                  childColumns = Running.Fields.userId,
                                  onDelete = ForeignKey.CASCADE))
public class Running implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private UUID runningUUID;
    private long userId;
    private String runningName;
    private Date date;
    private double distance;
    private double speed;
    private double time;
    private double calories;
    private String comments;

    public Running(long userId) {
        this(UUID.randomUUID(), userId);
        this.date = new Date();
    }

    public Running(UUID runningUUID, long userId) {
        this.runningUUID = runningUUID;
        this.userId = userId;
    }
}