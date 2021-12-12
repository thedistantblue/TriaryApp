package com.thedistantblue.triaryapp.mainscreen.power.dialog;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.entities.base.Training;

import io.reactivex.rxjava3.disposables.Disposable;

public class PowerTrainingCreationDialog extends DialogFragment {

    public static String TAG = "PowerTrainingCreationDialog";

    private final int userId;
    private final Fragment parentFragment;
    private final TrainingDao trainingDao;

    private Disposable disposable;
    private TextInputEditText nameEditText;
    private TextInputEditText descriptionEditText;

    public PowerTrainingCreationDialog(Fragment parentFragment, TrainingDao trainingDao, int userId) {
        super(R.layout.training_list_training_creation_dialog_layout);
        this.userId = userId;
        this.parentFragment = parentFragment;
        this.trainingDao = trainingDao;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_list_training_creation_dialog_layout, null);
        view.findViewById(R.id.training_list_training_creation_dialog_create_button).setOnClickListener(this::create);
        view.findViewById(R.id.training_list_training_creation_dialog_cancel_button).setOnClickListener(this::cancel);
        nameEditText = view.findViewById(R.id.training_list_training_creation_dialog_name_input_edit_text);
        descriptionEditText = view.findViewById(R.id.training_list_training_creation_dialog_description_input_edit_text);
        return view;
    }

    private void create(View ignored) {
        Training training = createTrainingAndSetValues();
        disposable = trainingDao.create(training)
                                .subscribe(this::trainingCreated);
    }

    private Training createTrainingAndSetValues() {
        Training training = new Training(userId);
        Editable nameEditable = nameEditText.getText();
        if (nameEditable != null) {
            training.setTrainingName(nameEditable.toString());
        }
        /*
        Editable descriptionEditable = descriptionEditText.getText();
        if (descriptionEditable != null) {
            training.setTrainingDescription(descriptionEditable.toString());
        }
        */
        return training;
    }

    private void trainingCreated() {
        parentFragment.onResume();
        dismiss();
    }

    private void cancel(View ignored) {
        dismiss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        disposable.dispose();
    }
}