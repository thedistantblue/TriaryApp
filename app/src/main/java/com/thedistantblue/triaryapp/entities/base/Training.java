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
        foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = User.ID_FIELD_NAME,
                                  childColumns = Training.USER_ID_FIELD_NAME,
                                  onDelete = ForeignKey.CASCADE))
public class Training implements Serializable {

    public static final String UUID_FIELD_NAME = "trainingUUID";
    public static final String USER_ID_FIELD_NAME = "userId";

    @NonNull
    @PrimaryKey
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