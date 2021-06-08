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
import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.TrainingFragmentLayoutBinding;
import com.thedistantblue.triaryapp.databinding.TrainingItemCardBinding;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.entities.composite.UserWithTrainingAndRunning;
import com.thedistantblue.triaryapp.mainscreen.TrainingFlow.DatesListFragment;
import com.thedistantblue.triaryapp.mainscreen.TrainingFlow.TrainingCreationFragment;
import com.thedistantblue.triaryapp.viewmodels.TrainingCardViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingListFragment extends TitledFragment {

    private static final String USER_KEY = "user";

    private List<Training> trainingList = new ArrayList<>();

    private MainScreenActivityCallback mainScreenActivityCallback;

    private User user;
    private TrainingAdapter trainingAdapter;

    private TrainingDao trainingDao;
    private UserWithTrainingAndRunningDao userWithTrainingAndRunningDao;

    public static TrainingListFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);

        TrainingListFragment fragment = new TrainingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getTitle() {
        return R.string.training_list_fragment_name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainScreenActivityCallback = (MainScreenActivityCallback) getActivity();
        initDaos();
        user = (User) getArguments().getSerializable(USER_KEY);
        userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                     .subscribe(this::initTrainingList);
    }

    private void initDaos() {
        this.userWithTrainingAndRunningDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                                                 .userWithTrainingAndRunningDao();
        this.trainingDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                               .trainingDao();
    }

    private void initTrainingList(UserWithTrainingAndRunning userWithTrainingAndRunning) {
        trainingList = userWithTrainingAndRunning.getTrainingList();
        trainingAdapter.setTrainingList(trainingList);
        trainingAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        TrainingFragmentLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.training_fragment_layout, parent, false);

        this.trainingAdapter = new TrainingAdapter(trainingList, getActivity());
        binding.trainingRecyclerView.setAdapter(this.trainingAdapter);
        binding.trainingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.trainingAddButton.setOnClickListener(v -> createTrainingAndSwitchFragment());

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((TrainingAdapter) binding.trainingRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.trainingRecyclerView);

        return binding.getRoot();
    }

    private void createTrainingAndSwitchFragment() {
        Training training = new Training(user.getUserID());
        trainingDao.create(training)
                   .subscribe(() -> mainScreenActivityCallback.switchFragment(TrainingCreationFragment.newInstance(user, training)));
    }

    @Override
    public void onResume() {
        super.onResume();
        userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                     .subscribe(userWithTrainingAndRunning -> {
                                         trainingList = userWithTrainingAndRunning.getTrainingList();
                                         trainingAdapter.setTrainingList(trainingList);
                                         trainingAdapter.notifyDataSetChanged();
                                     });
    }

    public class TrainingAdapter extends RecyclerView.Adapter<TrainingHolder> implements ItemTouchHelperAdapter {
        private List<Training> trainingList;
        private Context context;

        public TrainingAdapter(List<Training> trainingList, Context context) {
            this.trainingList = trainingList;
            this.context = context;
        }

        @Override
        public Context getContext() {
            return this.context;
        }

        public void setTrainingList(List<Training> trainingList) {
            this.trainingList = trainingList;
        }

        @Override
        public TrainingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(getActivity());

            TrainingItemCardBinding trainingItemCardBinding =
                    DataBindingUtil.inflate(inflate, R.layout.training_item_card, parent, false);

            return new TrainingHolder(trainingItemCardBinding);
        }

        @Override
        public void onBindViewHolder(TrainingHolder trainingHolder, int position) {
            Training training = trainingList.get(position);
            trainingHolder.bind(training);
        }

        @Override
        public int getItemCount() {
            return trainingList.size();
        }

        @Override
        public void onItemDismiss(int position) {
            trainingDao.delete(trainingList.get(position)).subscribe();
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(trainingList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(trainingList, i, i - 1);
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

    public class TrainingHolder extends RecyclerView.ViewHolder {
        private final TrainingItemCardBinding trainingItemCardBinding;

        private TrainingHolder(TrainingItemCardBinding trainingItemCardBinding) {
            super(trainingItemCardBinding.getRoot());
            this.trainingItemCardBinding = trainingItemCardBinding;
            this.trainingItemCardBinding.setViewModel(new TrainingCardViewModel());
        }

        public void bind(final Training training) {
            trainingItemCardBinding.executePendingBindings();
            trainingItemCardBinding.getViewModel().trainingName.set(training.getTrainingName());

            trainingItemCardBinding.trainingCard.setOnClickListener(v -> {
                mainScreenActivityCallback.switchFragment(DatesListFragment.newInstance(training));
            });
            trainingItemCardBinding.trainingSettingsButton.setOnClickListener(v -> {
                mainScreenActivityCallback.switchFragment(TrainingCreationFragment.newInstance(user, training));
            });
        }
    }
}