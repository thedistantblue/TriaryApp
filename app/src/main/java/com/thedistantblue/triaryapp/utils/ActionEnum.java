package com.thedistantblue.triaryapp.utils;

public enum ActionEnum implements StringEnum {
    CREATE("create"),
    UPDATE("update");

    private final String value;

    ActionEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}