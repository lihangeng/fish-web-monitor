package com.yihuacomputer.fish.monitor.entity.xfs;

import com.yihuacomputer.fish.api.monitor.xfs.IStateAnalysis;

/**
 * @author YiHua
 *
 */
public class StateAnalysis implements IStateAnalysis {
	private String description;
	private String solution;

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getSolution() {
		return this.solution;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public void setSolution(String solution){
		this.solution = solution;
	}
}
