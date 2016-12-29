package com.yihuacomputer.fish.web.monitor.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.software.IDeviceParam;

/**
 * @author YiHua
 *
 */
public class DeviceParamForm {
    private long id;

    private String terminalId;
    
    private String type;
    
    private String module;

    private String key;

    private String value;

    private String defaultValue;
    
    private String label;

    public DeviceParamForm() {
    }

    /**
     * @param config
     */
    public DeviceParamForm(IDeviceParam config) {
        this.id = config.getId();
        this.terminalId = config.getTerminalId();
        this.key = config.getKey();
        this.value = config.getValue();
        this.defaultValue = config.getDefaultValue();
        this.module = config.getModule();
        this.label = config.getLabel();
    }

    /**
     * @param config
     */
    public void translate(IDeviceParam config) {
        config.setTerminalId(this.terminalId);
        config.setKey(this.key);
        config.setValue(this.value);
        config.setModule(this.module);
        config.setDefaultValue(this.defaultValue);
        config.setLabel(this.label);
    }

    /**
     * @param configs
     * @return
     */
    public static List<DeviceParamForm> convert(List<IDeviceParam> configs) {
        List<DeviceParamForm> result = new ArrayList<DeviceParamForm>();
        for (IDeviceParam config : configs) {
            result.add(new DeviceParamForm(config));
        }
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
