package com.thedistantblue.triaryapp.mainscreen.power;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.TrainingFragmentLayoutBinding;
import com.thedistantblue.triaryapp.databinding.TrainingItemCardBinding;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.entities.composite.UserWithTrainingAndRunning;
import com.thedistantblue.triaryapp.mainscreen.AutoDisposableFragment;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.ListItemAdapter;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.ListItemHolder;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.touch.SimpleItemTouchHelperCallback;
import com.thedistantblue.triaryapp.viewmodels.TrainingCardViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrainingListFragment extends AutoDisposableFragment {
    private static final String USER_KEY = "user";

    private List<Training> trainingList = new ArrayList<>();

    private MainScreenActivityCallback mainScreenActivityCallback;

    private User user;
    private TrainingListItemAdapter trainingAdapter;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainScreenActivityCallback = (MainScreenActivityCallback) getActivity();
        initDaos();
        user = (User) getArguments().getSerializable(USER_KEY);
        if (user == null) {
            user = new User();
        }
        withAutoDispose(
                userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                             .subscribe(this::initTrainingList));
    }

    private void initDaos() {
        this.userWithTrainingAndRunningDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                                                 .userWithTrainingAndRunningDao();
        this.trainingDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                               .trainingDao();
    }

    private void initTrainingList(UserWithTrainingAndRunning userWithTrainingAndRunning) {
        trainingList = userWithTrainingAndRunning.getTrainingList();
        trainingAdapter.setObjectsList(trainingList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        TrainingFragmentLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.training_fragment_layout, parent, false);

        this.trainingAdapter = new TrainingListItemAdapter(trainingDao, trainingList, this);
        binding.trainingRecyclerView.setAdapter(this.trainingAdapter);
        binding.trainingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.trainingAddButton.setOnClickListener(v -> createTrainingAndSwitchFragment());

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((TrainingListItemAdapter) binding.trainingRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.trainingRecyclerView);

        return binding.getRoot();
    }

    private void createTrainingAndSwitchFragment() {
        Training training = new Training(user.getUserID());
        withAutoDispose(
                trainingDao.create(training)
                           .subscribe(() -> mainScreenActivityCallback.switchFragment(TrainingCreationFragment.newInstance(user, training))));
    }

    @Override
    public void onResume() {
        super.onResume();
        withAutoDispose(
                userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                             .subscribe(userWithTrainingAndRunning -> {
                                                 trainingList = userWithTrainingAndRunning.getTrainingList();
                                                 trainingAdapter.setObjectsList(trainingList);
                                             }));
    }

    public class TrainingListItemAdapter extends ListItemAdapter<Training, TrainingHolder, TrainingDao> {

        public TrainingListItemAdapter(TrainingDao trainingDao,
                                       List<Training> trainingList,
                                       AutoDisposableFragment fragment) {
            super(trainingDao, fragment, trainingList);
        }

        @NonNull
        @Override
        public TrainingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(getActivity());

            TrainingItemCardBinding trainingItemCardBinding =
                    DataBindingUtil.inflate(inflate, R.layout.training_item_card, parent, false);

            return new TrainingHolder(trainingItemCardBinding);
        }
    }

    public class TrainingHolder extends ListItemHolder<Training, TrainingItemCardBinding> {
        private final TrainingItemCardBinding trainingItemCardBinding;

        private TrainingHolder(TrainingItemCardBinding trainingItemCardBinding) {
            super(trainingItemCardBinding);
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