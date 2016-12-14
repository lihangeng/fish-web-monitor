package com.yihuacomputer.fish.api.person;

public enum PersonState {
    /**
     * 其他
     */
    OTHER(0, "PersonState.OTHER"),
    /**
     * 在岗
     */
    ONGUARD(1, "PersonState.ONGUARD"), 
    /**
     * 调休
     */
    OFF(2, "PersonState.OFF"), 
    /**
     * 休假
     */
    VACATION(3, "PersonState.VACATION");

    private int id;

    private String text;

    private PersonState(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public static PersonState getById(int id) {
        for (PersonState each : PersonState.values()) {
            if (each.getId() == id) {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }

}
