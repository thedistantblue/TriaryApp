package com.thedistantblue.triaryapp.mainscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.databinding.TrainingFragmentLayoutBinding;
import com.thedistantblue.triaryapp.databinding.TrainingItemCardBinding;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.entities.User;
import com.thedistantblue.triaryapp.mainscreen.TrainingFlow.TrainingCreationFragment;
import com.thedistantblue.triaryapp.viewmodels.TrainingViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Возможно, создание юзера надо перенести в активность,
// а потом доставать из аргументов фрагмента, которые будут добавляться
// во фрагмент в методе newInstance()
// СДЕЛАНО
public class TrainingFragment extends Fragment {
    private static final String USER_KEY = "user";

    DAO dao;
    User user;
    List<Training> trainingList;

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
        dao = DAO.get(getActivity());
        user = (User) getArguments().getSerializable(USER_KEY);
        try {
            trainingList = dao.getTrainingsList(user);
        } catch (NullPointerException exc) {
            trainingList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        TrainingFragmentLayoutBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.training_fragment_layout, parent, false);

        binding.trainingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.trainingRecyclerView.setAdapter(new TrainingAdapter(dao.getTrainingsList(user)));
        binding.trainingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Create training", Toast.LENGTH_SHORT).show();
                ((MainScreenActivity) getActivity()).manageFragments(TrainingCreationFragment.newInstance(user));
            }
        });
        return binding.getRoot();
    }

    private class TrainingHolder extends RecyclerView.ViewHolder {
        private TrainingItemCardBinding trainingItemCardBinding;

        private TrainingHolder(TrainingItemCardBinding ticb) {
            super(ticb.getRoot());
            trainingItemCardBinding = ticb;
            trainingItemCardBinding.setViewModel(new TrainingViewModel());
        }

        public void bind(Training training) {
            trainingItemCardBinding.getViewModel().setTraining(training);
            trainingItemCardBinding.executePendingBindings();
        }

    }

    private class TrainingAdapter extends RecyclerView.Adapter<TrainingHolder> {
        List<Training> trainingList;

        public TrainingAdapter(List<Training> trainingList) {
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
    }
}
