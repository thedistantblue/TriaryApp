package com.thedistantblue.triaryapp.entities.composite.details;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.cross.DayExerciseCrossRef;
import com.thedistantblue.triaryapp.entities.base.Day;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.cross.ExercisePackCrossRef;
import com.thedistantblue.triaryapp.entities.base.Pack;

import java.util.Collection;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseDetails {
    @Embedded private Exercise exercise;

    @Relation(
            parentColumn = EntityConstants.EXERCISE_ID_FIELD,
            entityColumn = EntityConstants.PACK_ID_FIELD,
            associateBy = @Junction(ExercisePackCrossRef.class)
    )
    public List<Pack> packs;

    @Relation(
            parentColumn = EntityConstants.EXERCISE_ID_FIELD,
            entityColumn = EntityConstants.DAY_ID_FIELD,
            associateBy = @Junction(DayExerciseCrossRef.class)
    )
    public List<Day> days;

    public Exercise getExercise() {
        return exercise;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public List<Day> getDays() {
        return days;
    }
}