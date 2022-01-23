package com.thedistantblue.triaryapp.entities.cross;

import static com.thedistantblue.triaryapp.database.room.database.DatabaseConstants.EXERCISES_WITH_PACKS_TABLE;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.thedistantblue.triaryapp.entities.EntityConstants;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = EXERCISES_WITH_PACKS_TABLE,
        primaryKeys = {
                EntityConstants.EXERCISE_ID_FIELD,
                EntityConstants.PACK_ID_FIELD})
public class ExercisePackCrossRef {
    @NonNull
    private UUID exerciseId;
    @NonNull
    private UUID packId;
}