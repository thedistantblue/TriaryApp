package com.thedistantblue.triaryapp.mainscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.databinding.TrainingFragmentLayoutBinding;
import com.thedistantblue.triaryapp.databinding.TrainingItemCardBinding;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.entities.User;
import com.thedistantblue.triaryapp.mainscreen.TrainingFlow.ExerciseListFragment;
import com.thedistantblue.triaryapp.mainscreen.TrainingFlow.TrainingCreationFragment;
import com.thedistantblue.triaryapp.viewmodels.TrainingViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingFragment extends Fragment {
    private static final String USER_KEY = "user";

    DAO dao;
    User user;
    List<Training> trainingList;

    TrainingFragmentLayoutBinding binding;

    public static TrainingFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);

        TrainingFragment fragment = new TrainingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((TrainingAdapter)binding.trainingRecyclerView.getAdapter()).setTrainingList(dao.getTrainingsList(user));
        ((MainScreenActivity) getActivity()).setTitle("Trainings");
        //binding.trainingRecyclerView.setAdapter(new TrainingAdapter(dao.getTrainingsList(user)));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = DAO.get(getActivity());
        user = (User) getArguments().getSerializable(USER_KEY);
        // Не нужно, потому что в дао уже возвращается пустой список
        // Или надо, если берем из юзера (так правильно), а юзера при этом берем из БД
        try {
            trainingList = dao.getTrainingsList(user);
            //trainingList = user.getUserTrainings();
        } catch (NullPointerException exc) {
            trainingList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.training_fragment_layout, parent, false);

        binding.trainingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.trainingRecyclerView.setAdapter(new TrainingAdapter(trainingList));

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback((TrainingAdapter) binding.trainingRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.trainingRecyclerView);

        binding.trainingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Create training", Toast.LENGTH_SHORT).show();
                ((MainScreenActivity) getActivity()).manageFragments(TrainingCreationFragment.newInstance(user), "Create training");
            }
        });
        return binding.getRoot();
    }

    public class TrainingHolder extends RecyclerView.ViewHolder {
        private TrainingItemCardBinding trainingItemCardBinding;

        private TrainingHolder(TrainingItemCardBinding ticb) {
            super(ticb.getRoot());
            trainingItemCardBinding = ticb;
            trainingItemCardBinding.setViewModel(new TrainingViewModel());
        }

        public void bind(final Training training) {
            trainingItemCardBinding.getViewModel().setTraining(training);
            trainingItemCardBinding.executePendingBindings();
            trainingItemCardBinding.trainingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainScreenActivity) getActivity()).manageFragments(ExerciseListFragment.newInstance(training), "Training exercises");
                }
            });
        }
    }

    public class TrainingAdapter extends RecyclerView.Adapter<TrainingHolder>
    implements ItemTouchHelperAdapter {
        List<Training> trainingList;

        public TrainingAdapter(List<Training> trainingList) {
            this.trainingList = trainingList;
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
            dao.deleteTraining(trainingList.get(position));
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
    }
}
