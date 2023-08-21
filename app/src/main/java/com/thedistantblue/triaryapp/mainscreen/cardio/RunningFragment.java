package com.thedistantblue.triaryapp.mainscreen.cardio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.RunningDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.RunningFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.mainscreen.TitledFragment;
import com.thedistantblue.triaryapp.mainscreen.utils.DateFragment;
import com.thedistantblue.triaryapp.viewmodels.RunningViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import io.reactivex.rxjava3.disposables.Disposable;

public class RunningFragment extends TitledFragment {

    private static final int REQUEST_DATE = 0;
    private static final String DATE_DIALOG = "date";
    private static final String RUNNING_KEY = "running";
    private static final String IS_CREATE_KEY = "isCreate";

    private Running running;
    private Boolean isCreate;
    private RunningDao runningDao;
    private RunningViewModel runningViewModel;
    private RunningFragmentLayoutBinding binding;

    public static RunningFragment newInstance(@NotNull User user, @Nullable Running running) {
        Bundle args = new Bundle();
        boolean creationRequired = running == null;
        args.putSerializable(IS_CREATE_KEY, creationRequired);
        if (creationRequired) {
            running = new Running(user.getUserId());
        }
        args.putSerializable(RUNNING_KEY, running);

        RunningFragment fragment = new RunningFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getTitle() {
        return R.string.running_fragment_title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runningDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).runningDao();
        running = (Running) requireArguments().getSerializable(RUNNING_KEY);
        isCreate = (Boolean) requireArguments().getSerializable(IS_CREATE_KEY);
        runningViewModel = new RunningViewModel(running);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.running_fragment_layout, parent, false);
        binding.setViewModel(runningViewModel);

        binding.runningDateButton.setOnClickListener(v -> {
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            DateFragment date = DateFragment.getInstance(running.getDate());
            date.setTargetFragment(RunningFragment.this, REQUEST_DATE);
            date.show(fm, DATE_DIALOG);
        });

        if (isCreate) {
            initRunningOperateButton(R.string.create, R.string.running_created_toast,
                                     (r) -> runningDao.create(r).subscribe());
        } else {
            initRunningOperateButton(R.string.save, R.string.running_updated_toast,
                                     (r) -> runningDao.save(r).subscribe());
        }

        return binding.getRoot();
    }

    private void initRunningOperateButton(int buttonText, int toastText, Function<Running, Disposable> operation) {
        Running running = runningViewModel.getRunning();
        binding.runningOperateButton.setText(buttonText);
        binding.runningOperateButton.setOnClickListener(v -> {
            if (running.getRunningName() == null || running.getRunningName().isEmpty()) {
                Toast.makeText(getActivity(), R.string.enter_running_name_toast, Toast.LENGTH_SHORT).show();
            } else {
                withAutoDispose(operation.apply(running));
                Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            long date = (long) data.getSerializableExtra(DateFragment.EXTRA_DATE);
            runningViewModel.setRunningDate(date);
        }
    }
}