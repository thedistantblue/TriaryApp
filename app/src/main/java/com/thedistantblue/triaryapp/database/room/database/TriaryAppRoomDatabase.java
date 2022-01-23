package com.thedistantblue.triaryapp.database.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.thedistantblue.triaryapp.entities.base.Day;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;
import com.thedistantblue.triaryapp.entities.base.Pack;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.entities.cross.DayExerciseCrossRef;
import com.thedistantblue.triaryapp.entities.cross.DayPackCrossRef;
import com.thedistantblue.triaryapp.entities.cross.ExercisePackCrossRef;

@Database(entities = { Day.class,
                       Exercise.class,
                       ExerciseSet.class,
                       Pack.class,
                       Running.class,
                       Training.class,
                       User.class,
                       ExercisePackCrossRef.class,
                       DayExerciseCrossRef.class,
                       DayPackCrossRef.class },
          version = 1)
@TypeConverters(Converters.class)
public abstract class TriaryAppRoomDatabase extends RoomDatabase implements TriaryAppDatabase {
}