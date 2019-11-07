package com.thedistantblue.triaryapp.entities;

import java.io.Serializable;
import java.util.UUID;

public class Dates implements Serializable {
    private long datesDate;
    private UUID datesTrainingUUID;

    public long getDatesDate() {
        return datesDate;
    }

    public void setDatesDate(long datesDate) {
        this.datesDate = datesDate;
    }

    public UUID getDatesTrainingUUID() {
        return datesTrainingUUID;
    }

    public void setDatesTrainingUUID(UUID datesTrainingUUID) {
        this.datesTrainingUUID = datesTrainingUUID;
    }
}
