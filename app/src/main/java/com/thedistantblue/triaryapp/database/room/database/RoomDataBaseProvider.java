package com.thedistantblue.triaryapp.database.room.database;

import android.content.Context;

import androidx.room.Room;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoomDataBaseProvider {
    private static TriaryAppDatabaseInterface triaryAppDatabase;

    public static TriaryAppDatabaseInterface getDatabase(Context context) {
        if (triaryAppDatabase == null) {
            TriaryAppDatabase database = Room.databaseBuilder(context,
                                                              TriaryAppDatabase.class,
                                                              "triaryapp_v2_database")
                                             .build();
            triaryAppDatabase = new TriaryAppDatabaseProxy(database);

        }
        return triaryAppDatabase;
    }
}