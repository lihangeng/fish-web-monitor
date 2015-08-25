package com.yihuacomputer.fish.api.monitor.software;

/**
 * 设备参数
 * 
 * @author YiHua
 * 
 */
public interface IDeviceParam {

    public long getId();

    public void setId(long id);

    /**
     * 获取设备号
     * 
     * @return
     */
    public String getTerminalId();

    public void setTerminalId(String terminalId);

    /**
     * 获取参数
     * 
     * @return
     */
    public String getKey();

    public void setKey(String key);

    /**
     * 获取值
     * 
     * @return
     */
    public String getValue();

    public void setValue(String value);

    /**
     * 默认值
     * 
     * @return
     */
    public String getDefaultValue();

    public void setDefaultValue(String defaultValue);

    /**
     * 模块
     * 
     * @return
     */
    public String getModule();

    public void setModule(String module);

    /**
     * 名称
     * 
     * @return
     */
    public String getLabel();

    public void setLabel(String label);
}
