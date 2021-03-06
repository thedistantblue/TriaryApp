package com.thedistantblue.triaryapp.mainscreen.RunningFlow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.databinding.RunningCreationFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.Running;
import com.thedistantblue.triaryapp.entities.User;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.mainscreen.RunningFragment;
import com.thedistantblue.triaryapp.utils.ActionEnum;
import com.thedistantblue.triaryapp.viewmodels.RunningViewModel;

import java.util.Date;

public class RunningCreationFragment extends Fragment {

    private static final String USER_KEY = "user";
    private static final String RUNNING_KEY = "running";
    private static final String ACTION_KEY = "action";
    private static final int REQUEST_DATE = 0;
    private static final String DATE_DIALOG = "date";

    private DAO dao;
    private User user;
    private Running running;
    private ActionEnum action;
    private RunningViewModel runningViewModel;

    public static RunningCreationFragment newInstance(User user, Running running, ActionEnum action) {
        Bundle args = new Bundle();

        if (running != null) {
            args.putSerializable(RUNNING_KEY, running);
        } else {
            args.putSerializable(RUNNING_KEY, new Running(user.getId()));
        }
        args.putSerializable(USER_KEY, user);
        args.putSerializable(ACTION_KEY, action);

        RunningCreationFragment fragment = new RunningCreationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = DAO.get(getActivity());
        user = (User) getArguments().getSerializable(USER_KEY);
        running = (Running) getArguments().getSerializable(RUNNING_KEY);
        action = (ActionEnum) getArguments().getSerializable(ACTION_KEY);
        runningViewModel = new RunningViewModel();
        runningViewModel.setDao(dao);
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
            if (action.equals("create")) {
                if (running.getRunningName() == null || running.getRunningName().equals("")) {
                    Toast.makeText(getActivity(), R.string.enter_running_name_toast, Toast.LENGTH_SHORT).show();
                } else {
                    runningViewModel.save();
                    Toast.makeText(getActivity(), R.string.running_created_toast, Toast.LENGTH_SHORT).show();
                    ((MainScreenActivityCallback) getActivity()).manageFragments(RunningFragment.newInstance(user), R.string.running_tab_button);
                }
            } else {
                runningViewModel.update();
                Toast.makeText(getActivity(), R.string.running_updated_toast, Toast.LENGTH_SHORT).show();
                ((MainScreenActivityCallback) getActivity()).manageFragments(RunningFragment.newInstance(user), R.string.running_tab_button);
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
            Date date = (Date) data.getSerializableExtra(DateFragment.EXTRA_DATE);
            runningViewModel.setRunningDate(date);
        }
    }
}