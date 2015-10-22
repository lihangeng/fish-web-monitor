/**
 *
 */
package com.yihuacomputer.fish.api.version.job;

/**
 * @author xuxigang
 *
 */
public enum NoticeStatus {
    /**
     * 修改成功
     */
    SUCCESS(0, "NoticeStatus.SUCCESS"),
    /**
     * 修改失败
     */
    FAIL(1, "NoticeStatus.FAIL"),
    /**
     * 不需修改
     */
    IGNORE(2,"NoticeStatus.IGNORE"),
    /**
     * 未知
     */
    UNKNOW(3,"NoticeStatus.UNKNOW");

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
