package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.database.DatabaseCaller;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.TrainingCreationFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.utils.ActionEnum;
import com.thedistantblue.triaryapp.viewmodels.TrainingViewModel;

import java.util.Date;

public class TrainingCreationFragment extends Fragment {

    private static final String USER_KEY = "user";
    private static final int REQUEST_DATE = 0;
    private static final String DATE_DIALOG = "date";
    private static final String ACTION = "action";
    private static final String TRAINING_KEY = "training";

    private TrainingDao dao;
    private DatabaseCaller databaseCaller;
    private User user;
    Training training;
    private ActionEnum action;
    private TrainingViewModel trainingViewModel;

    public static TrainingCreationFragment newInstance(User user, Training training, ActionEnum action) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);
        args.putSerializable(ACTION, action);
        if (training != null) {
            args.putSerializable(TRAINING_KEY, training);
        } else {
            args.putSerializable(TRAINING_KEY, new Training(1));
        }

        TrainingCreationFragment fragment = new TrainingCreationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.user = (User) getArguments().getSerializable(USER_KEY);
        dao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).trainingDao();
        databaseCaller = RoomDataBaseProvider.getDatabaseCaller();
        action = (ActionEnum) getArguments().getSerializable(ACTION);
        training = (Training) getArguments().getSerializable(TRAINING_KEY);
        trainingViewModel = new TrainingViewModel();
        trainingViewModel.setTraining(training);
        trainingViewModel.setDao(dao);
        trainingViewModel.setDatabaseCaller(databaseCaller);
        trainingViewModel.setAction(action);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        TrainingCreationFragmentLayoutBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.training_creation_fragment_layout, parent, false);


        binding.setViewModel(trainingViewModel);
        /*
        binding.trainingCreationDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DateFragment date = DateFragment.getInstance(training.getTrainingDate());
                date.setTargetFragment(TrainingCreationFragment.this, REQUEST_DATE);
                date.show(fm, DATE_DIALOG);
            }
        });
        */
        binding.trainingCreationCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action.equals("create")) {
                    if (training.getTrainingName() == null || training.getTrainingName().equals("")) {
                        Toast.makeText(getActivity(), R.string.enter_training_name_toast, Toast.LENGTH_SHORT).show();
                    } else {
                        trainingViewModel.save();
                        Toast.makeText(getActivity(), R.string.training_created_toast, Toast.LENGTH_SHORT).show();
                        ((MainScreenActivityCallback) getActivity()).manageFragments(DatesListFragment.newInstance(training), R.string.training_dates_fragment_name);
                        // Будем запускать DatesListFragment
                    }

                } else {
                    if (training.getTrainingName().equals("")) {
                        Toast.makeText(getActivity(), R.string.enter_training_name_toast, Toast.LENGTH_SHORT).show();
                    } else {
                        trainingViewModel.action();
                        Toast.makeText(getActivity(), R.string.training_updated_toast, Toast.LENGTH_SHORT).show();
                        ((MainScreenActivityCallback) getActivity()).manageFragments(DatesListFragment.newInstance(training), R.string.training_dates_fragment_name);
                        // Будем запускать DatesListFragment
                        // Вот этот весь код здесь и в ВМ, связанный с апдейтом\сохранением,
                        // обязательно переделать,
                        // сейчас написано пиздец криво
                    }
                }
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
            //trainingViewModel.setTrainingDate(date);
        }
    }

}