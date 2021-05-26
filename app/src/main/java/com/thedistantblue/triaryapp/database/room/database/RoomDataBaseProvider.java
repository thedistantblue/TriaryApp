package com.thedistantblue.triaryapp.database.room.database;

import android.content.Context;

import androidx.room.Room;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoomDataBaseProvider {
    private static TriaryAppDatabaseInterface triaryAppDatabaseWithProxy;

    public static TriaryAppDatabaseInterface getDatabaseWithProxy(Context context) {
        if (triaryAppDatabaseWithProxy == null) {
            TriaryAppDatabase database = Room.databaseBuilder(context,
                                                              TriaryAppDatabase.class,
                                                              "triaryapp_v2_database")
                                             .build();
            triaryAppDatabaseWithProxy = new TriaryAppDatabaseProxy(database);

        }
        return triaryAppDatabaseWithProxy;
    }
}