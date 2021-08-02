package com.thedistantblue.triaryapp.mainscreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.RunningDao;
import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.RunningFragmentLayoutBinding;
import com.thedistantblue.triaryapp.databinding.RunningItemCardBinding;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.mainscreen.RunningFlow.RunningCreationFragment;
import com.thedistantblue.triaryapp.utils.ActionEnum;
import com.thedistantblue.triaryapp.viewmodels.RunningViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RunningFragment extends TitledFragment {
    private static final String USER_KEY = "user";

    private RunningDao runningDao;
    private UserWithTrainingAndRunningDao userWithTrainingAndRunningDao;
    private User user;
    private List<Running> runningList = new ArrayList<>();
    private RunningFragmentLayoutBinding binding;
    private RunningAdapter runningAdapter;

    public static RunningFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);

        RunningFragment fragment = new RunningFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getTitle() {
        return R.string.running_tab_button;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainScreenActivityCallback) getActivity()).setTitle(R.string.running_tab_button);
        withAutoDispose(
                userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                             .subscribe(user -> {
                                                 runningList = user.getRunningList();
                                                 runningAdapter.setRunningList(runningList);
                                                 runningAdapter.notifyDataSetChanged();
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
                                                 runningAdapter.setRunningList(runningList);
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

        runningAdapter = new RunningAdapter(runningList, getActivity());
        binding.runningRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.runningRecyclerView.setAdapter(runningAdapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback((RunningAdapter) binding.runningRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.runningRecyclerView);

        binding.runningAddButton.setOnClickListener(v -> ((MainScreenActivityCallback) getActivity())
                .switchFragment
                        (RunningCreationFragment.newInstance(user, null, ActionEnum.CREATE)));

        return binding.getRoot();
    }

    private class RunningAdapter extends RecyclerView.Adapter<RunningHolder> implements ItemTouchHelperAdapter {
        private List<Running> runningList;
        private Context context;

        public RunningAdapter(List<Running> runningList, Context context) {
            this.runningList = runningList;
            this.context = context;
        }

        public Context getContext() {
            return this.context;
        }

        public void setRunningList(List<Running> runningList) {
            this.runningList = runningList;
        }

        @Override
        public RunningHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(getActivity());

            RunningItemCardBinding runningItemCardBinding =
                    DataBindingUtil.inflate(inflate, R.layout.running_item_card, parent, false);

            return new RunningHolder(runningItemCardBinding);
        }

        @Override
        public void onBindViewHolder(RunningHolder holder, int position) {
            Running running = runningList.get(position);
            holder.bind(running);
        }

        @Override
        public int getItemCount() {
            return runningList.size();
        }

        @Override
        public void onItemDismiss(int position) {
            withAutoDispose(
                    runningDao.delete(runningList.get(position))
                              .subscribe(() -> {
                                  runningList.remove(position);
                                  notifyItemRemoved(position);
                              }));
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(runningList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(runningList, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onRefresh(int position) {
            this.notifyItemChanged(position);
        }
    }

    private class RunningHolder extends RecyclerView.ViewHolder {
        private final RunningItemCardBinding runningItemCardBinding;

        public RunningHolder(RunningItemCardBinding runningItemCardBinding) {
            super(runningItemCardBinding.getRoot());
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
