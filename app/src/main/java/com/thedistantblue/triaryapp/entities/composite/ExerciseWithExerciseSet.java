package com.thedistantblue.triaryapp.entities.composite;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseWithExerciseSet {
    @Embedded private Exercise exercise;

    @Relation(parentColumn = EntityConstants.EXERCISE_ID_FIELD,
              entityColumn = EntityConstants.EXERCISE_ID_FIELD
    )
    private List<ExerciseSet> exerciseSetList = new ArrayList<>();
}