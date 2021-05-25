package com.thedistantblue.triaryapp.entities.composite;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseWithExerciseSet {
    @Embedded private Exercise exercise;
    @Relation(parentColumn = Exercise.UUID_FIELD_NAME, entityColumn = ExerciseSet.EXERCISE_UUID_FIELD)
    private List<ExerciseSet> exerciseSetList;
}