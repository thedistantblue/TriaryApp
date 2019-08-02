package com.thedistantblue.triaryapp.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class Training {
    private UUID id;

    private long userId;
    //private UUID userId; // если все-таки будет создаваться пользователь,
                        // то необходимо добавлять его идентификатор
    private String trainingName;
    private Date trainingDate;
    private List<Exercise> trainingExercises;

    public Training(UUID id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Training (long userId) {
        this(UUID.randomUUID(), userId);

        trainingDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(trainingDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        trainingDate = new GregorianCalendar(year, month, day).getTime();

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public List<Exercise> getTrainingExercises() {
        return trainingExercises;
    }

    public void setTrainingExercises(List<Exercise> trainingExercises) {
        this.trainingExercises = trainingExercises;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
