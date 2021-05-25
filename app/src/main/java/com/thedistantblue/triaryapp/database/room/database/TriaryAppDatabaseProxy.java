package com.thedistantblue.triaryapp.database.room.database;

import com.thedistantblue.triaryapp.database.room.dao.DatesWithExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.TrainingWithDatesDao;
import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.dao.base.DatesDao;
import com.thedistantblue.triaryapp.database.room.dao.base.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.base.ExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.base.RunningDao;
import com.thedistantblue.triaryapp.database.room.dao.base.TrainingDao;
import com.thedistantblue.triaryapp.database.room.dao.base.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TriaryAppDatabaseProxy implements TriaryAppDatabaseInterface {
    private final TriaryAppDatabase triaryAppDatabase;

    @Override
    public DatesDao datesDao() {
        return triaryAppDatabase.datesDao();
    }

    @Override
    public ExerciseDao exerciseDao() {
        return triaryAppDatabase.exerciseDao();
    }

    @Override
    public ExerciseSetDao exerciseSetDao() {
        return triaryAppDatabase.exerciseSetDao();
    }

    @Override
    public RunningDao runningDao() {
        return triaryAppDatabase.runningDao();
    }

    @Override
    public TrainingDao trainingDao() {
        return triaryAppDatabase.trainingDao();
    }

    @Override
    public UserDao userDao() {
        return triaryAppDatabase.userDao();
    }

    @Override
    public UserWithTrainingAndRunningDao userWithTrainingAndRunningDao() {
        return triaryAppDatabase.userWithTrainingAndRunningDao();
    }

    @Override
    public TrainingWithDatesDao trainingWithDatesDao() {
        return triaryAppDatabase.trainingWithDatesDao();
    }

    @Override
    public DatesWithExerciseDao datesWithExerciseDao() {
        return triaryAppDatabase.datesWithExerciseDao();
    }

    @Override
    public ExerciseWithExerciseSetDao exerciseWithExerciseSetDao() {
        return triaryAppDatabase.exerciseWithExerciseSetDao();
    }
}