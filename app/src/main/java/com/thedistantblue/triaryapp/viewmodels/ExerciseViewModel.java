package com.thedistantblue.triaryapp.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao;
import com.thedistantblue.triaryapp.database.room.dao.ExerciseSetDao;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.Getter;

public class ExerciseViewModel extends BaseObservable {

    @Data
    public static class ExerciseSetObservablePair {
        public final ObservableField<String> repetitions;
        public final ObservableField<String> weight;
    }

    public final ObservableField<String> exerciseName = new ObservableField<>("");
    public final ExerciseSetObservablePair firstSet = new ExerciseSetObservablePair(new ObservableField<>(""), new ObservableField<>(""));
    public final ExerciseSetObservablePair secondSet = new ExerciseSetObservablePair(new ObservableField<>(""), new ObservableField<>(""));
    public final ExerciseSetObservablePair thirdSet = new ExerciseSetObservablePair(new ObservableField<>(""), new ObservableField<>(""));
    public final ExerciseSetObservablePair fourthSet = new ExerciseSetObservablePair(new ObservableField<>(""), new ObservableField<>(""));
    public final ExerciseSetObservablePair fifthSet = new ExerciseSetObservablePair(new ObservableField<>(""), new ObservableField<>(""));
    public final ObservableField<String> exerciseComments = new ObservableField<>("");

    private final Exercise exercise;
    private final Map<Integer, ExerciseSet> setNumberToSetMap;
    private final ExerciseDao exerciseDao;
    private final ExerciseSetDao exerciseSetDao;

    private ExerciseSet first;
    private ExerciseSet second;
    private ExerciseSet third;
    private ExerciseSet fourth;
    private ExerciseSet fifth;

    public ExerciseViewModel(Exercise exercise,
                             List<ExerciseSet> exerciseSets,
                             ExerciseDao exerciseDao,
                             ExerciseSetDao exerciseSetDao) {
        this.exercise = exercise;
        this.setNumberToSetMap = exerciseSets.stream().collect(Collectors.toMap(ExerciseSet::getNumber, Function.identity()));
        this.exerciseDao = exerciseDao;
        this.exerciseSetDao = exerciseSetDao;
        init();
    }

    private void init() {
        exerciseName.set(exercise.getExerciseName());

        first = setNumberToSetMap.get(1);
        firstSet.getRepetitions().set(String.valueOf(first.getRepetitions()));
        firstSet.getWeight().set(String.valueOf(first.getWeight()));

        second = setNumberToSetMap.get(2);
        secondSet.getRepetitions().set(String.valueOf(second.getRepetitions()));
        secondSet.getWeight().set(String.valueOf(second.getWeight()));

        third = setNumberToSetMap.get(3);
        thirdSet.getRepetitions().set(String.valueOf(third.getRepetitions()));
        thirdSet.getWeight().set(String.valueOf(third.getWeight()));

        fourth = setNumberToSetMap.get(4);
        fourthSet.getRepetitions().set(String.valueOf(fourth.getRepetitions()));
        fourthSet.getWeight().set(String.valueOf(fourth.getWeight()));

        fifth = setNumberToSetMap.get(5);
        fifthSet.getRepetitions().set(String.valueOf(fifth.getRepetitions()));
        fifthSet.getWeight().set(String.valueOf(fifth.getWeight()));
    }

    public void save() {
        exercise.setExerciseName(exerciseName.get());

        first.setRepetitions(Integer.parseInt(firstSet.getRepetitions().get()));
        first.setWeight(Double.parseDouble(firstSet.getWeight().get()));

        second.setRepetitions(Integer.parseInt(secondSet.getRepetitions().get()));
        second.setWeight(Double.parseDouble(secondSet.getWeight().get()));

        third.setRepetitions(Integer.parseInt(thirdSet.getRepetitions().get()));
        third.setWeight(Double.parseDouble(thirdSet.getWeight().get()));

        fourth.setRepetitions(Integer.parseInt(fourthSet.getRepetitions().get()));
        fourth.setWeight(Double.parseDouble(fourthSet.getWeight().get()));

        fifth.setRepetitions(Integer.parseInt(fifthSet.getRepetitions().get()));
        fifth.setWeight(Double.parseDouble(fifthSet.getWeight().get()));

        exercise.setExerciseComments(exerciseComments.get());

        doSave();
    }

    // TODO вернуть Disposable из этих методов для последующего dispose()
    private void doSave() {
        exerciseDao.save(exercise).subscribe(() -> {
            // TODO: Добавить тоаст об успешном сохранении
        });
        setNumberToSetMap.values().forEach(exerciseSet -> exerciseSetDao.save(exerciseSet).subscribe());
    }

}