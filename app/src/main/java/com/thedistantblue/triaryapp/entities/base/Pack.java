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
@Entity(tableName = DatabaseConstants.PACK_TABLE,
        foreignKeys = @ForeignKey(entity = Training.class,
                                  parentColumns = EntityConstants.TRAINING_ID_FIELD,
                                  childColumns = EntityConstants.TRAINING_ID_FIELD,
                                  onDelete = ForeignKey.CASCADE))
public class Pack implements Serializable {

    @NonNull
    @PrimaryKey
    private UUID packId;
    private UUID trainingId;

    @Ignore
    public Pack(@NotNull UUID packId, UUID trainingId) {
        this.packId = packId;
        this.trainingId = trainingId;
    }

    @Ignore
    public Pack(UUID trainingId) {
        this(UUID.randomUUID(), trainingId);
    }
}