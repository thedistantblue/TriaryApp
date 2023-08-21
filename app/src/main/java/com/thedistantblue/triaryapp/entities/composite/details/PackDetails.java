package com.thedistantblue.triaryapp.entities.composite.details;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.cross.DayPackCrossRef;
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
public class PackDetails {
    @Embedded private Pack pack;

    @Relation(
            parentColumn = EntityConstants.PACK_ID_FIELD,
            entityColumn = EntityConstants.EXERCISE_ID_FIELD,
            associateBy = @Junction(ExercisePackCrossRef.class)
    )
    public List<Exercise> exercises;

    @Relation(
            parentColumn = EntityConstants.PACK_ID_FIELD,
            entityColumn = EntityConstants.DAY_ID_FIELD,
            associateBy = @Junction(DayPackCrossRef.class)
    )
    public List<Day> dates;

    public Pack getPack() {
        return this.pack;
    }

    public List<Exercise> getExercises() {
        return this.exercises;
    }

    public List<Day> getDates() {
        return this.dates;
    }
}