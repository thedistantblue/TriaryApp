package com.thedistantblue.triaryapp.mainscreen.TrainingFlow;

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
import com.thedistantblue.triaryapp.database.sqlite.DAO;
import com.thedistantblue.triaryapp.databinding.DateItemCardBinding;
import com.thedistantblue.triaryapp.databinding.DatesListFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.Dates;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.mainscreen.ItemTouchHelperAdapter;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.mainscreen.SimpleItemTouchHelperCallback;
import com.thedistantblue.triaryapp.viewmodels.DateViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatesListFragment extends Fragment {

    private static final String TRAINING_KEY = "training_key";

    private DatesListFragmentLayoutBinding binding;
    private List<Dates> datesList;
    private Training training;
    private DAO dao;

    public static DatesListFragment newInstance(Training training) {
        Bundle args = new Bundle();
        args.putSerializable(TRAINING_KEY, training);

        DatesListFragment fragment = new DatesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        init();

        binding = DataBindingUtil.inflate(inflater,
                                R.layout.dates_list_fragment_layout,
                                parent,
                                false);

        binding.datesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.datesRecyclerView.setAdapter(new DatesAdapter(datesList, getActivity()));

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback((DatesAdapter) binding.datesRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.datesRecyclerView);

        binding.datesRecyclerView.getAdapter().notifyDataSetChanged();
        binding.datesAddButton.setOnClickListener(v -> ((MainScreenActivityCallback) getActivity())
                .manageFragments(DatesFragment.newInstance(training), R.string.create_date_fragment));

        return binding.getRoot();

    }

    private void init() {
        training = (Training) getArguments().getSerializable(TRAINING_KEY);
        dao = DAO.get(getActivity());
        try {
            datesList = dao.getDates(training);
        } catch (NullPointerException ex) {
            datesList = new ArrayList<>();
        }
    }

    private class DatesHolder extends RecyclerView.ViewHolder {
        private DateItemCardBinding dateItemCardBinding;

        public DatesHolder(DateItemCardBinding dateItemCardBinding) {
            super(dateItemCardBinding.getRoot());
            this.dateItemCardBinding = dateItemCardBinding;
            this.dateItemCardBinding.setViewModel(new DateViewModel());
        }

        public void bind(final Dates dates) {

            this.dateItemCardBinding.getViewModel().setDate(dates);
            this.dateItemCardBinding.executePendingBindings();
            //dateItemCardBinding.getViewModel().setExerciseSets(exercise.getExerciseSets());


            this.dateItemCardBinding.dateCard.setOnClickListener(v -> ((MainScreenActivityCallback) getActivity())
                    .manageFragments(ExerciseListFragment.newInstance(dates), R.string.exercises_fragment));
        }
    }

    private class DatesAdapter extends RecyclerView.Adapter<DatesHolder>
            implements ItemTouchHelperAdapter {
        List<Dates> datesList;
        Context context;

        public DatesAdapter(List<Dates> datesList, Context context) {
            this.datesList = datesList;
            this.context = context;
        }

        public Context getContext() {
            return this.context;
        }

        public void setDatesList(List<Dates> datesList) {
            this.datesList = datesList;
        }

        @Override
        public DatesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(getActivity());

            DateItemCardBinding dateItemCardBinding =
                    DataBindingUtil.inflate(inflate, R.layout.date_item_card, parent, false);

            return new DatesListFragment.DatesHolder(dateItemCardBinding);
        }

        @Override
        public void onBindViewHolder(DatesHolder datesHolder, int position) {
            Dates dates = datesList.get(position);
            //Log.d("exercise id in adapter", exercise.getId().toString());
            datesHolder.bind(dates);
        }

        @Override
        public int getItemCount() {
            return datesList.size();
        }

        @Override
        public void onItemDismiss(int position) {
            dao.deleteDate(datesList.get(position));
            datesList.remove(position);
            //dao.deleteTraining(trainingList.get(position));
            notifyItemRemoved(position);
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(datesList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(datesList, i, i - 1);
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