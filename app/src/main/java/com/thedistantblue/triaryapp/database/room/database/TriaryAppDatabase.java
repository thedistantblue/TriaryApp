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

public interface TriaryAppDatabase {
    DatesDao datesDao();
    ExerciseDao exerciseDao();
    ExerciseSetDao exerciseSetDao();
    RunningDao runningDao();
    TrainingDao trainingDao();
    UserDao userDao();

    UserWithTrainingAndRunningDao userWithTrainingAndRunningDao();
    TrainingWithDatesDao trainingWithDatesDao();
    TrainingDetailsDao trainingDetailsDao();
    DatesWithExerciseDao datesWithExerciseDao();
    ExerciseWithExerciseSetDao exerciseWithExerciseSetDao();
    ExerciseDetailsDao exerciseDetailsDao();

    ExercisePackCrossDao exercisePackCrossDao();
    DateExerciseCrossDao dateExerciseCrossDao();
    DatePackCrossDao datePackCrossDao();
}