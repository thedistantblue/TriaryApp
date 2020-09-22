package com.thedistantblue.triaryapp.database;

import android.content.Context;

import com.thedistantblue.triaryapp.entities.Dates;
import com.thedistantblue.triaryapp.entities.Exercise;
import com.thedistantblue.triaryapp.entities.Running;
import com.thedistantblue.triaryapp.entities.Set;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.entities.User;

import java.util.List;

public interface DAO {

    static DAO get(Context context) {
        return LocalDAO.get(context);
    }

    void addUser(User user);

    void addTraining(Training training);

    void addDates(Dates dates);

    void addExercise(Exercise exercise);

    void addSet(Set set);

    void addRunning(Running running);

    DataCursorWrapper queryData(String databasename, String whereClause, String[] whereArgs);

    User getUser(User user);

    // ВОЗМОЖНО НЕПРАВИЛЬНО!
    // Тренировка представляет из себя список упражнений, поэтому
    // при получении самой тренировки, вы должны и получить ассоциированный с ней
    // список упражнений.
    // Сделал так, как должно быть, но возможны ошибки. Перевроверить.
    // UPD: По-идее, этот метод вообще не нужен, т.к. все необходимое делается в
    // получении списка, а сама тренировка берется при помощи курсор враппера.
    Training getTraining(Training training);

    // ВНИМАНИЕ!
    // Пользователь сейчас ищется по пустому UUID, что захардкодено.
    // Как задать пустой UUID: UUID.fromString("");
    List<Training> getTrainingsList(User user);

    // ВОЗМОЖНО НЕПРАВИЛЬНО!
    // Получая конкретное упражнение, мы должны также взять список сетов из
    // соответствующей таблицы.
    Exercise getExercise(Exercise exercise);

    List<Exercise> getExercisesList(Dates dates);

    Set getSet(Exercise exercise, int number);

    // ВНИМАНИЕ!
    // Не надо ли получать и задавать каждый сет отдельно?
    // Получение написано выше.
    List<Set> getSetsList(Exercise exercise);

    List<Dates> getDates(Training training);

    Running getRunning(Running running);

    List<Running> getRunningList(User user);

    // Обновляем тренировку
    void updateTraining(Training training);

    // Обновляем упражнение
    void updateExercise(Exercise exercise);

    // Обновляем сет
    void updateSet(Set set);

    void updateRunning(Running running);

    // Удаляем пользователя
    void deleteUser(User user);

    void deleteTraining(Training training);

    void deleteDate(Dates dates);

    // Удаляем упражнение из БД и ассоциированные с ним сеты
    void deleteExercise(Exercise exercise);

    void deleteSet(Set set);

    void deleteRunning(Running running);
}
