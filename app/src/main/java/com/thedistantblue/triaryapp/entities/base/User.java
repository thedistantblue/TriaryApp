package com.thedistantblue.triaryapp.entities.base;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.thedistantblue.triaryapp.database.room.database.DatabaseConstants;
import com.thedistantblue.triaryapp.entities.EntityConstants;

import java.io.Serializable;

import lombok.Data;

@Data
@Entity(tableName = DatabaseConstants.USER_TABLE,
        indices = {@Index(value = EntityConstants.USER_ID_FIELD,
                          unique = true)})
public class User implements Serializable {

    // Изменил id на лонг для того, чтобы можно было поставить просто как 1
    @PrimaryKey(autoGenerate = true)
    private int userId;
    private String userName;
    private String userPassword;

    public User() {
        this.userId = 1;
        this.userName = "user";
        this.userPassword = "password";
    }
}