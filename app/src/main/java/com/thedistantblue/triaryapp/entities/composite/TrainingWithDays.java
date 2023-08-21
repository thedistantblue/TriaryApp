package com.thedistantblue.triaryapp.entities.composite;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.base.Day;
import com.thedistantblue.triaryapp.entities.base.Training;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class TrainingWithDays {
    @Embedded private Training training;

    @Relation(parentColumn = EntityConstants.TRAINING_ID_FIELD,
              entityColumn = EntityConstants.TRAINING_ID_FIELD
    )
    private List<Day> datesList;
}