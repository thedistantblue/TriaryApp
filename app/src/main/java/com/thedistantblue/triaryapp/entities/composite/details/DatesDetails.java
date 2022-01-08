package com.thedistantblue.triaryapp.entities.composite.details;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.base.DateExerciseCrossRef;
import com.thedistantblue.triaryapp.entities.base.DatePackCrossRef;
import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.Pack;

import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DatesDetails {
    @Embedded private Dates dates;

    @Relation(
            parentColumn = EntityConstants.DATE_ID,
            entityColumn = EntityConstants.EXERCISE_ID,
            associateBy = @Junction(DateExerciseCrossRef.class)
    )
    public Collection<Exercise> exercises;

    @Relation(
            parentColumn = EntityConstants.DATE_ID,
            entityColumn = EntityConstants.PACK_ID,
            associateBy = @Junction(DatePackCrossRef.class)
    )
    public Collection<Pack> packs;
}