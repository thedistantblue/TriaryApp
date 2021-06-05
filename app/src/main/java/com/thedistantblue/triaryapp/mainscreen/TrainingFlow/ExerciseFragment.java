package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.ExerciseFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;
import com.thedistantblue.triaryapp.entities.composite.ExerciseWithExerciseSet;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.utils.ActionEnum;
import com.thedistantblue.triaryapp.viewmodels.ExerciseViewModelNew;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import lombok.Data;
import lombok.SneakyThrows;

public class ExerciseFragment extends Fragment {

    private static final String EXERCISE_KEY = "exercise";
    private static final String EXERCISE_LIST_KEY = "exerciseList";

    private ExerciseFragmentLayoutBinding binding;
    private ExerciseViewModelNew exerciseViewModelNew;
    private ExerciseDao exerciseDao;
    private ExerciseSetDao exerciseSetDao;
    private Exercise exercise;
    private List<ExerciseSet> exerciseSetList;

    public static ExerciseFragment newInstance(Exercise exercise, ArrayList<ExerciseSet> exerciseSets) {
        Bundle args = new Bundle();
        args.putSerializable(EXERCISE_KEY, exercise);
        args.putParcelableArrayList(EXERCISE_LIST_KEY, exerciseSets);

        ExerciseFragment fragment = new ExerciseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDaos();
        exercise = (Exercise) getArguments().getSerializable(EXERCISE_KEY);
        exerciseSetList = getArguments().getParcelableArrayList(EXERCISE_LIST_KEY);
    }

    private void initDaos() {
        exerciseDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                          .exerciseDao();
        exerciseSetDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                             .exerciseSetDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.exercise_fragment_layout, parent, false);
        exerciseViewModelNew = new ExerciseViewModelNew(exercise, exerciseSetList, exerciseDao, exerciseSetDao);
        binding.setViewModel(exerciseViewModelNew);
        binding.notifyChange();
        return binding.getRoot();
    }

}