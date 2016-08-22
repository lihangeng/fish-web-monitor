package com.yihuacomputer.fish.monitor.entity.box;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInitRule;

@Entity
@Table(name = "DEV_BOX_INIT_RULE")
public class DeviceBoxInitRule implements IDeviceBoxInitRule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_BOX_INIT_RULE")
	@SequenceGenerator(name = "SEQ_DEV_BOX_INIT_RULE", sequenceName = "SEQ_DEV_BOX_INIT_RULE")
	@Column(name = "ID")
	private long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "RULE_DESC")
	private String ruleDesc;
	@Column(name = "START_USING")
	private boolean startUsing;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	public boolean isStartUsing() {
		return startUsing;
	}

	public void setStartUsing(boolean startUsing) {
		this.startUsing = startUsing;
	}

}
