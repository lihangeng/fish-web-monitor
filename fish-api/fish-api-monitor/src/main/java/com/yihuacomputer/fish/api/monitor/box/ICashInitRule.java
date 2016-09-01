package com.yihuacomputer.fish.api.monitor.box;

public interface ICashInitRule {
	public long getId();
	public void setId(long id);
	
	/**
	 * 预警规则名称
	 * @return
	 */
	public String getName();
	/**
	 * 预警规则名称
	 * @param name
	 */
	public void setName(String name);
	/**
	 * 预警规则描述
	 * @return
	 */
	public String getRuleDesc();
	/**
	 * 预警规则描述
	 * @param ruleDesc
	 */
	public void setRuleDesc(String ruleDesc);
	/**
	 * 是否启用
	 * @return
	 */
	public boolean isStartUsing();
	/**
	 * 是否启用
	 * @param startUsing
	 */
	public void setStartUsing(boolean startUsing);
	/**
	 * 规则类型
	 * @return
	 */
	public BoxInitRuleType getRuleType();
	/**
	 * 规则类型
	 * @param ruleType
	 */
	public void setRuleType(BoxInitRuleType ruleType);
	
}
