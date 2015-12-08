package com.yihuacomputer.fish.web.monitor.form;

/**
 * 设备属性fgp
 * 
 * @author wangpeiqi
 * 
 */
public class PropertyFgp {
    /**
     * 是否具有比较特值功能
     */
    private String canCompare;

    /**
     * 获取指纹仪类型
     * 
     * @return 指纹仪类型
     */
    public String variant;

    public String getCanCompare() {
        return canCompare;
    }

    public void setCanCompare(String canCompare) {
        this.canCompare = canCompare;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }
}
