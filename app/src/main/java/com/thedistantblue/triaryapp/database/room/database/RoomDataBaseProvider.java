package com.thedistantblue.triaryapp.database.room.database;

import android.content.Context;

import androidx.room.Room;

import lombok.experimental.UtilityClass;

@UtilityClass
class RoomDataBaseProvider {
    private static TriaryAppDatabase triaryAppDatabase;

    public static TriaryAppDatabase getDatabase(Context context) {
        if (triaryAppDatabase == null) {
            triaryAppDatabase = Room.databaseBuilder(context,
                                                     TriaryAppDatabase.class,
                                                     "triaryapp_database")
                                    .build();
        }
        return triaryAppDatabase;
    }
}