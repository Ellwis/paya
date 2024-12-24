package com.paya.pleaserservice.enums;

public enum DataState {
    ACTIVE(0, "ACTIVE"),
    DELETED(100, "DELETED");

    private final int state;
    private final String title;

    DataState(int state, String title) {
        this.state = state;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getState() {
        return state;
    }
}
