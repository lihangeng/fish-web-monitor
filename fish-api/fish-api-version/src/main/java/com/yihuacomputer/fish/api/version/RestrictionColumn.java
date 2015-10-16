package com.yihuacomputer.fish.api.version;


public enum RestrictionColumn {
    /**
     * 现金标志
     */
    CASH_TYPE("RestrictionColumn.CASH_TYPE"),
    /**
     * 离行标志
     */
    AWAY_FLAG("RestrictionColumn.AWAY_FLAG");

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
