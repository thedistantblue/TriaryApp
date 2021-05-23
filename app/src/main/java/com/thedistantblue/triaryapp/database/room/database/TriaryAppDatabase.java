package com.thedistantblue.triaryapp.database.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.thedistantblue.triaryapp.database.room.dao.DatesWithExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.TrainingWithDatesDao;
import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.dao.base.DatesDao;
import com.thedistantblue.triaryapp.database.room.dao.base.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.base.RunningDao;
import com.thedistantblue.triaryapp.database.room.dao.base.ExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.base.TrainingDao;
import com.thedistantblue.triaryapp.database.room.dao.base.UserDao;
import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.entities.composite.ExerciseWithExerciseSet;
import com.thedistantblue.triaryapp.entities.composite.UserWithTrainingAndRunning;

@Database(entities = { Dates.class,
                       Exercise.class,
                       ExerciseSet.class,
                       Running.class,
                       Training.class,
                       User.class },
          version = 1)
@TypeConverters(Converters.class)
public abstract class TriaryAppDatabase extends RoomDatabase {

    public abstract DatesDao datesDao();
    public abstract ExerciseDao exerciseDao();
    public abstract ExerciseSetDao exerciseSetDao();
    public abstract RunningDao runningDao();
    public abstract TrainingDao trainingDao();
    public abstract UserDao userDao();

    public abstract UserWithTrainingAndRunningDao userWithTrainingAndRunningDao();
    public abstract TrainingWithDatesDao trainingWithDatesDao();
    public abstract DatesWithExerciseDao datesWithExerciseDao();
    public abstract ExerciseWithExerciseSetDao exerciseWithExerciseSetDao();
}