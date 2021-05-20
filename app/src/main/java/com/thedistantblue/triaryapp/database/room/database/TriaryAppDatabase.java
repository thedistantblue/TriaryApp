package com.thedistantblue.triaryapp.database.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.thedistantblue.triaryapp.database.room.dao.DatesDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.RunningDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.dao.UserDao;
import com.thedistantblue.triaryapp.entities.Dates;
import com.thedistantblue.triaryapp.entities.Exercise;
import com.thedistantblue.triaryapp.entities.ExerciseSet;
import com.thedistantblue.triaryapp.entities.Running;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.entities.User;

@Database(entities = { Dates.class,
                       Exercise.class,
                       ExerciseSet.class,
                       Running.class,
                       Training.class,
                       User.class },
          version = 1)

abstract class TriaryAppDatabase extends RoomDatabase {
    public abstract DatesDao datesDao();
    public abstract ExerciseDao exerciseDao();
    public abstract ExerciseSetDao exerciseSetDao();
    public abstract RunningDao runningDao();
    public abstract TrainingDao trainingDao();
    public abstract UserDao userDao();
}