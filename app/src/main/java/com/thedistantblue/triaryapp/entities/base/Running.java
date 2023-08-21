package com.thedistantblue.triaryapp.entities.base;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;
import com.thedistantblue.triaryapp.entities.EntityConstants;

import org.jetbrains.annotations.NotNull;

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
                                  parentColumns = EntityConstants.USER_ID_FIELD,
                                  childColumns = EntityConstants.USER_ID_FIELD,
                                  onDelete = ForeignKey.CASCADE))
public class Running implements Serializable {

    public static final String UUID_FIELD_NAME = "runningUUID";
    public static final String USER_ID_FIELD_NAME = "userId";

    private int id;
    @NonNull
    private UUID runningUUID;
    private long userId;
    private String runningName;
    private long date;
    private double distance;
    private double speed;
    private double time;
    private double calories;
    private String comments;

    @Ignore
    public Running(long userId) {
        this(UUID.randomUUID(), userId);
        this.date = new Date().getTime();
    }

    @Ignore
    public Running(@NotNull UUID runningUUID, long userId) {
        this.runningUUID = runningUUID;
        this.userId = userId;
    }
}