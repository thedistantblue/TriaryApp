package com.thedistantblue.triaryapp.mainscreen.RunningFlow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.RunningDao;
import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.RunningFragmentLayoutBinding;
import com.thedistantblue.triaryapp.databinding.RunningItemCardBinding;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.mainscreen.AutoDisposableFragment;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.ListItemAdapter;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.ListItemHolder;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.touch.SimpleItemTouchHelperCallback;
import com.thedistantblue.triaryapp.utils.ActionEnum;
import com.thedistantblue.triaryapp.viewmodels.RunningViewModel;

import java.util.ArrayList;
import java.util.List;

public class RunningListFragment extends AutoDisposableFragment {
    private static final String USER_KEY = "user";

    private RunningDao runningDao;
    private UserWithTrainingAndRunningDao userWithTrainingAndRunningDao;
    private User user;
    private List<Running> runningList = new ArrayList<>();
    private RunningFragmentLayoutBinding binding;
    private RunningListItemAdapter runningAdapter;

    public static RunningListFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);

        RunningListFragment fragment = new RunningListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        withAutoDispose(
                userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                             .subscribe(user -> {
                                                 runningList = user.getRunningList();
                                                 runningAdapter.setObjectsList(runningList);
                                             }));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDaos();
        user = (User) getArguments().getSerializable(USER_KEY);
        if (user == null) {
            user = new User();
        }
        withAutoDispose(
                userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                             .subscribe(user -> {
                                                 runningList = user.getRunningList();
                                                 runningAdapter.setObjectsList(runningList);
                                                 runningAdapter.notifyDataSetChanged();
                                             }));
    }

    private void initDaos() {
        runningDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                         .runningDao();
        userWithTrainingAndRunningDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                                            .userWithTrainingAndRunningDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.running_fragment_layout, parent, false);

        runningAdapter = new RunningListItemAdapter(runningDao, runningList, this);
        binding.runningRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.runningRecyclerView.setAdapter(runningAdapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback((RunningListItemAdapter) binding.runningRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.runningRecyclerView);

        binding.runningAddButton.setOnClickListener(v -> ((MainScreenActivityCallback) getActivity())
                .switchFragment
                        (RunningCreationFragment.newInstance(user, null, ActionEnum.CREATE)));

        return binding.getRoot();
    }

    private class RunningListItemAdapter extends ListItemAdapter<Running, RunningHolder, RunningDao> {

        public RunningListItemAdapter(RunningDao runningDao,
                                      List<Running> runningList,
                                      AutoDisposableFragment fragment) {
            super(runningDao, fragment, runningList);
        }

        @NonNull
        @Override
        public RunningHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(getActivity());

            RunningItemCardBinding runningItemCardBinding =
                    DataBindingUtil.inflate(inflate, R.layout.running_item_card, parent, false);

            return new RunningHolder(runningItemCardBinding);
        }
    }

    private class RunningHolder extends ListItemHolder<Running, RunningItemCardBinding> {
        private final RunningItemCardBinding runningItemCardBinding;

        public RunningHolder(RunningItemCardBinding runningItemCardBinding) {
            super(runningItemCardBinding);
            this.runningItemCardBinding = runningItemCardBinding;
            runningItemCardBinding.setViewModel(new RunningViewModel());
        }

        public void bind(final Running running) {
            runningItemCardBinding.getViewModel().setRunning(running);
            runningItemCardBinding.executePendingBindings();
            runningItemCardBinding.runningCard.setOnClickListener(v -> ((MainScreenActivityCallback) getActivity())
                    .switchFragment(RunningCreationFragment.newInstance(user, running, ActionEnum.UPDATE)));
        }
    }
}