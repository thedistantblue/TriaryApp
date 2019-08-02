package com.thedistantblue.triaryapp.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


// Изменил id на лонг для того, чтобы можно было поставить просто как 1
public class User implements Serializable {
    private long id;
    //private UUID id;
    private String userName;
    private String userPassword;
    private List<Training> userTrainings;

    public User() {
        this(1);

    }

    public User(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<Training> getUserTrainings() {
        return userTrainings;
    }

    public void setUserTrainings(List<Training> userTrainings) {
        this.userTrainings = userTrainings;
    }
}
