package com.thedistantblue.triaryapp.entities.composite.details;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.cross.DayExerciseCrossRef;
import com.thedistantblue.triaryapp.entities.cross.DayPackCrossRef;
import com.thedistantblue.triaryapp.entities.base.Day;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.Pack;

import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DayDetails {
    @Embedded private Day day;

    @Relation(
            parentColumn = EntityConstants.DAY_ID_FIELD,
            entityColumn = EntityConstants.EXERCISE_ID_FIELD,
            associateBy = @Junction(DayExerciseCrossRef.class)
    )
    public Collection<Exercise> exercises;

    @Relation(
            parentColumn = EntityConstants.DAY_ID_FIELD,
            entityColumn = EntityConstants.PACK_ID_FIELD,
            associateBy = @Junction(DayPackCrossRef.class)
    )
    public Collection<Pack> packs;
}