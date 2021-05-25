package com.thedistantblue.triaryapp.entities.base;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;
import com.thedistantblue.triaryapp.entities.EntityConstants;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = DatabaseConstants.TRAINING_TABLE,
        primaryKeys = {EntityConstants.PRIMARY_KEY_FIELD_NAME, Training.UUID_FIELD_NAME},
        foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = "userID",
                                  childColumns = "userId",
                                  onDelete = ForeignKey.CASCADE))
public class Training implements Serializable {

    public static final String UUID_FIELD_NAME = "trainingUUID";
    public static final String USER_ID_FIELD_NAME = "userId";

    @ColumnInfo(index = true)
    private int id;
    @NonNull
    @ColumnInfo(index = true)
    private UUID trainingUUID;
    private long userId;
    private String trainingName;

    @Ignore
    public Training(@NotNull UUID trainingUUID, long userId) {
        this.trainingUUID = trainingUUID;
        this.userId = userId;
    }

    @Ignore
    public Training(long userId) {
        this(UUID.randomUUID(), userId);
    }
}