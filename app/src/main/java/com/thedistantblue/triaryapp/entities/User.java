package com.thedistantblue.triaryapp.entities;

import androidx.room.Entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
@Entity(tableName = "user_table")
public class User implements Serializable {
    // Изменил id на лонг для того, чтобы можно было поставить просто как 1
    private long id;
    private String userName;
    private String userPassword;
    private List<Training> userTrainings;

    public User() {
        this(1);
    }

    public User(long id) {
        this.id = id;
    }
}