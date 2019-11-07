package com.thedistantblue.triaryapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;

import com.thedistantblue.triaryapp.entities.Dates;
import com.thedistantblue.triaryapp.entities.Exercise;
import com.thedistantblue.triaryapp.entities.Running;
import com.thedistantblue.triaryapp.entities.Set;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.entities.User;

import java.util.Date;
import java.util.UUID;

public class DataCursorWrapper extends CursorWrapper {
    public DataCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser() {
        String uuid = getString(getColumnIndex(DatabaseScheme.UserTable.Columns.UUID));
        String name = getString(getColumnIndex(DatabaseScheme.UserTable.Columns.Name));
        String password = getString(getColumnIndex(DatabaseScheme.UserTable.Columns.Password));

        //User user = new User(UUID.fromString(uuid));
        User user = new User(Long.parseLong(uuid));
        user.setUserName(name);
        user.setUserPassword(password);
        return user;
    }

    public Training getTraining() {
        String uuid = getString(getColumnIndex(DatabaseScheme.TrainingTable.Columns.UUID));
        String userUUID = getString(getColumnIndex(DatabaseScheme.TrainingTable.Columns.UUID_USER));
        String name = getString(getColumnIndex(DatabaseScheme.TrainingTable.Columns.Name));
        //long date = getLong(getColumnIndex(DatabaseScheme.TrainingTable.Columns.Date));

        // По дате надо посмотреть код в DailyWorkout в аналогичном классе и в TrainingLab
        // в методе, который задает ContentValues.

        Training training = new Training(UUID.fromString(uuid), Long.parseLong(userUUID));
        //Training training = new Training(Long.parseLong(uuid));
        training.setTrainingName(name);
        //training.setUserId(UUID.fromString(userUUID));
        training.setUserId(Long.parseLong(userUUID));
        //training.setTrainingDate(date); // Здесь надо будет переработать
        return training;
    }

    public Dates getDates() {
        String trainingUUID = getString(getColumnIndex(DatabaseScheme.DateTable.Columns.UUID_TRAINING));
        long dates = getLong(getColumnIndex(DatabaseScheme.DateTable.Columns.Dates));

        Dates d = new Dates();
        d.setDatesTrainingUUID(UUID.fromString(trainingUUID));
        d.setDatesDate(dates);

        return d;
    }

    public Exercise getExercise() {
        String uuid = getString(getColumnIndex(DatabaseScheme.ExerciseTable.Columns.UUID));
        String trainingUUID = getString(getColumnIndex(DatabaseScheme.ExerciseTable.Columns.UUID_TRAINING));
        String name = getString(getColumnIndex(DatabaseScheme.ExerciseTable.Columns.Name));
        String comments = getString(getColumnIndex(DatabaseScheme.ExerciseTable.Columns.Comments));

        Exercise exercise = new Exercise(UUID.fromString(uuid), UUID.fromString(trainingUUID));
        exercise.setExerciseName(name);
        //exercise.setTrainingId(UUID.fromString(trainingUUID));
        exercise.setExerciseComments(comments);
        return exercise;
    }

    public Set getSet() {
        String uuid = getString(getColumnIndex(DatabaseScheme.SetTable.Columns.UUID));
        String exerciseUUID = getString(getColumnIndex(DatabaseScheme.SetTable.Columns.UUID_EXERCISE));
        String setNumer = getString(getColumnIndex(DatabaseScheme.SetTable.Columns.Set));
        String repetitions = getString(getColumnIndex(DatabaseScheme.SetTable.Columns.Repetitions));
        String weight = getString(getColumnIndex(DatabaseScheme.SetTable.Columns.Weight));

        Set set = new Set(UUID.fromString(uuid), UUID.fromString(exerciseUUID));
        set.setExerciseId(UUID.fromString(exerciseUUID));
        set.setSetNumber(Integer.parseInt(setNumer));
        set.setSetRepetitions(Integer.parseInt(repetitions));
        set.setSetWeight(Double.parseDouble(weight));
        return set;
    }

    public Running getRunning() {
        String uuid = getString(getColumnIndex(DatabaseScheme.RunningTable.Columns.UUID));
        String userUUID = getString(getColumnIndex(DatabaseScheme.RunningTable.Columns.UUID_USER));
        String name = getString(getColumnIndex(DatabaseScheme.RunningTable.Columns.Name));
        long date = getLong(getColumnIndex(DatabaseScheme.RunningTable.Columns.Date));
        double distance = getDouble(getColumnIndex(DatabaseScheme.RunningTable.Columns.Distance));
        double speed = getDouble(getColumnIndex(DatabaseScheme.RunningTable.Columns.Speed));
        double time = getDouble(getColumnIndex(DatabaseScheme.RunningTable.Columns.Time));
        double calories = getDouble(getColumnIndex(DatabaseScheme.RunningTable.Columns.Calories));
        String comments = getString(getColumnIndex(DatabaseScheme.RunningTable.Columns.Comments));

        Running running = new Running(UUID.fromString(uuid), Long.parseLong(userUUID));
        running.setId(UUID.fromString(uuid));
        running.setDate(new Date(date)); // Переработать
        running.setRunningName(name);
        running.setDistance(distance);
        running.setSpeed(speed);
        running.setTime(time);
        running.setCalories(calories);
        running.setComments(comments);

        return running;
    }
}
