package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.databinding.ExerciseItemCardBinding;
import com.thedistantblue.triaryapp.databinding.ExerciseListFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.Exercise;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivity;
import com.thedistantblue.triaryapp.viewmodels.ExerciseViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExerciseListFragment extends Fragment {
    private static final String TRAINING_KEY = "training";

    DAO dao;
    Training training;
    List<Exercise> exerciseList;

    public static ExerciseListFragment newInstance(Training training) {
        Bundle args = new Bundle();
        args.putSerializable(TRAINING_KEY, training);

        ExerciseListFragment fragment = new ExerciseListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = DAO.get(getActivity());
        training = (Training) getArguments().getSerializable(TRAINING_KEY);
        try {
            exerciseList = dao.getExercisesList(training);
            //exerciseList = training.getTrainingExercises();
        } catch (NullPointerException exc) {
            exerciseList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ExerciseListFragmentLayoutBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.exercise_list_fragment_layout, parent, false);

        binding.exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.exerciseRecyclerView.setAdapter(new ExerciseAdapter(exerciseList));
        binding.exerciseRecyclerView.getAdapter().notifyDataSetChanged();
        binding.exerciseAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "New exercise", Toast.LENGTH_SHORT).show();
                ((MainScreenActivity)getActivity())
                        .manageFragments(ExerciseFragment.newInstance(training, null, "create"));
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private class ExerciseHolder extends RecyclerView.ViewHolder {
        private ExerciseItemCardBinding exerciseItemCardBinding;

        public ExerciseHolder(ExerciseItemCardBinding exerciseItemCardBinding) {
            super(exerciseItemCardBinding.getRoot());
            this.exerciseItemCardBinding = exerciseItemCardBinding;
            this.exerciseItemCardBinding.setViewModel(new ExerciseViewModel());
        }

        public void bind(final Exercise exercise) {
            //Log.d("asd", "asd");
            /*
            Log.d("exercise id", exercise.getId().toString());
            Log.d("exercise name", exercise.getExerciseName());
            Log.d("exercise training id", exercise.getTrainingId().toString());
            Log.d("exercise comments", exercise.getExerciseComments());

            for (int i = 0; i < exercise.getExerciseSets().size(); i++) {
                Log.d("setlisttag", "setList(" + i + "): " + exercise.getExerciseSets().get(i).getSetNumber());
                Log.d("setlisttag", "setList(" + i + "): " + exercise.getExerciseSets().get(i).getSetRepetitions());
            }
            */
            this.exerciseItemCardBinding.getViewModel().setExercise(exercise);
            this.exerciseItemCardBinding.executePendingBindings();
            exerciseItemCardBinding.getViewModel().setExerciseSets(exercise.getExerciseSets());
            for (int i = 0; i < exercise.getExerciseSets().size(); i++) {
                Log.d("set number before clic ", "setList(" + i + "): " + exercise.getExerciseSets().get(i).getSetNumber());
                Log.d("set weight before clic", "setList(" + i + "): " + exercise.getExerciseSets().get(i).getSetWeight());
                Log.d("set reps before clic", "setList(" + i + "): " + exercise.getExerciseSets().get(i).getSetRepetitions());
            }

            this.exerciseItemCardBinding.exerciseCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Exercise details", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < exercise.getExerciseSets().size(); i++) {
                        Log.d("set number ", "setList(" + i + "): " + exercise.getExerciseSets().get(i).getSetNumber());
                        Log.d("set weight", "setList(" + i + "): " + exercise.getExerciseSets().get(i).getSetWeight());
                        Log.d("set reps", "setList(" + i + "): " + exercise.getExerciseSets().get(i).getSetRepetitions());
                    }
                    ((MainScreenActivity)getActivity())
                            .manageFragments(ExerciseFragment.newInstance(training, exercise, "update"));
                }
            });
        }
    }

    private class ExerciseAdapter extends RecyclerView.Adapter<ExerciseHolder> {
        List<Exercise> exerciseList;

        public ExerciseAdapter(List<Exercise> exerciseList) {
            this.exerciseList = exerciseList;
        }

        @Override
        public ExerciseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(getActivity());

             ExerciseItemCardBinding exerciseItemCardBinding =
                    DataBindingUtil.inflate(inflate, R.layout.exercise_item_card, parent, false);

            return new ExerciseHolder(exerciseItemCardBinding);
        }

        @Override
        public void onBindViewHolder(ExerciseHolder exerciseHolder, int position) {
            Exercise exercise = exerciseList.get(position);
            //Log.d("exercise id in adapter", exercise.getId().toString());
            exerciseHolder.bind(exercise);
        }

        @Override
        public int getItemCount() {
            return exerciseList.size();
        }
    }
}
