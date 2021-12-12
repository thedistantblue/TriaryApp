package com.thedistantblue.triaryapp.mainscreen.power;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.thedistantblue.triaryapp.R;

public class PowerTrainingCreationDialog extends DialogFragment implements View.OnClickListener {

    public static String TAG = "PowerTrainingCreationDialog";
    private TextInputEditText nameEditText;
    private TextInputEditText descriptionEditText;

    public PowerTrainingCreationDialog() {
        super(R.layout.training_list_training_creation_dialog_layout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(R.string.training_list_training_creation_dialog_message);
        View view = inflater.inflate(R.layout.training_list_training_creation_dialog_layout, null);
        view.findViewById(R.id.training_list_training_creation_dialog_create_button).setOnClickListener(this);
        view.findViewById(R.id.training_list_training_creation_dialog_cancel_button).setOnClickListener(this);
        nameEditText = view.findViewById(R.id.training_list_training_creation_dialog_name_input_edit_text);
        descriptionEditText = view.findViewById(R.id.training_list_training_creation_dialog_description_input_edit_text);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

}