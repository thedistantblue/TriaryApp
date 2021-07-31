package com.thedistantblue.triaryapp.database.sqlite;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;

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
        User user = new User();
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
        String uuid = getString(getColumnIndex(DatabaseScheme.DateTable.Columns.UUID));
        long dates = getLong(getColumnIndex(DatabaseScheme.DateTable.Columns.Dates));

        Dates d = new Dates(UUID.fromString(uuid), UUID.fromString(trainingUUID));
        d.setDatesTrainingUUID(UUID.fromString(trainingUUID));
        d.setDatesDate(dates);

        return d;
    }

    public Exercise getExercise() {
        String uuid = getString(getColumnIndex(DatabaseScheme.ExerciseTable.Columns.UUID));
        String datesUUID = getString(getColumnIndex(DatabaseScheme.ExerciseTable.Columns.UUID_TRAINING));
        String name = getString(getColumnIndex(DatabaseScheme.ExerciseTable.Columns.Name));
        String comments = getString(getColumnIndex(DatabaseScheme.ExerciseTable.Columns.Comments));

        Exercise exercise = new Exercise(UUID.fromString(uuid), UUID.fromString(datesUUID));
        exercise.setExerciseName(name);
        exercise.setDatesId(UUID.fromString(datesUUID));
        exercise.setExerciseComments(comments);
        return exercise;
    }

    public ExerciseSet getSet() {
        String uuid = getString(getColumnIndex(DatabaseScheme.SetTable.Columns.UUID));
        String exerciseUUID = getString(getColumnIndex(DatabaseScheme.SetTable.Columns.UUID_EXERCISE));
        String setNumer = getString(getColumnIndex(DatabaseScheme.SetTable.Columns.Set));
        String repetitions = getString(getColumnIndex(DatabaseScheme.SetTable.Columns.Repetitions));
        String weight = getString(getColumnIndex(DatabaseScheme.SetTable.Columns.Weight));

        ExerciseSet exerciseSet = new ExerciseSet(UUID.fromString(uuid), UUID.fromString(exerciseUUID));
        exerciseSet.setExerciseId(UUID.fromString(exerciseUUID));
        exerciseSet.setNumber(Integer.parseInt(setNumer));
        exerciseSet.setRepetitions(Integer.parseInt(repetitions));
        exerciseSet.setWeight(Double.parseDouble(weight));
        return exerciseSet;
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
        running.setRunningUUID(UUID.fromString(uuid));
        running.setDate(date); // Переработать
        running.setRunningName(name);
        running.setDistance(distance);
        running.setSpeed(speed);
        running.setTime(time);
        running.setCalories(calories);
        running.setComments(comments);

        return running;
    }
}
