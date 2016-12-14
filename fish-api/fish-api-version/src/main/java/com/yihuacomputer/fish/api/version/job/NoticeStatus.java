/**
 *
 */
package com.yihuacomputer.fish.api.version.job;

/**
 * @author xuxigang
 *
 */
public enum NoticeStatus {
    SUCCESS(0, "NoticeStatus.SUCCESS"),//修改成功
    FAIL(1, "NoticeStatus.FAIL"),//修改失败
    IGNORE(2,"NoticeStatus.IGNORE"),//不需修改
    UNKNOW(3,"NoticeStatus.UNKNOW");//未知

    private int id;
    private String text;

    private NoticeStatus(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

}
