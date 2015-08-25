package com.yihuacomputer.fish.web.monitor.form;
public class PropertyTtu
{
    /**
     * 是否支持字母数字键输入
     */
    private String alphanumericKeysPresent;

    /**
     * 是否支持十六进制键输入
     */
    private String hexadecimalKeysPresent;

    /**
     * 是否支持数字键输入
     */
    private String numericKeysPresent;

    /**
     * 是否支持键盘锁定
     */
    private String keyboardLockPresent;

    /**
     * 是否支持屏幕亮度调节
     */
    private String displayLightPresent;

    /**
     * 是否支持鼠标
     */
    private String cursorSupported;

    /**
     * 是否支持FORM
     */
    private String formsSupported;

    /**
     * 获取分辨率X值
     */
    private int resolutionX;

    /**
     * 获取分辨率Y值
     */
    private int resolutionY;

    public String getAlphanumericKeysPresent()
    {
        return alphanumericKeysPresent;
    }

    public void setAlphanumericKeysPresent(String alphanumericKeysPresent)
    {
        this.alphanumericKeysPresent = alphanumericKeysPresent;
    }

    public String getHexadecimalKeysPresent()
    {
        return hexadecimalKeysPresent;
    }

    public void setHexadecimalKeysPresent(String hexadecimalKeysPresent)
    {
        this.hexadecimalKeysPresent = hexadecimalKeysPresent;
    }

    public String getNumericKeysPresent()
    {
        return numericKeysPresent;
    }

    public void setNumericKeysPresent(String numericKeysPresent)
    {
        this.numericKeysPresent = numericKeysPresent;
    }

    public String getKeyboardLockPresent()
    {
        return keyboardLockPresent;
    }

    public void setKeyboardLockPresent(String keyboardLockPresent)
    {
        this.keyboardLockPresent = keyboardLockPresent;
    }

    public String getDisplayLightPresent()
    {
        return displayLightPresent;
    }

    public void setDisplayLightPresent(String displayLightPresent)
    {
        this.displayLightPresent = displayLightPresent;
    }

    public String getCursorSupported()
    {
        return cursorSupported;
    }

    public void setCursorSupported(String cursorSupported)
    {
        this.cursorSupported = cursorSupported;
    }

    public String getFormsSupported()
    {
        return formsSupported;
    }

    public void setFormsSupported(String formsSupported)
    {
        this.formsSupported = formsSupported;
    }

    public int getResolutionX()
    {
        return resolutionX;
    }

    public void setResolutionX(int resolutionX)
    {
        this.resolutionX = resolutionX;
    }

    public int getResolutionY()
    {
        return resolutionY;
    }

    public void setResolutionY(int resolutionY)
    {
        this.resolutionY = resolutionY;
    }
}
