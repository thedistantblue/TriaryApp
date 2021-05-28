package com.thedistantblue.triaryapp.mainscreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
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
import com.thedistantblue.triaryapp.utils.ActionEnum;
import com.thedistantblue.triaryapp.viewmodels.TrainingViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TrainingFragment extends Fragment {

    private static final String USER_KEY = "user";

    private TrainingAdapter trainingAdapter;

    private User user;
    private List<Training> trainingList = new ArrayList<>();

    private TrainingDao trainingDao;
    private UserWithTrainingAndRunningDao userWithTrainingAndRunningDao;

    TrainingFragmentLayoutBinding binding;

    public static TrainingFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);

        TrainingFragment fragment = new TrainingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDaos();
        user = (User) getArguments().getSerializable(USER_KEY);
        userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                     .subscribeOn(Schedulers.io())
                                     .observeOn(AndroidSchedulers.mainThread())
                                     .subscribeWith(new SingleObserver<UserWithTrainingAndRunning>() {
                                         @Override
                                         public void onSubscribe(@NonNull Disposable d) {

                                         }

                                         @Override
                                         public void onSuccess(@NonNull UserWithTrainingAndRunning userWithTrainingAndRunning) {
                                             trainingList = userWithTrainingAndRunning.getTrainingList();
                                             trainingAdapter.setTrainingList(trainingList);
                                             trainingAdapter.notifyDataSetChanged();
                                         }

                                         @Override
                                         public void onError(@NonNull Throwable e) {

                                         }
                                     });
    }

    private void initDaos() {
        this.userWithTrainingAndRunningDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                                                 .userWithTrainingAndRunningDao();
        this.trainingDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                               .trainingDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        this.binding =
                DataBindingUtil.inflate(inflater, R.layout.training_fragment_layout, parent, false);

        this.trainingAdapter = new TrainingAdapter(trainingList, getActivity());
        this.binding.trainingRecyclerView.setAdapter(this.trainingAdapter);
        this.binding.trainingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback((TrainingAdapter) binding.trainingRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.trainingRecyclerView);

        this.binding.trainingAddButton.setOnClickListener(v -> {
            ((MainScreenActivityCallback) getActivity()).manageFragments(TrainingCreationFragment.newInstance(user,
                                                                                                              null,
                                                                                                              ActionEnum.CREATE),
                                                                         R.string.create_training_fragment_name);
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        userWithTrainingAndRunningDao.findById(String.valueOf(user.getUserID()))
                                     .subscribeOn(Schedulers.io())
                                     .observeOn(AndroidSchedulers.mainThread())
                                     .subscribeWith(new SingleObserver<UserWithTrainingAndRunning>() {
                                         @Override
                                         public void onSubscribe(@NonNull Disposable d) {

                                         }

                                         @Override
                                         public void onSuccess(@NonNull UserWithTrainingAndRunning userWithTrainingAndRunning) {
                                             trainingList = userWithTrainingAndRunning.getTrainingList();
                                             trainingAdapter.setTrainingList(trainingList);
                                             trainingAdapter.notifyDataSetChanged();
                                         }

                                         @Override
                                         public void onError(@NonNull Throwable e) {

                                         }
                                     });
        ((MainScreenActivityCallback) getActivity()).setTitle(R.string.training_tab_button);
    }

    public class TrainingHolder extends RecyclerView.ViewHolder {
        private TrainingItemCardBinding trainingItemCardBinding;
        int pos;

        private TrainingHolder(TrainingItemCardBinding ticb) {
            super(ticb.getRoot());
            trainingItemCardBinding = ticb;
            trainingItemCardBinding.setViewModel(new TrainingViewModel());
        }

        public void bind(final Training training, final int position) {
            this.pos = position;
            trainingItemCardBinding.getViewModel().setTraining(training);
            trainingItemCardBinding.executePendingBindings();
            trainingItemCardBinding.trainingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainScreenActivityCallback) getActivity()).manageFragments(DatesListFragment.newInstance(training), R.string.training_dates_fragment_name);
                }
            });
            trainingItemCardBinding.trainingSettingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainScreenActivityCallback) getActivity()).manageFragments(TrainingCreationFragment.newInstance(user, trainingList.get(pos), ActionEnum.UPDATE), R.string.training_settings_fragment_name);
                }
            });
        }
    }

    public class TrainingAdapter extends RecyclerView.Adapter<TrainingHolder>
    implements ItemTouchHelperAdapter {
        List<Training> trainingList;
        Context context;

        public TrainingAdapter(List<Training> trainingList, Context context) {
            this.trainingList = trainingList;
            this.context = context;
        }

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
            trainingHolder.bind(training, position);
        }

        @Override
        public int getItemCount() {
            return trainingList.size();
        }

        @Override
        public void onItemDismiss(int position) {
            trainingDao.delete(trainingList.get(position));
            trainingList.remove(position);
            notifyItemRemoved(position);
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
}