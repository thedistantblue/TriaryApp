package com.thedistantblue.triaryapp.mainscreen.cardio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.RunningDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.RunningCreationFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.mainscreen.TitledFragment;
import com.thedistantblue.triaryapp.mainscreen.utils.DateFragment;
import com.thedistantblue.triaryapp.utils.ActionEnum;
import com.thedistantblue.triaryapp.viewmodels.RunningViewModel;

public class RunningCreationFragment extends TitledFragment {

    private static final String USER_KEY = "user";
    private static final String RUNNING_KEY = "running";
    private static final String ACTION_KEY = "action";
    private static final int REQUEST_DATE = 0;
    private static final String DATE_DIALOG = "date";

    private RunningDao runningDao;
    private User user;
    private Running running;
    private ActionEnum action;
    private RunningViewModel runningViewModel;

    public static RunningCreationFragment newInstance(User user, @NonNull Running running, ActionEnum action) {
        Bundle args = new Bundle();
        args.putSerializable(RUNNING_KEY, running);
        args.putSerializable(USER_KEY, user);
        args.putSerializable(ACTION_KEY, action);

        RunningCreationFragment fragment = new RunningCreationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getTitle() {
        return R.string.create_running_fragment_name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runningDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                         .runningDao();
        user = (User) getArguments().getSerializable(USER_KEY);
        running = (Running) getArguments().getSerializable(RUNNING_KEY);
        action = (ActionEnum) getArguments().getSerializable(ACTION_KEY);
        runningViewModel = new RunningViewModel();
        runningViewModel.setRunningDao(runningDao);
        runningViewModel.setAutoDisposableFragment(this);
        runningViewModel.setRunning(running);
        runningViewModel.setAction(action);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        RunningCreationFragmentLayoutBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.running_creation_fragment_layout, parent, false);

        binding.setViewModel(runningViewModel);

        binding.runningDateButton.setOnClickListener(v -> {
            FragmentManager fm = getFragmentManager();
            DateFragment date = DateFragment.getInstance(running.getDate());
            date.setTargetFragment(RunningCreationFragment.this, REQUEST_DATE);
            date.show(fm, DATE_DIALOG);
        });

        binding.runningCreateButton.setOnClickListener(v -> {
            if (action.equals(ActionEnum.CREATE)) {
                if (running.getRunningName() == null || running.getRunningName().equals("")) {
                    Toast.makeText(getActivity(), R.string.enter_running_name_toast, Toast.LENGTH_SHORT).show();
                } else {
                    runningViewModel.save();
                    Toast.makeText(getActivity(), R.string.running_created_toast, Toast.LENGTH_SHORT).show();
                    ((MainScreenActivityCallback) getActivity()).switchFragment(RunningListFragment.newInstance(user));
                }
            } else {
                runningViewModel.update();
                Toast.makeText(getActivity(), R.string.running_updated_toast, Toast.LENGTH_SHORT).show();
                ((MainScreenActivityCallback) getActivity()).switchFragment(RunningListFragment.newInstance(user));
            }
        });

        return binding.getRoot();
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