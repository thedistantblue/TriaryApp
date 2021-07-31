package com.thedistantblue.triaryapp.entities.base;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = DatabaseConstants.EXERCISE_SET_TABLE,
        foreignKeys = @ForeignKey(entity = Exercise.class,
                                  parentColumns = Exercise.UUID_FIELD_NAME,
                                  childColumns = ExerciseSet.EXERCISE_UUID_FIELD,
                                  onDelete = ForeignKey.CASCADE))
public class ExerciseSet implements Serializable, Parcelable {

    public static final String UUID_FIELD_NAME = "exerciseSetUUID";
    public static final String EXERCISE_UUID_FIELD = "exerciseId";

    @NonNull
    @PrimaryKey
    private UUID exerciseSetUUID;
    private UUID exerciseId;
    private int number;
    private int repetitions;
    private double weight;

    @Ignore
    public ExerciseSet(UUID exerciseId) {
        this(UUID.randomUUID(), exerciseId);
    }

    @Ignore
    public ExerciseSet(@NotNull UUID exerciseSetUUID, UUID exerciseId) {
        this.exerciseSetUUID = exerciseSetUUID;
        this.exerciseId = exerciseId;
    }

    @Ignore
    public ExerciseSet(Parcel in) {
        number = in.readInt();
        repetitions = in.readInt();
        weight = in.readDouble();
    }

    public static final Creator<ExerciseSet> CREATOR = new Creator<ExerciseSet>() {
        @Override
        public ExerciseSet createFromParcel(Parcel in) {
            return new ExerciseSet(in);
        }

        @Override
        public ExerciseSet[] newArray(int size) {
            return new ExerciseSet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeInt(repetitions);
        dest.writeDouble(weight);
    }
}