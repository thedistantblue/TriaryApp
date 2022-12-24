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
        private String description;

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

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Exercise build() {
            Exercise exercise = new Exercise();
            exercise.setExerciseId(this.exerciseId);
            exercise.setTrainingId(this.trainingId);
            exercise.setName(this.name);
            exercise.setDescription(this.description);
            return exercise;
        }
    }

    @NonNull
    @PrimaryKey
    private UUID exerciseId;
    private UUID trainingId;
    private String name = "";
    private String description = "";

    @Ignore
    public Exercise(@NotNull UUID exerciseId, UUID trainingId) {
        this.exerciseId = exerciseId;
        this.trainingId = trainingId;
    }

    @Ignore
    public Exercise(UUID trainingId) {
        this(UUID.randomUUID(), trainingId);
    }

    @Ignore
    public Exercise(UUID trainingId, String name, String description) {
        this.exerciseId = UUID.randomUUID();
        this.trainingId = trainingId;
        this.name = name;
        this.description = description;
    }

    @Ignore
    public Exercise(UUID exerciseId, UUID trainingId, String name, String description) {
        this.exerciseId = exerciseId;
        this.trainingId = trainingId;
        this.name = name;
        this.description = description;
    }

    public Exercise() {

    }

    @NonNull
    public UUID getExerciseId() {
        return exerciseId;
    }

    public UUID getTrainingId() {
        return trainingId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setExerciseId(@NonNull UUID exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setTrainingId(UUID trainingId) {
        this.trainingId = trainingId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}