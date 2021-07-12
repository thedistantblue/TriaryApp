package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.mainscreen.AutoDisposableFragment;

import io.reactivex.rxjava3.disposables.Disposable;
import lombok.Setter;

public class TrainingViewModel extends BaseObservable {

    public final ObservableField<String> trainingName = new ObservableField<>();

    @Setter
    private Training training;
    private final AutoDisposableFragment autoDisposableFragment;
    private final TrainingDao trainingDao;

    public TrainingViewModel(Training training, TrainingDao trainingDao, AutoDisposableFragment autoDisposableFragment) {
        this.training = training;
        this.trainingDao = trainingDao;
        this.autoDisposableFragment = autoDisposableFragment;
        init();
    }

    private void init() {
        trainingName.set(training.getTrainingName());
    }

    // TODO вернуть Disposable из этих методов для последующего dispose()
    public void save() {
        training.setTrainingName(trainingName.get());
        Disposable disposable = trainingDao.save(training).subscribe(() -> {
            // TODO: Добавить тоаст об успешном сохранении
        });
        autoDisposableFragment.addDisposable(disposable);
    }
}