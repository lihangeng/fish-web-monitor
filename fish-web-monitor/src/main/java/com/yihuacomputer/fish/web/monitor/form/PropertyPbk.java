package com.yihuacomputer.fish.web.monitor.form;

/**
 * @author YiHua
 *
 */
public class PropertyPbk {
    /**
     * 是否具有退纸能力
     *
     */
    public String canEject;

    /**
     * 是否具有回收能力
     *
     */
    public String canCapture;

    /**
     * 是否具有暂存能力
     *
     */
    public String canStack;

    public String getCanEject() {
        return canEject;
    }

    public void setCanEject(String canEject) {
        this.canEject = canEject;
    }

    public String getCanCapture() {
        return canCapture;
    }

    public void setCanCapture(String canCapture) {
        this.canCapture = canCapture;
    }

    public String getCanStack() {
        return canStack;
    }

    public void setCanStack(String canStack) {
        this.canStack = canStack;
    }
}
