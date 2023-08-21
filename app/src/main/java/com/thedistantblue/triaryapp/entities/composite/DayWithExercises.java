package com.thedistantblue.triaryapp.entities.composite;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.base.Day;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.cross.ExercisePackCrossRef;

import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DayWithExercises {
    @Embedded private Day day;

    @Relation(
            parentColumn = EntityConstants.DAY_ID_FIELD,
            entityColumn = EntityConstants.EXERCISE_ID_FIELD,
            associateBy = @Junction(ExercisePackCrossRef.class)
    )
    public Collection<Exercise> exercises;
}