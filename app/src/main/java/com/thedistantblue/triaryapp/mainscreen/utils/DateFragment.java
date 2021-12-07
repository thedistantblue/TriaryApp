package com.thedistantblue.triaryapp.mainscreen.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import com.thedistantblue.triaryapp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFragment extends DialogFragment {
    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "extra_date";
    private DatePicker mDatePicker;

    public static DateFragment getInstance(long dateLong) {
        Bundle args = new Bundle();
        Date date = new Date(dateLong);
        args.putSerializable(ARG_DATE, date);

        DateFragment fragment = new DateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void sendResult(int resultCode, Date date) {

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.date_picker, null);

        mDatePicker = v.findViewById(R.id.date_picker);
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_chooser_title)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    Date date1 = new GregorianCalendar(mDatePicker.getYear(),
                                                       mDatePicker.getMonth(),
                                                       mDatePicker.getDayOfMonth())
                                 .getTime();
                    sendResult(Activity.RESULT_OK, date1);
                })
                .create();
    }
}