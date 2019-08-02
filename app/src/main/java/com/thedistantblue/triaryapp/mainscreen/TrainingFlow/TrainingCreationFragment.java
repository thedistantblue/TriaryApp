package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.databinding.TrainingCreationFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.entities.User;
import com.thedistantblue.triaryapp.viewmodels.TrainingViewModel;

public class TrainingCreationFragment extends Fragment {
    private static final String USER_KEY = "user";

    private DAO dao;
    private User user;
    private TrainingViewModel trainingViewModel;

    public static TrainingCreationFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);

        TrainingCreationFragment fragment = new TrainingCreationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.user = (User) getArguments().getSerializable(USER_KEY);
        dao = DAO.get(getActivity());
        trainingViewModel = new TrainingViewModel();
        trainingViewModel.setTraining(new Training(this.user.getId()));
        trainingViewModel.setDao(dao);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        TrainingCreationFragmentLayoutBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.training_creation_fragment_layout, parent, false);


        binding.setViewModel(trainingViewModel);

        return binding.getRoot();
    }

}
