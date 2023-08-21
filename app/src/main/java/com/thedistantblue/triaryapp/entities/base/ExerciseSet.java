package com.thedistantblue.triaryapp.entities.base;

import android.os.Parcel;
import android.os.Parcelable;

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
@Entity(tableName = DatabaseConstants.EXERCISE_SET_TABLE,
        foreignKeys = @ForeignKey(entity = Exercise.class,
                                  parentColumns = EntityConstants.EXERCISE_ID_FIELD,
                                  childColumns = EntityConstants.EXERCISE_ID_FIELD,
                                  onDelete = ForeignKey.CASCADE))
public class ExerciseSet implements Serializable, Parcelable {

    @NonNull
    @PrimaryKey
    private UUID exerciseSetId;
    private UUID exerciseId;
    private int number;
    private int repetitions;
    private double weight;

    @Ignore
    public ExerciseSet(UUID exerciseId) {
        this(UUID.randomUUID(), exerciseId);
    }

    @Ignore
    public ExerciseSet(@NotNull UUID exerciseSetId, UUID exerciseId) {
        this.exerciseSetId = exerciseSetId;
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