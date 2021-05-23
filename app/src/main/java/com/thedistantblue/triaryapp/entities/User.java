package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Entity(tableName = DatabaseConstants.USER_TABLE)
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    // Изменил id на лонг для того, чтобы можно было поставить просто как 1
    private long userID;
    private String userName;
    private String userPassword;

    @Relation(parentColumn = Fields.userID, entityColumn = Training.Fields.userId)
    private List<Training> userTrainings;

    public User() {
        this(1);
    }

    public User(long userID) {
        this.userID = userID;
    }
}