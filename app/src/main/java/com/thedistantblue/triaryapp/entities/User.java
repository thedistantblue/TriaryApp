package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
@Entity(tableName = "user_table")
public class User implements Serializable {
    @PrimaryKey
    private int id;
    // Изменил id на лонг для того, чтобы можно было поставить просто как 1
    private long userID;
    private String userName;
    private String userPassword;
    private List<Training> userTrainings;

    public User() {
        this(1);
    }

    public User(long userID) {
        this.userID = userID;
    }
}