package com.thedistantblue.triaryapp.database.room.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoomDataBaseProvider {
    private static TriaryAppDatabase triaryAppDatabaseWithProxy;

    public static TriaryAppDatabase getDatabaseWithProxy(Context context) {
        if (triaryAppDatabaseWithProxy == null) {
            TriaryAppRoomDatabase database = Room.databaseBuilder(context,
                                                                  TriaryAppRoomDatabase.class,
                                                                  "triaryapp_v2_database_1")
                                                 .build();
            triaryAppDatabaseWithProxy = new TriaryAppDatabaseProxy(database);

            //int dataBaseVersion = database.getOpenHelper().getWritableDatabase().getVersion();
            //Log.d("DB_VERSION: ", String.valueOf(dataBaseVersion));
        }
        return triaryAppDatabaseWithProxy;
    }
}