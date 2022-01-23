package com.thedistantblue.triaryapp.entities.base;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;
import com.thedistantblue.triaryapp.entities.EntityConstants;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = DatabaseConstants.EXERCISE_TABLE,
        foreignKeys = @ForeignKey(entity = Training.class,
                                  parentColumns = EntityConstants.TRAINING_ID_FIELD,
                                  childColumns = EntityConstants.TRAINING_ID_FIELD,
                                  onDelete = ForeignKey.CASCADE))
public class Exercise implements Serializable {

    @NoArgsConstructor
    public static class Builder {
        private UUID exerciseId;
        private UUID trainingId;
        private String name;
        private String comments;

        public Builder setExerciseId(UUID exerciseId) {
            this.exerciseId = exerciseId;
            return this;
        }

        public Builder setTrainingId(UUID trainingId) {
            this.trainingId = trainingId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setComments(String comments) {
            this.comments = comments;
            return this;
        }

        public Exercise build() {
            Exercise exercise = new Exercise();
            exercise.setExerciseId(this.exerciseId);
            exercise.setTrainingId(this.trainingId);
            exercise.setName(this.name);
            exercise.setComments(this.comments);
            return exercise;
        }
    }

    @NonNull
    @PrimaryKey
    private UUID exerciseId;
    private UUID trainingId;

    // В связи с новой архитектурой упражнение может быть в нескольких датах,
    // и дата теперь не является владельцем упражнений
    @Deprecated
    private UUID dateId;

    private String name = "";
    private String comments = "";

    @Ignore
    public Exercise(@NotNull UUID exerciseId, UUID trainingId) {
        this.exerciseId = exerciseId;
        this.trainingId = trainingId;
    }

    @Ignore
    public Exercise(UUID trainingId) {
        this(UUID.randomUUID(), trainingId);
    }
}