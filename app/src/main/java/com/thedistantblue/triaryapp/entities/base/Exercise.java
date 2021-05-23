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
@Entity(tableName = DatabaseConstants.EXERCISE_TABLE,
        primaryKeys = {EntityConstants.PRIMARY_KEY_FIELD_NAME, Exercise.UUID_FIELD_NAME},
        foreignKeys = @ForeignKey(entity = Dates.class,
                                  parentColumns = Dates.UUID_FIELD_NAME,
                                  childColumns = Exercise.DATES_UUID_FIELD_NAME,
                                  onDelete = ForeignKey.CASCADE))
public class Exercise implements Serializable {

    public static final String UUID_FIELD_NAME = "exerciseUUID";
    public static final String DATES_UUID_FIELD_NAME = "datesId";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private UUID exerciseUUID;
    private UUID datesId;
    private long exerciseDate;
    private String exerciseName;
    private String exerciseComments;

    @Ignore
    public Exercise(UUID exerciseUUID, UUID datesId) {
        this.exerciseUUID = exerciseUUID;
        this.datesId = datesId;
    }

    @Ignore
    public Exercise(UUID datesId) {
        this(UUID.randomUUID(), datesId);
    }
}