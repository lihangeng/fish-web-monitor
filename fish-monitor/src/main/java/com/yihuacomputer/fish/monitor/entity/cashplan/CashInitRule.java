package com.yihuacomputer.fish.monitor.entity.cashplan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.box.BoxInitRuleType;
import com.yihuacomputer.fish.api.monitor.box.ICashInitRule;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_CASH_INIT_RULE")
public class CashInitRule implements ICashInitRule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_CASH_INIT_RULE")
	@SequenceGenerator(name = "SEQ_DEV_CASH_INIT_RULE", sequenceName = "SEQ_DEV_CASH_INIT_RULE")
	@Column(name = "ID")
	private long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "RULE_DESC")
	private String ruleDesc;
	@Column(name = "START_USING")
	private boolean startUsing;
    @Enumerated(EnumType.STRING)
	@Column(name = "RULE_TYPE")
	private BoxInitRuleType ruleType;
    
    @Override
	public long getId() {
		return id;
	}

    @Override
	public void setId(long id) {
		this.id = id;
	}

    @Override
	public String getName() {
		return name;
	}

    @Override
	public void setName(String name) {
		this.name = name;
	}

    @Override
	public String getRuleDesc() {
		return ruleDesc;
	}

    @Override
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

    @Override
	public boolean isStartUsing() {
		return startUsing;
	}

    @Override
	public void setStartUsing(boolean startUsing) {
		this.startUsing = startUsing;
	}

    @Override
	public BoxInitRuleType getRuleType() {
		return ruleType;
	}

    @Override
	public void setRuleType(BoxInitRuleType ruleType) {
		this.ruleType = ruleType;
	}

}
