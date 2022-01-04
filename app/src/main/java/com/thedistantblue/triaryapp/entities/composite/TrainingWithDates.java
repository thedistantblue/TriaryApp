package com.thedistantblue.triaryapp.entities.composite;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Training;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class TrainingWithDates {
    @Embedded private Training training;
    @Relation(parentColumn = EntityConstants.UUID_FIELD, entityColumn = EntityConstants.PARENT_UUID_FIELD)
    private List<Dates> datesList;
}