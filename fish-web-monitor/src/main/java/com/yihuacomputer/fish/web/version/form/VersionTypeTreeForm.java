package com.yihuacomputer.fish.web.version.form;

import com.yihuacomputer.fish.api.version.IVersionType;

public class VersionTypeTreeForm {
    private long id;

    private String text;

    private String qtip;

    private boolean leaf = true;

    public VersionTypeTreeForm() {
    }

    public VersionTypeTreeForm(IVersionType type) {
        this.id = type.getId();
        this.text = type.getTypeName();
        this.qtip = type.getDesc();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public String getQtip() {
        return qtip;
    }

    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

}
