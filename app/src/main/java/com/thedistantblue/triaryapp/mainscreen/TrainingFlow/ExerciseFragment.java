package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.dao.base.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.base.ExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.ExerciseFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.composite.ExerciseWithExerciseSet;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.utils.ActionEnum;
import com.thedistantblue.triaryapp.viewmodels.ExerciseViewModel;

public class ExerciseFragment extends Fragment {

    private static final String DATES_KEY = "dates";
    private static final String EXERCISE_KEY = "exercise";
    private static final String ACTION_CODE = "action";

    private ExerciseDao exerciseDao;
    private ExerciseSetDao exerciseSetDao;
    private ExerciseWithExerciseSetDao exerciseWithExerciseSetDao;
    private Exercise exercise;
    private Dates dates;

    private ActionEnum action;

    public static ExerciseFragment newInstance(Dates dates, Exercise exercise, ActionEnum action) {
        Bundle args = new Bundle();

        if (exercise != null) {args.putSerializable(EXERCISE_KEY, exercise);}
        else {args.putSerializable(EXERCISE_KEY, new Exercise(dates.getDatesUUID()));}
        args.putSerializable(ACTION_CODE, action);
        if (dates != null) {args.putSerializable(DATES_KEY, dates);}

        ExerciseFragment fragment = new ExerciseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDaos();
        dates = (Dates) getArguments().getSerializable(DATES_KEY);
        exercise = (Exercise) getArguments().getSerializable(EXERCISE_KEY);
        action = (ActionEnum) getArguments().getSerializable(ACTION_CODE);
    }

    private void initDaos() {
        exerciseDao = RoomDataBaseProvider.getDatabase(getActivity())
                                          .exerciseDao();
        exerciseSetDao = RoomDataBaseProvider.getDatabase(getActivity())
                                             .exerciseSetDao();
        exerciseWithExerciseSetDao = RoomDataBaseProvider.getDatabase(getActivity())
                                                         .exerciseWithExerciseSetDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ExerciseFragmentLayoutBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.exercise_fragment_layout, parent, false);

        final ExerciseViewModel exerciseViewModel = new ExerciseViewModel();
        ExerciseWithExerciseSet exerciseWithExerciseSet = exerciseWithExerciseSetDao.findById(exercise.getExerciseUUID().toString());
        exerciseViewModel.setExerciseWithExerciseSet(exerciseWithExerciseSet);
        exerciseViewModel.setExerciseDao(exerciseDao);
        exerciseViewModel.setExerciseSetDao(exerciseSetDao);
        exerciseViewModel.setExerciseWithExerciseSetDao(exerciseWithExerciseSetDao);
        exerciseViewModel.setAction(action);
        if (action.equals(ActionEnum.CREATE)) {
            exerciseViewModel.setEmptyExerciseSets();
        } else {
            ExerciseWithExerciseSet exerciseWithExerciseSetForDunnoWhat = exerciseWithExerciseSetDao.findById(exercise.getExerciseUUID().toString());
            exerciseViewModel.setExerciseSets(exerciseWithExerciseSetForDunnoWhat.getExerciseSetList());
        }
        binding.exerciseActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseViewModel.action();
                ((MainScreenActivityCallback) getActivity()).manageFragments(ExerciseListFragment.newInstance(dates), R.string.training_exercises_fragment_name);
            }
        });

        binding.setViewModel(exerciseViewModel);

        return binding.getRoot();
    }

}