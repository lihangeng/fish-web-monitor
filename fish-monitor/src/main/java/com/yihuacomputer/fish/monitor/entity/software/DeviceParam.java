package com.yihuacomputer.fish.monitor.entity.software;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.software.IDeviceParam;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_RUNTIMEPARAM")
public class DeviceParam implements IDeviceParam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_RUNTIMEPARAM")
    @SequenceGenerator(name = "SEQ_DEV_RUNTIMEPARAM", sequenceName = "SEQ_DEV_RUNTIMEPARAM")
    @Column(name = "ID")
    private long id;

    @Column(name = "TERMINAL_ID", length = 20)
    private String terminalId;

    @Column(name = "DEV_MODULE", length = 20)
    private String module;

    @Column(name = "DEV_KEY", length = 50)
    private String key;

    @Column(name = "DEV_VALUE", length = 50)
    private String value;

    @Column(name = "DEFAULT_VALUE", length = 50)
    private String defaultValue;

    @Column(name = "DEV_LABEL", length = 128)
    private String label;


    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTerminalId() {
        return terminalId;
    }

    @Override
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String getModule() {
        return module;
    }

    @Override
    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }
}
