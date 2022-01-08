package com.thedistantblue.triaryapp.database.room.database;

import com.thedistantblue.triaryapp.database.room.dao.DateExerciseCrossDao;
import com.thedistantblue.triaryapp.database.room.dao.DatePackCrossDao;
import com.thedistantblue.triaryapp.database.room.dao.DatesWithExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExercisePackCrossDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao;
import com.thedistantblue.triaryapp.database.room.dao.TrainingWithDatesDao;
import com.thedistantblue.triaryapp.database.room.dao.TrainingDetailsDao;
import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.dao.DatesDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.RunningDao;
import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.dao.UserDao;
import com.thedistantblue.triaryapp.database.room.database.proxy.DateExerciseCrossDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.DatePackCrossDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.DatesWithExerciseDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.ExercisePackCrossDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.ExerciseWithExerciseSetDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.ExerciseDetailsDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.TrainingWithDatesDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.TrainingDetailsDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.UserWithTrainingAndRunningDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.base.DatesDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.base.ExerciseDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.base.ExerciseSetDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.base.RunningDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.base.TrainingDaoProxy;
import com.thedistantblue.triaryapp.database.room.database.proxy.base.UserDaoProxy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TriaryAppDatabaseProxy implements TriaryAppDatabase {
    private final TriaryAppRoomDatabase triaryAppDatabase;

    @Override
    public DatesDao datesDao() {
        return new DatesDaoProxy(triaryAppDatabase.datesDao());
    }

    @Override
    public ExerciseDao exerciseDao() {
        return new ExerciseDaoProxy(triaryAppDatabase.exerciseDao());
    }

    @Override
    public ExerciseSetDao exerciseSetDao() {
        return new ExerciseSetDaoProxy(triaryAppDatabase.exerciseSetDao());
    }

    @Override
    public RunningDao runningDao() {
        return new RunningDaoProxy(triaryAppDatabase.runningDao());
    }

    @Override
    public TrainingDao trainingDao() {
        return new TrainingDaoProxy(triaryAppDatabase.trainingDao());
    }

    @Override
    public UserDao userDao() {
        return new UserDaoProxy(triaryAppDatabase.userDao());
    }

    @Override
    public UserWithTrainingAndRunningDao userWithTrainingAndRunningDao() {
        return new UserWithTrainingAndRunningDaoProxy(triaryAppDatabase.userWithTrainingAndRunningDao());
    }

    @Override
    public TrainingWithDatesDao trainingWithDatesDao() {
        return new TrainingWithDatesDaoProxy(triaryAppDatabase.trainingWithDatesDao());
    }

    @Override
    public TrainingDetailsDao trainingDetailsDao() {
        return new TrainingDetailsDaoProxy(triaryAppDatabase.trainingDetailsDao());
    }

    @Override
    public DatesWithExerciseDao datesWithExerciseDao() {
        return new DatesWithExerciseDaoProxy(triaryAppDatabase.datesWithExerciseDao());
    }

    @Override
    public ExerciseWithExerciseSetDao exerciseWithExerciseSetDao() {
        return new ExerciseWithExerciseSetDaoProxy(triaryAppDatabase.exerciseWithExerciseSetDao());
    }

    @Override
    public ExerciseDetailsDao exerciseDetailsDao() {
        return new ExerciseDetailsDaoProxy(triaryAppDatabase.exerciseDetailsDao());
    }

    @Override
    public ExercisePackCrossDao exercisePackCrossDao() {
        return new ExercisePackCrossDaoProxy(triaryAppDatabase.exercisePackCrossDao());
    }

    @Override
    public DateExerciseCrossDao dateExerciseCrossDao() {
        return new DateExerciseCrossDaoProxy(triaryAppDatabase.dateExerciseCrossDao());
    }

    @Override
    public DatePackCrossDao datePackCrossDao() {
        return new DatePackCrossDaoProxy(triaryAppDatabase.datePackCrossDao());
    }
}