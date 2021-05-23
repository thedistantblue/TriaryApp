package com.thedistantblue.triaryapp.entities.composite;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Training;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainingWithDates {
    @Embedded private Training training;
    @Relation(parentColumn = Training.UUID_FIELD_NAME, entityColumn = Dates.TRAINING_UUID_FIELD_NAME)
    private List<String> datesList;
}