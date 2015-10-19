package com.yihuacomputer.fish.api.advert;

/**
 * 屏幕分辨率
 * @author xuxigang
 *
 */
public enum Screen {
    SCREEN_1024("Screen.SCREEN_1024"),
    SCREEN_800("Screen.SCREEN_800"),
    SCREEN_600("Screen.SCREEN_600"),
    SCREEN_video("Screen.SCREEN_video"),
    SCREEN_resource("Screen.SCREEN_resource");

    private String text;

    private Screen(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
