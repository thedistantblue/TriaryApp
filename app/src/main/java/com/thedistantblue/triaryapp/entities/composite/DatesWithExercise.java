package com.thedistantblue.triaryapp.entities.composite;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class DatesWithExercise {
    @Embedded private Dates dates;
    @Relation(parentColumn = Dates.UUID_FIELD_NAME, entityColumn = Exercise.DATES_UUID_FIELD_NAME)
    private List<Exercise> exerciseList;
}