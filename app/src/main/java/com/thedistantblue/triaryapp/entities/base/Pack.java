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
    private String name = "";
    private String description = "";

    public Pack() {

    }

    @Ignore
    public Pack(UUID trainingId, String name, String description) {
        this.packId = UUID.randomUUID();
        this.trainingId = trainingId;
        this.name = name;
        this.description = description;
    }

    @Ignore
    public Pack(UUID trainingId) {
        this(UUID.randomUUID(), trainingId);
    }

    @Ignore
    public Pack(@NotNull UUID packId, UUID trainingId) {
        this.packId = packId;
        this.trainingId = trainingId;
    }

    @Ignore
    public Pack(@NotNull UUID packId, UUID trainingId, String name, String description) {
        this.packId = packId;
        this.trainingId = trainingId;
        this.name = name;
        this.description = description;
    }

    @NonNull
    public UUID getPackId() {
        return packId;
    }

    public void setPackId(@NonNull UUID packId) {
        this.packId = packId;
    }

    public UUID getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(UUID trainingId) {
        this.trainingId = trainingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}