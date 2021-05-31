package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.DatesDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.utils.FragmentSwitcher;
import com.thedistantblue.triaryapp.utils.TriaryDateFormat;

import java.util.Date;

public class DatesFragment extends Fragment {

    private static final int REQUEST_DATE = 0;
    private static final String DATE_DIALOG = "date";
    private static final String TRAINING_KEY = "training";
    private TextView dateTextView;
    private Button datesButton;
    private Button confirmButton;
    private Training training;
    private Dates dates;
    private DatesDao datesDao;

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
        initDaos();
        training = (Training) getArguments().getSerializable(TRAINING_KEY);
        dates = new Dates(training.getTrainingUUID());
        dates.setDatesTrainingUUID(training.getTrainingUUID());
    }

    private void initDaos() {
        datesDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                       .datesDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dates_layout, parent, false);

        dateTextView = view.findViewById(R.id.date_text_view);

        datesButton = view.findViewById(R.id.dates_button);
        datesButton.setOnClickListener(v -> {
            FragmentManager fm = getFragmentManager();
            DateFragment date = DateFragment.getInstance(new Date().getTime());
            date.setTargetFragment(DatesFragment.this, REQUEST_DATE);
            date.show(fm, DATE_DIALOG);
        });

        confirmButton = view.findViewById(R.id.confirm_button);
        datesDao.create(dates).subscribe(() -> {
            FragmentSwitcher.showFragment(this,
                                          DatesListFragment.newInstance(training),
                                          R.string.training_dates_fragment_name);
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
            dates.setDatesDate(date.getTime());
            dateTextView.setText(TriaryDateFormat.getFormattedDate(date.getTime()));
        }
    }
}