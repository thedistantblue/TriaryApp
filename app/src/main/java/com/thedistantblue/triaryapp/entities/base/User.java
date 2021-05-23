package com.thedistantblue.triaryapp.entities.base;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;
import com.thedistantblue.triaryapp.entities.EntityConstants;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
@Entity(tableName = DatabaseConstants.USER_TABLE,
        primaryKeys = {EntityConstants.PRIMARY_KEY_FIELD_NAME, User.ID_FIELD_NAME})
public class User implements Serializable {

    public static final String ID_FIELD_NAME = "userID";

    @PrimaryKey(autoGenerate = true)
    private int id;
    // Изменил id на лонг для того, чтобы можно было поставить просто как 1
    private long userID;
    private String userName;
    private String userPassword;

    @Relation(parentColumn = User.ID_FIELD_NAME, entityColumn = Training.USER_ID_FIELD_NAME)
    private List<Training> userTrainings;

    public User() {
        this(1);
    }

    @Ignore
    public User(long userID) {
        this.userID = userID;
    }
}