package com.yihuacomputer.fish.api.device;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午12:13:20
 * @version 类说明
 */
public enum NetType {
    CABLE(0, "Cable 有线"), WIRELESS(1, "wireless 无线"), CW(2, "有线无线");

    private int id;

    private String text;

    private NetType(int id, String text) {
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

    public static NetType getById(int id) {
        for (NetType each : NetType.values()) {
            if (each.getId() == id) {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
