package com.thedistantblue.triaryapp.entities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EntityConstants {
    public static final String USER_PRIMARY_KEY_FIELD_NAME = "_id";
    public static final String PRIMARY_KEY_FIELD_NAME = "id";
    public static final String UUID_FIELD_NAME = "name"; // for future to unify entity fields with id
    public static final String UUID_FIELD = "uuid";
    public static final String PARENT_UUID_FIELD = "parentUuid";

    // MANY-TO-MANY
    public static final String EXERCISE_ID = "exerciseId";
    public static final String PACK_ID = "packId";
    public static final String DATE_ID = "dateId";
}