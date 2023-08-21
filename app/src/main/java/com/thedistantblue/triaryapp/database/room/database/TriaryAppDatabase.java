package com.thedistantblue.triaryapp.database.room.database;

import com.thedistantblue.triaryapp.database.room.dao.DayDao;
import com.thedistantblue.triaryapp.database.room.dao.PackDao;
import com.thedistantblue.triaryapp.database.room.dao.cross.DayExerciseCrossDao;
import com.thedistantblue.triaryapp.database.room.dao.cross.DayPackCrossDao;
import com.thedistantblue.triaryapp.database.room.dao.cross.ExercisePackCrossDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.details.ExerciseDetailsDao;
import com.thedistantblue.triaryapp.database.room.dao.TrainingWithDaysDao;
import com.thedistantblue.triaryapp.database.room.dao.details.PackDetailsDao;
import com.thedistantblue.triaryapp.database.room.dao.details.TrainingDetailsDao;
import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.RunningDao;
import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.dao.UserDao;

public interface TriaryAppDatabase {
    DayDao dayDao();
    ExerciseDao exerciseDao();
    ExerciseSetDao exerciseSetDao();
    PackDao packDao();
    RunningDao runningDao();
    TrainingDao trainingDao();
    UserDao userDao();

    UserWithTrainingAndRunningDao userWithTrainingAndRunningDao();
    TrainingWithDaysDao trainingWithDatesDao();
    TrainingDetailsDao trainingDetailsDao();
    ExerciseWithExerciseSetDao exerciseWithExerciseSetDao();
    ExerciseDetailsDao exerciseDetailsDao();
    PackDetailsDao packDetailsDao();

    ExercisePackCrossDao exercisePackCrossDao();
    DayExerciseCrossDao dateExerciseCrossDao();
    DayPackCrossDao datePackCrossDao();
}