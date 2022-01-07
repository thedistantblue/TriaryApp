package com.thedistantblue.triaryapp.database.room.database;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DatabaseConstants {
    public static final String USER_TABLE = "user_table";
    public static final String TRAINING_TABLE = "training_table";
    public static final String RUNNING_TABLE = "running_table";
    public static final String DATES_TABLE = "dates_table";
    public static final String EXERCISE_TABLE = "exercise_table";
    public static final String EXERCISE_SET_TABLE = "exercise_set_table";
    public static final String PACK_TABLE = "pack_table";

    // MANY-TO-MANY
    public static final String EXERCISE_WITH_PACKS_TABLE = "exercise_with_packs_table";
    public static final String DATES_WITH_EXERCISE_TABLE = "dates_with_exercise_table";
    public static final String DATES_WITH_PACKS_TABLE = "dates_with_packs_table";

    public static final String DATE_FORMAT = "dd:MM:yyyy";
}