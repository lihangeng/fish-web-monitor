package com.yihuacomputer.fish.api.person;

public enum PersonState {
    OTHER(0, "其他"),ONGUARD(1, "在岗"), OFF(2, "调休"), VACATION(3, "休假");

    private int id;

    private String text;

    private PersonState(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
