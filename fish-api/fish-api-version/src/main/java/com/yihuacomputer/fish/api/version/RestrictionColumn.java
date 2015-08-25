package com.yihuacomputer.fish.api.version;


public enum RestrictionColumn {
    CASH_TYPE("现金标志"),
    AWAY_FLAG("离行标志");

    private String text;

    private RestrictionColumn(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
