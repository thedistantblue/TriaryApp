package com.thedistantblue.triaryapp.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TriaryDateFormat {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy",
                                                                            Locale.getDefault());

    public static String getFormattedDate(long dateInMillis) {
        return dateFormat.format(dateInMillis);
    }
}