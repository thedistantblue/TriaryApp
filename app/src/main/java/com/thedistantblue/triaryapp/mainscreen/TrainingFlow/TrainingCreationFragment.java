package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.databinding.TrainingCreationFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.entities.User;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivity;
import com.thedistantblue.triaryapp.viewmodels.TrainingViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainingCreationFragment extends Fragment {
    private static final String USER_KEY = "user";
    private static final int REQUEST_DATE = 0;
    private static final String DATE_DIALOG = "date";
    private static final String ACTION_STRING = "action";
    private static final String TRAINING_KEY = "training";

    private DAO dao;
    private User user;
    Training training;
    private String actionString;
    private TrainingViewModel trainingViewModel;

    public static TrainingCreationFragment newInstance(User user, Training training, String actionString) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);
        args.putSerializable(ACTION_STRING, actionString);
        if (training != null) {
            args.putSerializable(TRAINING_KEY, training);
        } else {
            args.putSerializable(TRAINING_KEY, new Training(user.getId()));
        }

        TrainingCreationFragment fragment = new TrainingCreationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.user = (User) getArguments().getSerializable(USER_KEY);
        dao = DAO.get(getActivity());
        actionString = (String)getArguments().getSerializable(ACTION_STRING);
        training = (Training) getArguments().getSerializable(TRAINING_KEY);
        trainingViewModel = new TrainingViewModel();
        trainingViewModel.setTraining(training);
        trainingViewModel.setDao(dao);
        trainingViewModel.setActionString(actionString);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        TrainingCreationFragmentLayoutBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.training_creation_fragment_layout, parent, false);


        binding.setViewModel(trainingViewModel);
        binding.trainingCreationDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DateFragment date = DateFragment.getInstance(training.getTrainingDate());
                date.setTargetFragment(TrainingCreationFragment.this, REQUEST_DATE);
                date.show(fm, DATE_DIALOG);
            }
        });
        binding.trainingCreationCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionString.equals("create")) {
                    trainingViewModel.save();
                } else {
                    trainingViewModel.action(); // Вот этот весь код здесь и в ВМ, связанный с апдейтом\сохранением,
                                                // обязательно переделать,
                                                // сейчас написано пиздец криво
                }
                ((MainScreenActivity) getActivity()).manageFragments(ExerciseListFragment.newInstance(training), "Training exercises");
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DateFragment.EXTRA_DATE);
            trainingViewModel.setTrainingDate(date);
        }
    }

}
