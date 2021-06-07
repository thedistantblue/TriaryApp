package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.ExerciseFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;
import com.thedistantblue.triaryapp.mainscreen.TitledFragment;
import com.thedistantblue.triaryapp.viewmodels.ExerciseViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExerciseFragment extends TitledFragment {

    private static final String EXERCISE_KEY = "exercise";
    private static final String EXERCISE_LIST_KEY = "exerciseList";

    private ExerciseFragmentLayoutBinding binding;
    private ExerciseViewModel exerciseViewModel;
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
    public int getTitle() {
        return R.string.create_exercise_fragment_name;
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
        exerciseViewModel = new ExerciseViewModel(exercise, exerciseSetList, exerciseDao, exerciseSetDao);
        binding.setViewModel(exerciseViewModel);
        binding.notifyChange();
        return binding.getRoot();
    }

}