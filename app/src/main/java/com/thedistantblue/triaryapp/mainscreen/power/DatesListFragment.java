package com.thedistantblue.triaryapp.mainscreen.power;

import static com.thedistantblue.triaryapp.utils.BundleKeyConstants.TRAINING_KEY;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.DayDao;
import com.thedistantblue.triaryapp.database.room.dao.TrainingWithDaysDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.databinding.DateItemCardBinding;
import com.thedistantblue.triaryapp.databinding.DatesListFragmentLayoutBinding;
import com.thedistantblue.triaryapp.entities.base.Day;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.composite.TrainingWithDays;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivity;
import com.thedistantblue.triaryapp.mainscreen.TitledFragment;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.touch.ItemTouchHelperAdapter;
import com.thedistantblue.triaryapp.mainscreen.utils.recycler.touch.SimpleItemTouchHelperCallback;
import com.thedistantblue.triaryapp.viewmodels.DateViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatesListFragment extends TitledFragment {

    private DatesAdapter datesAdapter;
    private List<Day> datesList = new ArrayList<>();
    private Training training;
    private DayDao datesDao;
    private TrainingWithDaysDao trainingWithDatesDao;

    public static DatesListFragment newInstance(Training training) {
        Bundle args = new Bundle();
        args.putSerializable(TRAINING_KEY, training);

        DatesListFragment fragment = new DatesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getTitle() {
        return R.string.training_dates_fragment_name;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        training = (Training) getArguments().getSerializable(TRAINING_KEY);
        initDaos();
        withAutoDispose(
                trainingWithDatesDao.findById(training.getTrainingId().toString())
                                    .subscribe(this::initDatesList));
    }

    private void initDaos() {
        datesDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                       .dayDao();
        trainingWithDatesDao = RoomDataBaseProvider.getDatabaseWithProxy(getActivity())
                                                   .trainingWithDatesDao();
    }

    private void initDatesList(TrainingWithDays trainingWithDates) {
        datesList = trainingWithDates.getDatesList();
        datesAdapter.setDatesList(datesList);
        datesAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        DatesListFragmentLayoutBinding binding = DataBindingUtil.inflate(inflater,
                                                                         R.layout.dates_list_fragment_layout,
                                                                         parent,
                                                                         false);

        datesAdapter = new DatesAdapter(datesList, getActivity());
        binding.datesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.datesRecyclerView.setAdapter(datesAdapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback((DatesAdapter) binding.datesRecyclerView.getAdapter());

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.datesRecyclerView);

        binding.datesRecyclerView.getAdapter().notifyDataSetChanged();
        binding.datesAddButton.setOnClickListener(v -> ((MainScreenActivity) getActivity())
                .switchFragment(DatesFragment.newInstance(training)));

        return binding.getRoot();

    }

    private class DatesHolder extends RecyclerView.ViewHolder {
        private final DateItemCardBinding dateItemCardBinding;

        public DatesHolder(DateItemCardBinding dateItemCardBinding) {
            super(dateItemCardBinding.getRoot());
            this.dateItemCardBinding = dateItemCardBinding;
            this.dateItemCardBinding.setViewModel(new DateViewModel());
        }

        public void bind(final Day dates) {
            this.dateItemCardBinding.getViewModel().setDate(dates);
            this.dateItemCardBinding.executePendingBindings();

            this.dateItemCardBinding.dateCard.setOnClickListener(v -> ((MainScreenActivity) getActivity())
                    .switchFragment(ExerciseListFragment.newInstance(dates)));
        }
    }

    private class DatesAdapter extends RecyclerView.Adapter<DatesHolder>
            implements ItemTouchHelperAdapter {
        List<Day> datesList;
        Context context;

        public DatesAdapter(List<Day> datesList, Context context) {
            this.datesList = datesList;
            this.context = context;
        }

        public Context getContext() {
            return this.context;
        }

        public void setDatesList(List<Day> datesList) {
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
            Day dates = datesList.get(position);
            datesHolder.bind(dates);
        }

        @Override
        public int getItemCount() {
            return datesList.size();
        }

        @Override
        public void onItemDismiss(int position) {
            withAutoDispose(
                    datesDao.delete(datesList.get(position)).subscribe(() -> {
                        datesList.remove(position);
                        notifyItemRemoved(position);
                    }));
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