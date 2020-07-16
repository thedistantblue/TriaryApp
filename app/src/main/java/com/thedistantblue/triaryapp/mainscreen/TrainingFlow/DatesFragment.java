package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.database.DatabaseScheme;
import com.thedistantblue.triaryapp.entities.Dates;
import com.thedistantblue.triaryapp.entities.Training;

import java.util.Date;

public class DatesFragment extends Fragment {
    private static final int REQUEST_DATE = 0;
    private static final String DATE_DIALOG = "date";
    private static final String TRAINING_KEY = "training";
    private Button datesButton;
    private Training training;
    private Dates dates;
    private DAO dao;

    public static DatesFragment newInstance(Training training) {
        Bundle args = new Bundle();
        args.putSerializable(TRAINING_KEY, training);

        DatesFragment fragment = new DatesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        training = (Training) getArguments().getSerializable(TRAINING_KEY);
        dao = DAO.get(getActivity());
        dates = new Dates(training.getId());
        dates.setDatesTrainingUUID(training.getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dates_layout, parent, false);

        datesButton = view.findViewById(R.id.dates_button);
        datesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DateFragment date = DateFragment.getInstance(new Date().getTime());
                date.setTargetFragment(DatesFragment.this, REQUEST_DATE);
                date.show(fm, DATE_DIALOG);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DateFragment.EXTRA_DATE);
            /**
             * Надо сохранять дату и id тренировки в БД
             */
            dates.setDatesDate(date.getTime());
            dao.addDates(dates);
        }
    }
}
