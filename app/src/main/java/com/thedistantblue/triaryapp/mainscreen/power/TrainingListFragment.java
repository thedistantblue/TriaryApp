package com.thedistantblue.triaryapp.mainscreen.power;

import static com.thedistantblue.triaryapp.utils.BundleKeyConstants.TRAINING_KEY;
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
import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.TrainingItemCardBinding;
import com.thedistantblue.triaryapp.databinding.TrainingListFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.mainscreen.AutoDisposableFragment;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivity;
import com.thedistantblue.triaryapp.mainscreen.cardio.RunningDetailActivity;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.ListItemAdapter;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.ListItemHolder;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.touch.SimpleItemTouchHelperCallback;
import com.thedistantblue.triaryapp.viewmodels.TrainingCardViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrainingListFragment extends AutoDisposableFragment {

    private List<Training> trainingList = new ArrayList<>();

    private User user;
    private TrainingDao trainingDao;
    private TrainingListItemAdapter trainingAdapter;
    private UserWithTrainingAndRunningDao userWithTrainingAndRunningDao;

    public static TrainingListFragment newInstance(@NonNull User user) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);

        TrainingListFragment fragment = new TrainingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (user != null) {
            withAutoDispose(userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                                         .subscribe(user -> trainingAdapter.setObjectsList(user.getTrainingList())));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDaos();
        user = (User) requireArguments().getSerializable(USER_KEY);
    }

    private void initDaos() {
        this.userWithTrainingAndRunningDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).userWithTrainingAndRunningDao();
        this.trainingDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity()).trainingDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        TrainingListFragmentLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.training_list_fragment_layout, parent, false);

        this.trainingAdapter = new TrainingListItemAdapter(trainingDao, trainingList, this);
        binding.trainingRecyclerView.setAdapter(this.trainingAdapter);
        binding.trainingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.trainingAddButton.setOnClickListener(v -> {
            new PowerTrainingCreationDialog().show(getChildFragmentManager(), PowerTrainingCreationDialog.TAG);
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((TrainingListItemAdapter) binding.trainingRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.trainingRecyclerView);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (user != null) {
            withAutoDispose(userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                                         .subscribe(user -> trainingAdapter.setObjectsList(user.getTrainingList())));
        }
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

        @Override
        public void bind(@NonNull final Training training) {
            trainingItemCardBinding.executePendingBindings();
            trainingItemCardBinding.getViewModel().trainingName.set(training.getTrainingName());

            trainingItemCardBinding.trainingCard.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), TrainingDetailActivity.class);
                intent.putExtra(TRAINING_KEY, training);
                startActivity(intent);
            });
            trainingItemCardBinding.trainingSettingsButton.setOnClickListener(v -> {
                //todo добавить диалог для редактирования
            });
        }
    }
}