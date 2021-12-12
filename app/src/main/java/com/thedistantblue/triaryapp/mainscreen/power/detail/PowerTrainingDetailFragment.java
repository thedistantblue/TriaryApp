package com.thedistantblue.triaryapp.mainscreen.power.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.thedistantblue.triaryapp.R;

public class PowerTrainingDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_detail_layout, container, false);
        view.findViewById(R.id.training_detail_exercise_list).setOnClickListener(this::navigateToExerciseList);
        view.findViewById(R.id.training_detail_exercise_pack_list).setOnClickListener(this::navigateToExercisePackList);
        view.findViewById(R.id.training_detail_dates_list).setOnClickListener(this::navigateToDateList);
        return view;
    }

    private void navigateToExerciseList(View ignored) {

    }

    private void navigateToExercisePackList(View ignored) {

    }

    private void navigateToDateList(View ignored) {

    }

}