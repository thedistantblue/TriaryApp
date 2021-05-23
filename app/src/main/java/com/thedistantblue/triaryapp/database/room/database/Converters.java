package com.thedistantblue.triaryapp.database.room.database;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Converters {
    @TypeConverter
    public static UUID uuidFromString(String uuidString) {
        return UUID.fromString(uuidString);
    }

    @TypeConverter
    public static String uuidToString(UUID uuid) {
        return uuid.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static Date dateFromString(String dateString) {
        return Date.from(LocalDateTime.parse(dateString)
                                      .atZone(ZoneId.systemDefault())
                                      .toInstant());
    }

    @TypeConverter
    public static String dateToString(Date date) {
        return new SimpleDateFormat(DatabaseConstants.DATE_FORMAT, Locale.getDefault()).format(date);
    }
}