package com.yihuacomputer.fish.api.advert;

/**
 * 屏幕分辨率
 * @author xuxigang
 *
 */
public enum Screen {
    SCREEN_1024("1024"),
    SCREEN_800("800"),
    SCREEN_600("600"),
    SCREEN_video("video"),
    SCREEN_resource("resource");

    private String text;

    private Screen(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
