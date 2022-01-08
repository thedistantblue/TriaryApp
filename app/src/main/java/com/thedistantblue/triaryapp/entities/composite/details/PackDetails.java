package com.thedistantblue.triaryapp.entities.composite.details;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.base.DatePackCrossRef;
import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExercisePackCrossRef;
import com.thedistantblue.triaryapp.entities.base.Pack;

import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PackDetails {
    @Embedded private Pack pack;

    @Relation(
            parentColumn = EntityConstants.PACK_ID,
            entityColumn = EntityConstants.EXERCISE_ID,
            associateBy = @Junction(ExercisePackCrossRef.class)
    )
    public Collection<Exercise> exercises;

    @Relation(
            parentColumn = EntityConstants.PACK_ID,
            entityColumn = EntityConstants.DATE_ID,
            associateBy = @Junction(DatePackCrossRef.class)
    )
    public Collection<Dates> dates;
}