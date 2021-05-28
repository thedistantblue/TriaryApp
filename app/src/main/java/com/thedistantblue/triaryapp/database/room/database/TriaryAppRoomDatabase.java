package com.thedistantblue.triaryapp.database.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;

@Database(entities = { Dates.class,
                       Exercise.class,
                       ExerciseSet.class,
                       Running.class,
                       Training.class,
                       User.class },
          version = 1)
@TypeConverters(Converters.class)
public abstract class TriaryAppRoomDatabase extends RoomDatabase implements TriaryAppDatabase {
}