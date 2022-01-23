package com.thedistantblue.triaryapp.database.sqlite;

public class LocalDAO { //implements DAO {

/*    private static DAO mDao;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private LocalDAO(Context mContext) {
        this.mContext = mContext;
        mDatabase = new DatabaseHelper(this.mContext).getWritableDatabase();
    }

    private static ContentValues getUserContentValues(User user) {
        ContentValues cv = new ContentValues();
        //cv.put(DatabaseScheme.UserTable.Columns.UUID, user.getId().toString());
        cv.put(DatabaseScheme.UserTable.Columns.UUID, String.valueOf(user.getId()));
        cv.put(DatabaseScheme.UserTable.Columns.Name, user.getUserName());
        cv.put(DatabaseScheme.UserTable.Columns.Password, user.getUserPassword());
        return cv;
    }

    private static ContentValues getDatesContentValues(Day dates) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseScheme.DateTable.Columns.UUID_TRAINING, String.valueOf(dates.getDatesTrainingUUID()));
        cv.put(DatabaseScheme.DateTable.Columns.UUID, String.valueOf(dates.getDatesUUID()));
        cv.put(DatabaseScheme.DateTable.Columns.Day, dates.getDatesDate());
        return cv;
    }

    // Возможно, придется опять шаманить в датой
    private static ContentValues getTrainingContentValues(Training training) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseScheme.TrainingTable.Columns.UUID, training.getTrainingUUID().toString());
        cv.put(DatabaseScheme.TrainingTable.Columns.UUID_USER, String.valueOf(training.getUserId()));
        cv.put(DatabaseScheme.TrainingTable.Columns.Name, training.getTrainingName());
        //cv.put(DatabaseScheme.TrainingTable.Columns.Date, training.getTrainingDate());
        return cv;
    }

    private static ContentValues getExerciseContentValues(Exercise exercise) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseScheme.ExerciseTable.Columns.UUID, exercise.getExerciseUUID().toString());
        cv.put(DatabaseScheme.ExerciseTable.Columns.UUID_TRAINING, exercise.getDatesId().toString());
        cv.put(DatabaseScheme.ExerciseTable.Columns.Name, exercise.getExerciseName());
        cv.put(DatabaseScheme.ExerciseTable.Columns.Comments, exercise.getExerciseComments());
        cv.put(DatabaseScheme.ExerciseTable.Columns.Day, exercise.getDatesId().toString());
        return cv;
    }

    private static ContentValues getSetContentValues(ExerciseSet exerciseSet) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseScheme.SetTable.Columns.UUID, exerciseSet.getExerciseSetUUID().toString());
        cv.put(DatabaseScheme.SetTable.Columns.UUID_EXERCISE, exerciseSet.getExerciseId().toString());
        cv.put(DatabaseScheme.SetTable.Columns.Set, exerciseSet.getSetNumber());
        cv.put(DatabaseScheme.SetTable.Columns.Repetitions, exerciseSet.getSetRepetitions());
        cv.put(DatabaseScheme.SetTable.Columns.Weight, exerciseSet.getSetWeight());
        return cv;
    }

    // Возможно, придется опять шаманить с датой
    private static ContentValues getRunningContentValues(Running running) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseScheme.RunningTable.Columns.UUID, running.getRunningUUID().toString());
        cv.put(DatabaseScheme.RunningTable.Columns.UUID_USER, String.valueOf(running.getUserId()));
        cv.put(DatabaseScheme.RunningTable.Columns.Name, running.getRunningName());
        cv.put(DatabaseScheme.RunningTable.Columns.Date, String.valueOf(running.getDate().getTime()));
        cv.put(DatabaseScheme.RunningTable.Columns.Distance, running.getDistance());
        cv.put(DatabaseScheme.RunningTable.Columns.Speed, running.getSpeed());
        cv.put(DatabaseScheme.RunningTable.Columns.Time, running.getTime());
        cv.put(DatabaseScheme.RunningTable.Columns.Calories, running.getCalories());
        cv.put(DatabaseScheme.RunningTable.Columns.Comments, running.getComments());
        return cv;
    }

    public static DAO get(Context context) {
        if (mDao == null) {
            mDao = new LocalDAO(context);
        }
        return mDao;
    }

    @Override
    public void addUser(User user) {
        ContentValues cv = getUserContentValues(user);
        mDatabase.insert(DatabaseScheme.UserTable.NAME, null, cv);
    }

    @Override
    public void addTraining(Training training) {
        ContentValues cv = getTrainingContentValues(training);
        mDatabase.insert(DatabaseScheme.TrainingTable.NAME, null, cv);
    }

    @Override
    public void addDates(Day dates) {
        ContentValues cv = getDatesContentValues(dates);
        mDatabase.insert(DatabaseScheme.DateTable.NAME, null, cv);
    }

    @Override
    public void addExercise(Exercise exercise) {
        ContentValues cv = getExerciseContentValues(exercise);
        for (int i = 0; i < exercise.getExerciseExerciseSets().size(); i++) {
            addSet(exercise.getExerciseExerciseSets().get(i));
        }
        mDatabase.insert(DatabaseScheme.ExerciseTable.NAME, null, cv);
    }

    @Override
    public void addSet(ExerciseSet exerciseSet) {
        ContentValues cv = getSetContentValues(exerciseSet);
        mDatabase.insert(DatabaseScheme.SetTable.NAME, null, cv);
    }

    @Override
    public void addRunning(Running running) {
        ContentValues cv = getRunningContentValues(running);
        mDatabase.insert(DatabaseScheme.RunningTable.NAME, null, cv);
    }

    @Override
    public DataCursorWrapper queryData(String databasename, String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                databasename,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        return new DataCursorWrapper(cursor);
    }

    @Override
    public User getUser(User user) {
        User u;
        String userId = String.valueOf(user.getId());
        //String userId = user.getId().toString();
        DataCursorWrapper dcw = this.queryData(
                DatabaseScheme.UserTable.NAME,
                DatabaseScheme.UserTable.Columns.UUID + " =?",
                new String[] {userId});

        try {
            if (dcw.getCount() == 0) {
                return null;
            }
            dcw.moveToFirst();
            u = dcw.getUser();
        } finally {
            dcw.close();
        }
        u.setUserTrainings(getTrainingsList(u));
        return u;
    }

    // ВОЗМОЖНО НЕПРАВИЛЬНО!
    // Тренировка представляет из себя список упражнений, поэтому
    // при получении самой тренировки, вы должны и получить ассоциированный с ней
    // список упражнений.
    // Сделал так, как должно быть, но возможны ошибки. Перевроверить.
    // UPD: По-идее, этот метод вообще не нужен, т.к. все необходимое делается в
    // получении списка, а сама тренировка берется при помощи курсор враппера.
    @Override
    public Training getTraining(Training training) {
        Training t;
        String trainingId = training.getTrainingUUID().toString();
        DataCursorWrapper dcw = this.queryData(
                DatabaseScheme.TrainingTable.NAME,
                DatabaseScheme.TrainingTable.Columns.UUID + " =?",
                new String[] {trainingId}
        );

        try {
            if (dcw.getCount() == 0) {
                return null;
            }
            dcw.moveToFirst();
            t = dcw.getTraining();
        } finally {
            dcw.close();
        }

        //t.setTrainingExercises(this.getExercisesList(t));
        return t;
    }

    // ВНИМАНИЕ!
    // Пользователь сейчас ищется по пустому UUID, что захардкодено.
    // Как задать пустой UUID: UUID.fromString("");
    @Override
    public List<Training> getTrainingsList(User user) {
        String uuid = String.valueOf(user.getId());
        List<Training> trainingsList = new ArrayList<>();
        DataCursorWrapper dcw = this.queryData(
                DatabaseScheme.TrainingTable.NAME,
                DatabaseScheme.TrainingTable.Columns.UUID_USER + " =?",
                new String[] {uuid}
        );

        try {
            dcw.moveToFirst();
            while (!dcw.isAfterLast()) {
                Training t = dcw.getTraining();
                //t.setTrainingExercises(getExercisesList(t));
                t.setTrainingDates(getDates(t));
                trainingsList.add(t);
                dcw.moveToNext();
            }
        } finally {
            dcw.close();
        }

        return trainingsList;
    }

    // ВОЗМОЖНО НЕПРАВИЛЬНО!
    // Получая конкретное упражнение, мы должны также взять список сетов из
    // соответствующей таблицы.
    @Override
    public Exercise getExercise(Exercise exercise) {
        Exercise e;
        String uuid = exercise.getExerciseUUID().toString();
        DataCursorWrapper dcw = this.queryData(
                DatabaseScheme.ExerciseTable.NAME,
                DatabaseScheme.ExerciseTable.Columns.UUID + " =?",
                new String[] {uuid}
        );

        try {
            if (dcw.getCount() == 0) {
                return null;
            }
            dcw.moveToFirst();
            e = dcw.getExercise();
        } finally {
            dcw.close();
        }

        e.setExerciseExerciseSets(this.getSetsList(e));
        return e;
    }

    @Override
    public List<Exercise> getExercisesList(Day dates) {
        List<Exercise> exercisesList = new ArrayList<>();
        String uuid = dates.getDatesUUID().toString();
        DataCursorWrapper dcw = this.queryData(
                DatabaseScheme.ExerciseTable.NAME,
                DatabaseScheme.ExerciseTable.Columns.Day + " =?",
                new String[] {uuid}
        );

        try {
            dcw.moveToFirst();
            while (!dcw.isAfterLast()) {
                Exercise e = dcw.getExercise();
                //if (e.getDatesId() == dates.getDatesTrainingUUID()) {
                    e.setExerciseExerciseSets(getSetsList(e));
                    exercisesList.add(e);
                //}
                dcw.moveToNext();
            }
        } finally {
            dcw.close();
        }

        return exercisesList;
    }

    @Override
    public ExerciseSet getSet(Exercise exercise, int number) {
        String uuid = exercise.getExerciseUUID().toString();
        DataCursorWrapper dcw = this.queryData(
                DatabaseScheme.SetTable.NAME,
                DatabaseScheme.SetTable.Columns.UUID_EXERCISE + " =?",
                new String[] {uuid}
        );

        try {
            if (dcw.getCount() == 0) {
                return null;
            }
            dcw.moveToFirst();
            return dcw.getSet();
        } finally {
            dcw.close();
        }
    }

    // ВНИМАНИЕ!
    // Не надо ли получать и задавать каждый сет отдельно?
    // Получение написано выше.
    @Override
    public List<ExerciseSet> getSetsList(Exercise exercise) {
        String uuid = exercise.getExerciseUUID().toString();
        List<ExerciseSet> setsList = new ArrayList<>();
        DataCursorWrapper dcw = this.queryData(
                DatabaseScheme.SetTable.NAME,
                DatabaseScheme.SetTable.Columns.UUID_EXERCISE + " =?",
                new String[] {uuid}
        );

        try {
            dcw.moveToFirst();
            while (!dcw.isAfterLast()) {
                setsList.add(dcw.getSet());
                dcw.moveToNext();
            }
        } finally {
            dcw.close();
        }

        return setsList;
    }

    @Override
    public List<Day> getDates(Training training) {
        String uuid = String.valueOf(training.getTrainingUUID());
        List<Day> datesList = new ArrayList<>();
        DataCursorWrapper dcw = this.queryData(
                DatabaseScheme.DateTable.NAME,
                DatabaseScheme.DateTable.Columns.UUID_TRAINING + " =?",
                new String[] {uuid}
        );

        try {
            dcw.moveToFirst();
            while (!dcw.isAfterLast()) {
                Day dates = dcw.getDates();
                dates.setDatesExerciseList(getExercisesList(dates));
                datesList.add(dates);
                dcw.moveToNext();
            }
        } finally {
            dcw.close();
        }

        return datesList;
    }

    @Override
    public Running getRunning(Running running) {
        String uuid = running.getRunningUUID().toString();
        DataCursorWrapper dcw = this.queryData(
                DatabaseScheme.RunningTable.NAME,
                DatabaseScheme.RunningTable.Columns.UUID + " =?",
                new String[] {uuid}
        );

        try {
            if (dcw.getCount() == 0) {
                return null;
            }
            dcw.moveToFirst();
            return dcw.getRunning();
        } finally {
            dcw.close();
        }
    }

    @Override
    public List<Running> getRunningList(User user) {
        List<Running> runnings = new ArrayList<>();
        String uuid = String.valueOf(user.getId());
        DataCursorWrapper dcw = this.queryData(
                DatabaseScheme.RunningTable.NAME,
                DatabaseScheme.RunningTable.Columns.UUID_USER + " =?",
                new String[] {uuid}
                );

        try {
            dcw.moveToFirst();
            while(!dcw.isAfterLast()) {
                runnings.add(dcw.getRunning());
                dcw.moveToNext();
            }
        } finally {
            dcw.close();
        }

        return runnings;
    }

    // Обновляем тренировку
    @Override
    public void updateTraining(Training training) {
        String uuid = training.getTrainingUUID().toString();
        ContentValues cv = getTrainingContentValues(training);

        mDatabase.update(
                DatabaseScheme.TrainingTable.NAME,
                cv,
                DatabaseScheme.TrainingTable.Columns.UUID + " =?",
                new String[] {uuid}
        );
    }

    // Обновляем упражнение
    @Override
    public void updateExercise(Exercise exercise) {
        String uuid = exercise.getExerciseUUID().toString();
        ContentValues cv = getExerciseContentValues(exercise);
        for (int i = 0; i < exercise.getExerciseExerciseSets().size(); i++) {
            Log.d("updateExercise(): ", String.valueOf(exercise.getExerciseExerciseSets().get(i).getSetWeight()));
            updateSet(exercise.getExerciseExerciseSets().get(i));
        }

        mDatabase.update(
                DatabaseScheme.ExerciseTable.NAME,
                cv,
                DatabaseScheme.ExerciseTable.Columns.UUID + " =?",
                new String[] {uuid}
        );
    }

    // Обновляем сет
    @Override
    public void updateSet(ExerciseSet exerciseSet) {
        String uuid = exerciseSet.getExerciseSetUUID().toString();
        ContentValues cv = getSetContentValues(exerciseSet);

        Log.d("updateSet(): ", String.valueOf(exerciseSet.getSetWeight()));

        mDatabase.update(
                DatabaseScheme.SetTable.NAME,
                cv,
                DatabaseScheme.SetTable.Columns.UUID + " =?",
                new String[] {uuid}
        );
    }

    @Override
    public void updateRunning(Running running) {
        String uuid = running.getRunningUUID().toString();
        ContentValues cv = getRunningContentValues(running);

        mDatabase.update(
          DatabaseScheme.RunningTable.NAME,
          cv,
          DatabaseScheme.RunningTable.Columns.UUID + " =?",
          new String[] {uuid}
        );
    }

    // Удаляем пользователя
    @Override
    public void deleteUser(User user) {
        String uuid = String.valueOf(user.getId());
        //String uuid = user.getId().toString();

        mDatabase.delete(
                DatabaseScheme.UserTable.NAME,
                DatabaseScheme.UserTable.Columns.UUID + " =?",
                new String[] {uuid}
        );
    }

    // Удаляем тренировку и ассоциированные с ней упражнения и сеты
    // Пока закомментируем для простоты (работаю над датами для упражнений)

    @Override
    public void deleteTraining(Training training) {
        String uuid = training.getTrainingUUID().toString();

        mDatabase.delete(
                DatabaseScheme.TrainingTable.NAME,
                DatabaseScheme.TrainingTable.Columns.UUID + " =?",
                new String[] {uuid}
        );

        int exerciseListSize = training.getTrainingDates().size();
        for (int i = 0; i < exerciseListSize; i++) {
            mDatabase.delete(
                    DatabaseScheme.ExerciseTable.NAME,
                    DatabaseScheme.ExerciseTable.Columns.UUID + " =?",
                    new String[] {training.getTrainingDates().get(i).getDatesUUID().toString()}
            );

            int exListSize = training.getTrainingDates().get(i).getDatesExerciseList().size();

            for (int j = 0; j < exListSize; j++) {
                mDatabase.delete(
                        DatabaseScheme.SetTable.NAME,
                        DatabaseScheme.SetTable.Columns.UUID + " =?",
                        new String[] {training.getTrainingDates().get(i)
                                .getDatesExerciseList().get(j).getExerciseUUID().toString()}
                        // TODO: 24.02.2020 Дописать удаление сетов из упражнения
                        //error
                );

            }
        }
    }


    @Override
    public void deleteDate(Day dates) {
        String uuid = dates.getDatesUUID().toString();

        mDatabase.delete(
                DatabaseScheme.DateTable.NAME,
                DatabaseScheme.DateTable.Columns.UUID + " =?",
                new String[] {uuid}
        );

        int exListSize = dates.getDatesExerciseList().size();
        for (int i = 0; i < exListSize; i++) {
            mDatabase.delete(
                    DatabaseScheme.ExerciseTable.NAME,
                    DatabaseScheme.ExerciseTable.Columns.UUID + " =?",
                    new String[] {dates.getDatesExerciseList().get(i).getExerciseUUID().toString()}
            );
        }
    }

    // Удаляем упражнение из БД и ассоциированные с ним сеты
    @Override
    public void deleteExercise(Exercise exercise) {
        String uuid = exercise.getExerciseUUID().toString();

        mDatabase.delete(
                DatabaseScheme.ExerciseTable.NAME,
                DatabaseScheme.ExerciseTable.Columns.UUID + " =?",
                new String[] {uuid}
        );

        int setListSize = exercise.getExerciseExerciseSets().size();
        for (int i = 0; i < setListSize; i++) {
            mDatabase.delete(
                    DatabaseScheme.SetTable.NAME,
                    DatabaseScheme.SetTable.Columns.UUID + " =?",
                    new String[] {exercise.getExerciseExerciseSets().get(i).getExerciseSetUUID().toString()}
            );
        }
    }

    @Override
    public void deleteSet(ExerciseSet exerciseSet) {
        String uuid = exerciseSet.getExerciseSetUUID().toString();

        mDatabase.delete(
                DatabaseScheme.SetTable.NAME,
                DatabaseScheme.SetTable.Columns.UUID + " =?",
                new String[] {uuid}
        );
    }

    @Override
    public void deleteRunning(Running running) {
        String uuid = running.getRunningUUID().toString();

        mDatabase.delete(
                DatabaseScheme.RunningTable.NAME,
                DatabaseScheme.RunningTable.Columns.UUID + " =?",
                new String[] {uuid}
        );
    }*/
}