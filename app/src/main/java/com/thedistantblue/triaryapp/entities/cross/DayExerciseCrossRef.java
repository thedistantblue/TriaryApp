package com.thedistantblue.triaryapp.entities.cross;

import static com.thedistantblue.triaryapp.database.room.database.DatabaseConstants.DAYS_WITH_EXERCISES_TABLE;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.thedistantblue.triaryapp.entities.EntityConstants;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = DAYS_WITH_EXERCISES_TABLE,
        primaryKeys = {
                EntityConstants.DAY_ID_FIELD,
                EntityConstants.EXERCISE_ID_FIELD})
public class DayExerciseCrossRef {
    @NonNull
    private UUID dayId;
    @NonNull
    private UUID exerciseId;
}