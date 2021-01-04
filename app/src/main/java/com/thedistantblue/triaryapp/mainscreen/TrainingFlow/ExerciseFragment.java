package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.databinding.ExerciseFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.Dates;
import com.thedistantblue.triaryapp.entities.Exercise;
import com.thedistantblue.triaryapp.entities.Set;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivity;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.viewmodels.ExerciseViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExerciseFragment extends Fragment {

    private static final String DATES_KEY = "dates";
    private static final String EXERCISE_KEY = "exercise";
    private static final String ACTION_CODE = "action";

    private DAO dao;
    private Exercise exercise;
    private Dates dates;
    private List<Set> setList;

    private String action = "";

    public static ExerciseFragment newInstance(Dates dates, Exercise exercise, String action) {
        Bundle args = new Bundle();

        if (exercise != null) {args.putSerializable(EXERCISE_KEY, exercise);}
        else {args.putSerializable(EXERCISE_KEY, new Exercise(dates.getId()));}
        args.putSerializable(ACTION_CODE, action);
        if (dates != null) {args.putSerializable(DATES_KEY, dates);}

        ExerciseFragment fragment = new ExerciseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = DAO.get(getActivity());
        dates = (Dates) getArguments().getSerializable(DATES_KEY);
        exercise = (Exercise) getArguments().getSerializable(EXERCISE_KEY);
        action = (String) getArguments().getSerializable(ACTION_CODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ExerciseFragmentLayoutBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.exercise_fragment_layout, parent, false);

        final ExerciseViewModel exerciseViewModel = new ExerciseViewModel();
        exerciseViewModel.setExercise(exercise);
        exerciseViewModel.setDao(dao);
        exerciseViewModel.setActionString(action);
        if (action.equals("create")) {
            exerciseViewModel.setEmptyExerciseSets();
        } else {
            exerciseViewModel.setExerciseSets(exercise.getExerciseSets());
        }
        binding.exerciseActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseViewModel.action();
                Toast.makeText(getActivity(), "Exercise " + action + "!", Toast.LENGTH_SHORT).show();
                ((MainScreenActivityCallback) getActivity()).manageFragments(ExerciseListFragment.newInstance(dates), R.string.training_exercises_fragment_name);
            }
        });

        binding.setViewModel(exerciseViewModel);

        return binding.getRoot();
    }

}