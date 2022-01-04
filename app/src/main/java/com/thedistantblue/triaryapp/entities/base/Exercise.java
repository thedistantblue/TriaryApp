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

    @NonNull
    @PrimaryKey
    private UUID uuid;
    private UUID parentUuid;
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