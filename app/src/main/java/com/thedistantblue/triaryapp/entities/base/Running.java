package com.thedistantblue.triaryapp.entities.base;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;
import com.thedistantblue.triaryapp.entities.EntityConstants;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = DatabaseConstants.RUNNING_TABLE,
        primaryKeys = {EntityConstants.PRIMARY_KEY_FIELD_NAME, Running.UUID_FIELD_NAME},
        foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = User.ID_FIELD_NAME,
                                  childColumns = Running.USER_ID_FIELD_NAME,
                                  onDelete = ForeignKey.CASCADE))
public class Running implements Serializable {

    public static final String UUID_FIELD_NAME = "runningUUID";
    public static final String USER_ID_FIELD_NAME = "userId";

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

    @Ignore
    public Running(long userId) {
        this(UUID.randomUUID(), userId);
        this.date = new Date();
    }

    @Ignore
    public Running(UUID runningUUID, long userId) {
        this.runningUUID = runningUUID;
        this.userId = userId;
    }
}