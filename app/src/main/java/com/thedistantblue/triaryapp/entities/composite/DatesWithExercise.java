package com.thedistantblue.triaryapp.entities.composite;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
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
    @Relation(parentColumn = EntityConstants.UUID_FIELD, entityColumn = EntityConstants.PARENT_UUID_FIELD)
    private List<Exercise> exerciseList;
}