package com.thedistantblue.triaryapp.entities.composite.details;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.base.DateExerciseCrossRef;
import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExercisePackCrossRef;
import com.thedistantblue.triaryapp.entities.base.Pack;

import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseDetails {
    @Embedded private Exercise exercise;

    @Relation(
            parentColumn = EntityConstants.EXERCISE_ID,
            entityColumn = EntityConstants.PACK_ID,
            associateBy = @Junction(ExercisePackCrossRef.class)
    )
    public Collection<Pack> packs;

    @Relation(
            parentColumn = EntityConstants.EXERCISE_ID,
            entityColumn = EntityConstants.DATE_ID,
            associateBy = @Junction(DateExerciseCrossRef.class)
    )
    public Collection<Dates> dates;
}