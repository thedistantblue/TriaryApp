package com.thedistantblue.triaryapp.entities.base;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;
import com.thedistantblue.triaryapp.entities.EntityConstants;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
@Entity(tableName = DatabaseConstants.USER_TABLE,
        primaryKeys = {EntityConstants.USER_PRIMARY_KEY_FIELD_NAME, User.ID_FIELD_NAME},
        indices = {@Index(value = {EntityConstants.USER_PRIMARY_KEY_FIELD_NAME, User.ID_FIELD_NAME},
                          unique = true)})
public class User implements Serializable {

    public static final String ID_FIELD_NAME = "user_id";

    @ColumnInfo(name = EntityConstants.USER_PRIMARY_KEY_FIELD_NAME)
    private int id;
    // Изменил id на лонг для того, чтобы можно было поставить просто как 1
    @ColumnInfo(name = "user_id")
    private int userID;
    private String userName;
    private String userPassword;

    public User() {
        this(1);
    }

    @Ignore
    public User(int userID) {
        this.userID = userID;
    }
}