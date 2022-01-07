package com.thedistantblue.triaryapp.entities.base;

import androidx.room.Entity;

import com.thedistantblue.triaryapp.entities.EntityConstants;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(primaryKeys = {EntityConstants.DATE_ID, EntityConstants.PACK_ID})
public class DatePackCrossRef {
    private UUID dateId;
    private UUID packId;
}