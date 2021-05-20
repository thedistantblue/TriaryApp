package com.thedistantblue.triaryapp.database.sqlite;

import android.content.Context;

import com.thedistantblue.triaryapp.entities.Dates;
import com.thedistantblue.triaryapp.entities.Exercise;
import com.thedistantblue.triaryapp.entities.Running;
import com.thedistantblue.triaryapp.entities.Set;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.entities.User;

import java.util.List;

public interface DAO {

    static DAO get(Context context) {
        return LocalDAO.get(context);
    }

    void addUser(User user);

    void addTraining(Training training);

    void addDates(Dates dates);

    void addExercise(Exercise exercise);

    void addSet(Set set);

    void addRunning(Running running);

    DataCursorWrapper queryData(String databasename, String whereClause, String[] whereArgs);

    User getUser(User user);

    Training getTraining(Training training);

    List<Training> getTrainingsList(User user);

    Exercise getExercise(Exercise exercise);

    List<Exercise> getExercisesList(Dates dates);

    Set getSet(Exercise exercise, int number);

    List<Set> getSetsList(Exercise exercise);

    List<Dates> getDates(Training training);

    Running getRunning(Running running);

    List<Running> getRunningList(User user);

    void updateTraining(Training training);

    void updateExercise(Exercise exercise);

    void updateSet(Set set);

    void updateRunning(Running running);

    void deleteUser(User user);

    void deleteTraining(Training training);

    void deleteDate(Dates dates);

    void deleteExercise(Exercise exercise);

    void deleteSet(Set set);

    void deleteRunning(Running running);
}
