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
                                  parentColumns = EntityConstants.UUID_FIELD,
                                  childColumns = EntityConstants.PARENT_UUID_FIELD,
                                  onDelete = ForeignKey.CASCADE))
public class Exercise implements Serializable {

    @NoArgsConstructor
    public static class Builder {
        private UUID uuid;
        private UUID parentUuid;
        private String name;
        private String comments;

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setParentUuid(UUID parentUuid) {
            this.parentUuid = parentUuid;
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
            exercise.setUuid(this.uuid);
            exercise.setParentUuid(this.parentUuid);
            exercise.setName(this.name);
            exercise.setComments(this.comments);
            return exercise;
        }
    }

    @NonNull
    @PrimaryKey
    private UUID uuid;
    private UUID parentUuid;
    private UUID dateId;
    private String name = "";
    private String comments = "";

    @Ignore
    public Exercise(@NotNull UUID uuid, UUID parentUuid) {
        this.uuid = uuid;
        this.parentUuid = parentUuid;
    }

    @Ignore
    public Exercise(UUID parentUuid) {
        this(UUID.randomUUID(), parentUuid);
    }
}