package com.yihuacomputer.fish.web.monitor.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数消息
 * 
 * @author pengwenchao
 * 
 */
public class DeviceParamMsg {
    /**
     * 返回码
     */
    private String appRet;

    /**
     * 设备号
     */
    private String terminalId;

    /**
     * 内容(json格式)
     */
    private String content;

    /**
     * 参数
     */
    private Map<String, List<Map<String, String>>> configMap = new HashMap<String, List<Map<String, String>>>();

    public DeviceParamMsg() {
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, List<Map<String, String>>> getConfigMap() {
        return configMap;
    }

    public void setConfigMap(Map<String, List<Map<String, String>>> configMap) {
        this.configMap = configMap;
    }

    public String getAppRet() {
        return appRet;
    }

    public void setAppRet(String appRet) {
        this.appRet = appRet;
    }
}
