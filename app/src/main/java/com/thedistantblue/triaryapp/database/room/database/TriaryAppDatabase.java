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

public interface TriaryAppDatabase {
    DatesDao datesDao();
    ExerciseDao exerciseDao();
    ExerciseSetDao exerciseSetDao();
    RunningDao runningDao();
    TrainingDao trainingDao();
    UserDao userDao();

    UserWithTrainingAndRunningDao userWithTrainingAndRunningDao();
    TrainingWithDatesDao trainingWithDatesDao();
    DatesWithExerciseDao datesWithExerciseDao();
    ExerciseWithExerciseSetDao exerciseWithExerciseSetDao();
}