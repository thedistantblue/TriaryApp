package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.TrainingCreationFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.mainscreen.TitledFragment;
import com.thedistantblue.triaryapp.utils.ActionEnum;
import com.thedistantblue.triaryapp.viewmodels.TrainingViewModel;

public class TrainingCreationFragment extends TitledFragment {

    private static final String USER_KEY = "user";
    private static final String TRAINING_KEY = "training";

    private TrainingDao trainingDao;
    Training training;
    private TrainingViewModel trainingViewModel;

    public static TrainingCreationFragment newInstance(User user, Training training) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);
        args.putSerializable(TRAINING_KEY, training);

        TrainingCreationFragment fragment = new TrainingCreationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getTitle() {
        return R.string.training_settings_fragment_name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainingDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).trainingDao();
        training = (Training) getArguments().getSerializable(TRAINING_KEY);
        trainingViewModel = new TrainingViewModel(training, trainingDao);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        TrainingCreationFragmentLayoutBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.training_creation_fragment_layout, parent, false);

        binding.setViewModel(trainingViewModel);

        // TODO перенести в TrainingViewModel.save()
        //binding.trainingCreationCreate.setOnClickListener(v -> {
        //    ((MainScreenActivityCallback) getActivity()).manageFragments(DatesListFragment.newInstance(training), R.string.training_dates_fragment_name);
        //});

        return binding.getRoot();
    }

}