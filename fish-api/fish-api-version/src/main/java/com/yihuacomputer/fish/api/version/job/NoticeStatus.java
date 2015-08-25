/**
 *
 */
package com.yihuacomputer.fish.api.version.job;

/**
 * @author xuxigang
 *
 */
public enum NoticeStatus {
    SUCCESS(0, "修改成功"),
    FAIL(1, "修改失败"),
    IGNORE(2,"不需修改"),
    UNKNOW(3,"未知");

    private int id;
    private String text;

    private NoticeStatus(int id, String text) {
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
}
