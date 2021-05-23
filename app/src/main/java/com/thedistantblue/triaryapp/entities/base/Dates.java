package com.thedistantblue.triaryapp.entities.base;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;
import com.thedistantblue.triaryapp.entities.EntityConstants;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = DatabaseConstants.DATES_TABLE,
        primaryKeys = {EntityConstants.PRIMARY_KEY_FIELD_NAME, Dates.UUID_FIELD_NAME},
        foreignKeys = @ForeignKey(entity = Training.class,
                                  parentColumns = Training.UUID_FIELD_NAME,
                                  childColumns = Dates.TRAINING_UUID_FIELD_NAME,
                                  onDelete = ForeignKey.CASCADE))
public class Dates implements Serializable {

    public static final String UUID_FIELD_NAME = "datesUUID";
    public static final String TRAINING_UUID_FIELD_NAME = "datesTrainingUUID";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private UUID datesUUID;
    private UUID datesTrainingUUID;
    private long datesDate;

    @Ignore
    public Dates(UUID datesUUID, UUID datesTrainingUUID) {
        this.datesUUID = datesUUID;
        this.datesTrainingUUID = datesTrainingUUID;
    }

    @Ignore
    public Dates(UUID trainingId) {
        this(UUID.randomUUID(), trainingId);
    }
}