package com.thedistantblue.triaryapp.database.room.database;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DatabaseConstants {
    public static final String USER_TABLE = "user_table";
    public static final String TRAINING_TABLE = "training_table";
    public static final String RUNNING_TABLE = "running_table";
    public static final String DAY_TABLE = "day_table";
    public static final String EXERCISE_TABLE = "exercise_table";
    public static final String EXERCISE_SET_TABLE = "exercise_set_table";
    public static final String PACK_TABLE = "pack_table";

    // MANY-TO-MANY
    public static final String EXERCISES_WITH_PACKS_TABLE = "exercises_with_packs_table";
    public static final String DAYS_WITH_EXERCISES_TABLE = "days_with_exercises_table";
    public static final String DAYS_WITH_PACKS_TABLE = "days_with_packs_table";

    public static final String DATE_FORMAT = "dd:MM:yyyy";
}