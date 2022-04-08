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
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = DatabaseConstants.TRAINING_TABLE,
        foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = EntityConstants.USER_ID_FIELD,
                                  childColumns = EntityConstants.USER_ID_FIELD,
                                  onDelete = ForeignKey.CASCADE))
public class Training implements Serializable {

    @NonNull
    @PrimaryKey
    private UUID trainingId;
    private long userId;
    private String trainingName;

    @Ignore
    public Training(@NotNull UUID trainingId, long userId) {
        this.trainingId = trainingId;
        this.userId = userId;
    }

    @Ignore
    public Training(long userId) {
        this(UUID.randomUUID(), userId);
    }

    @NonNull
    public UUID getTrainingId() {
        return trainingId;
    }

    public long getUserId() {
        return userId;
    }

    public String getTrainingName() {
        return trainingName;
    }
}