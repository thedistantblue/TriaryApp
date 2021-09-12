package com.thedistantblue.triaryapp.mainscreen.power;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.TrainingFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.mainscreen.TitledFragment;
import com.thedistantblue.triaryapp.viewmodels.TrainingViewModel;

import java.util.function.Function;

import io.reactivex.rxjava3.disposables.Disposable;

public class TrainingFragment extends TitledFragment {

    private static final String USER_KEY = "user";
    private static final String TRAINING_KEY = "training";
    private static final String IS_CREATE_KEY = "isCreate";

    private Boolean isCreate;
    private Training training;
    private TrainingDao trainingDao;
    private TrainingViewModel trainingViewModel;
    private TrainingFragmentLayoutBinding binding;

    public static TrainingFragment newInstance(@NonNull User user, @Nullable Training training) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);
        boolean creationRequired = training == null;
        args.putSerializable(IS_CREATE_KEY, creationRequired);
        if (creationRequired) {
            training = new Training(user.getUserID());
        }
        args.putSerializable(TRAINING_KEY, training);

        TrainingFragment fragment = new TrainingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getTitle() {
        return R.string.training_fragment_title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainingDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).trainingDao();
        training = (Training) requireArguments().getSerializable(TRAINING_KEY);
        isCreate = (Boolean) requireArguments().getSerializable(IS_CREATE_KEY);
        trainingViewModel = new TrainingViewModel(training);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.training_fragment_layout, parent, false);
        binding.setViewModel(trainingViewModel);

        if (isCreate) {
            initTrainingOperateButton(R.string.create, R.string.training_created_toast,
                                      (t) -> trainingDao.create(t).subscribe());
        } else {
            initTrainingOperateButton(R.string.save, R.string.training_updated_toast,
                                      (t) -> trainingDao.save(t).subscribe());
        }

        return binding.getRoot();
    }

    private void initTrainingOperateButton(int buttonText, int toastText, Function<Training, Disposable> operation) {
        Training training = trainingViewModel.getTraining();
        binding.trainingOperateButton.setText(buttonText);
        binding.trainingOperateButton.setOnClickListener(v -> {
            if (training.getTrainingName() == null || training.getTrainingName().isEmpty()) {
                Toast.makeText(getActivity(), R.string.enter_running_name_toast, Toast.LENGTH_SHORT).show();
            } else {
                withAutoDispose(operation.apply(training));
                Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT).show();
            }
        });
    }

}