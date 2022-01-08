package com.thedistantblue.triaryapp.entities.composite.details;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.Pack;
import com.thedistantblue.triaryapp.entities.base.Training;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainingDetails implements Serializable {
    @Embedded private Training training;

    @Relation(parentColumn = EntityConstants.UUID_FIELD, entityColumn = EntityConstants.PARENT_UUID_FIELD)
    private List<Pack> packList;
    @Relation(parentColumn = EntityConstants.UUID_FIELD, entityColumn = EntityConstants.PARENT_UUID_FIELD)
    private List<Dates> datesList;
    @Relation(parentColumn = EntityConstants.UUID_FIELD, entityColumn = EntityConstants.PARENT_UUID_FIELD)
    private List<Exercise> exerciseList;
}