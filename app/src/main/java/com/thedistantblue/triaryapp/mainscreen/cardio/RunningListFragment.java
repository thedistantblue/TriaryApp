package com.thedistantblue.triaryapp.mainscreen.cardio;

import static com.thedistantblue.triaryapp.utils.BundleKeyConstants.RUNNING_KEY;
import static com.thedistantblue.triaryapp.utils.BundleKeyConstants.USER_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.RunningDao;
import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.RunningItemCardBinding;
import com.thedistantblue.triaryapp.databinding.RunningListFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.mainscreen.AutoDisposableFragment;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivity;
import com.thedistantblue.triaryapp.mainscreen.power.TrainingFragment;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.ListItemAdapter;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.ListItemHolder;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.touch.SimpleItemTouchHelperCallback;
import com.thedistantblue.triaryapp.viewmodels.RunningCardViewModel;

import java.util.ArrayList;
import java.util.List;

public class RunningListFragment extends AutoDisposableFragment {

    private User user;
    private RunningDao runningDao;
    private RunningListItemAdapter runningAdapter;
    private UserWithTrainingAndRunningDao userWithTrainingAndRunningDao;

    public static RunningListFragment newInstance(@NonNull User user) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);

        RunningListFragment fragment = new RunningListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        withAutoDispose(userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                                     .subscribe(user -> runningAdapter.setObjectsList(user.getRunningList())));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDaos();
        user = (User) requireArguments().getSerializable(USER_KEY);
    }

    private void initDaos() {
        runningDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).runningDao();
        userWithTrainingAndRunningDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).userWithTrainingAndRunningDao();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        RunningListFragmentLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.running_list_fragment_layout, parent, false);

        runningAdapter = new RunningListItemAdapter(runningDao, new ArrayList<>(), this);
        binding.runningRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.runningRecyclerView.setAdapter(runningAdapter);
        binding.runningAddButton.setOnClickListener(v -> {
            //todo надо переделать создание бега на диалог
        });

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback((RunningListItemAdapter) binding.runningRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.runningRecyclerView);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        withAutoDispose(userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                                     .subscribe(user -> runningAdapter.setObjectsList(user.getRunningList())));
    }

    private class RunningListItemAdapter extends ListItemAdapter<Running, RunningHolder, RunningDao> {

        public RunningListItemAdapter(RunningDao runningDao, List<Running> runningList, AutoDisposableFragment fragment) {
            super(runningDao, fragment, runningList);
        }

        @NonNull
        @Override
        public RunningHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            runningItemCardBinding.setViewModel(new RunningCardViewModel());
        }

        @Override
        public void bind(@NonNull final Running running) {
            runningItemCardBinding.getViewModel().runningName.set(running.getRunningName());
            runningItemCardBinding.executePendingBindings();
            runningItemCardBinding.runningCard.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), RunningDetailActivity.class);
                intent.putExtra(USER_KEY, user);
                intent.putExtra(RUNNING_KEY, running);
                startActivity(intent);
            });
        }
    }
}