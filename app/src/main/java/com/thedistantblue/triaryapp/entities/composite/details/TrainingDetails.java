package com.thedistantblue.triaryapp.entities.composite.details;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.base.Day;
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

    @Relation(parentColumn = EntityConstants.TRAINING_ID_FIELD, entityColumn = EntityConstants.EXERCISE_ID_FIELD)
    private List<Exercise> exerciseList;
    @Relation(parentColumn = EntityConstants.TRAINING_ID_FIELD, entityColumn = EntityConstants.PACK_ID_FIELD)
    private List<Pack> packList;
    @Relation(parentColumn = EntityConstants.TRAINING_ID_FIELD, entityColumn = EntityConstants.DAY_ID_FIELD)
    private List<Day> datesList;

    public Training getTraining() {
        return training;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public List<Pack> getPackList() {
        return packList;
    }

    public List<Day> getDatesList() {
        return datesList;
    }
}