package com.thedistantblue.triaryapp.entities.composite;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.thedistantblue.triaryapp.entities.EntityConstants;
import com.thedistantblue.triaryapp.entities.base.Running;
import com.thedistantblue.triaryapp.entities.base.Training;
import com.thedistantblue.triaryapp.entities.base.User;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UserWithTrainingAndRunning {
    @Embedded private User user;
    @Relation(parentColumn = User.ID_FIELD_NAME, entityColumn = EntityConstants.PARENT_UUID_FIELD)
    private List<Training> trainingList;
    @Relation(parentColumn = User.ID_FIELD_NAME, entityColumn = Running.USER_ID_FIELD_NAME)
    private List<Running> runningList;
}