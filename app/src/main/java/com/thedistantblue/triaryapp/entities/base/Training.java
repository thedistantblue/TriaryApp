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
@Entity(tableName = DatabaseConstants.TRAINING_TABLE,
        foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = User.ID_FIELD_NAME,
                                  childColumns = EntityConstants.PARENT_UUID_FIELD,
                                  onDelete = ForeignKey.CASCADE))
public class Training implements Serializable {

    @NonNull
    @PrimaryKey
    private UUID uuid;
    private long parentUuid;
    private String trainingName;

    @Ignore
    public Training(@NotNull UUID uuid, long parentUuid) {
        this.uuid = uuid;
        this.parentUuid = parentUuid;
    }

    @Ignore
    public Training(long parentUuid) {
        this(UUID.randomUUID(), parentUuid);
    }
}