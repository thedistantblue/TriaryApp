package com.thedistantblue.triaryapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "triaryBase";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_USER_TABLE =
            "create table " + DatabaseScheme.UserTable.NAME + "(" +
                    "_id integer primary key autoincrement, " +
                    DatabaseScheme.UserTable.Columns.UUID + ", " +
                    DatabaseScheme.UserTable.Columns.Name + ", " +
                    DatabaseScheme.UserTable.Columns.Password + ")";

    private static final String CREATE_TRAINING_TABLE =
            "create table " + DatabaseScheme.TrainingTable.NAME + "(" +
                    "_id integer primary key autoincrement, " +
                    DatabaseScheme.TrainingTable.Columns.UUID + ", " +
                    DatabaseScheme.TrainingTable.Columns.UUID_USER + ", " +
                    DatabaseScheme.TrainingTable.Columns.Name + ", " +
                    DatabaseScheme.TrainingTable.Columns.Date + ")";

    private static final String CREATE_EXERCISE_TABLE =
            "create table " + DatabaseScheme.ExerciseTable.NAME + "(" +
                    "_id integer primary key autoincrement, " +
                    DatabaseScheme.ExerciseTable.Columns.UUID + ", " +
                    DatabaseScheme.ExerciseTable.Columns.UUID_TRAINING + ", " +
                    DatabaseScheme.ExerciseTable.Columns.Name + ", " +
                    DatabaseScheme.ExerciseTable.Columns.Comments + ")";

    private static final String CREATE_SET_TABLE =
            "create table " + DatabaseScheme.SetTable.NAME + "(" +
                    "_id integer primary key autoincrement, " +
                    DatabaseScheme.SetTable.Columns.UUID + ", " +
                    DatabaseScheme.SetTable.Columns.UUID_EXERCISE + ", " +
                    DatabaseScheme.SetTable.Columns.Set + ", " +
                    DatabaseScheme.SetTable.Columns.Repetitions + ", " +
                    DatabaseScheme.SetTable.Columns.Weight + ")";

    private static final String CREATE_RUNNING_TABLE =
            "create table " + DatabaseScheme.RunningTable.NAME + "(" +
                    "_id integer primary key autoincrement, " +
                    DatabaseScheme.RunningTable.Columns.UUID + ", " +
                    DatabaseScheme.RunningTable.Columns.UUID_USER + ", " +
                    DatabaseScheme.RunningTable.Columns.Date + ", " +
                    DatabaseScheme.RunningTable.Columns.Distance + ", " +
                    DatabaseScheme.RunningTable.Columns.Speed + ", " +
                    DatabaseScheme.RunningTable.Columns.Time + ", " +
                    DatabaseScheme.RunningTable.Columns.Calories + ", " +
                    DatabaseScheme.RunningTable.Columns.Comments + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TRAINING_TABLE);
        db.execSQL(CREATE_EXERCISE_TABLE);
        db.execSQL(CREATE_SET_TABLE);
        db.execSQL(CREATE_RUNNING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}