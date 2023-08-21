package com.thedistantblue.triaryapp.database.sqlite;

import com.thedistantblue.triaryapp.entities.base.Day;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;

import java.util.List;

public interface DAO {

/*    static DAO get(Context context) {
        return LocalDAO.get(context);
    }*/

    void addUser(User user);

    void addTraining(Training training);

    void addDates(Day dates);

    void addExercise(Exercise exercise);

    void addSet(ExerciseSet exerciseSet);

    void addRunning(Running running);

    DataCursorWrapper queryData(String databasename, String whereClause, String[] whereArgs);

    User getUser(User user);

    Training getTraining(Training training);

    List<Training> getTrainingsList(User user);

    Exercise getExercise(Exercise exercise);

    List<Exercise> getExercisesList(Day dates);

    ExerciseSet getSet(Exercise exercise, int number);

    List<ExerciseSet> getSetsList(Exercise exercise);

    List<Day> getDates(Training training);

    Running getRunning(Running running);

    List<Running> getRunningList(User user);

    void updateTraining(Training training);

    void updateExercise(Exercise exercise);

    void updateSet(ExerciseSet exerciseSet);

    void updateRunning(Running running);

    void deleteUser(User user);

    void deleteTraining(Training training);

    void deleteDate(Day dates);

    void deleteExercise(Exercise exercise);

    void deleteSet(ExerciseSet exerciseSet);

    void deleteRunning(Running running);
}
