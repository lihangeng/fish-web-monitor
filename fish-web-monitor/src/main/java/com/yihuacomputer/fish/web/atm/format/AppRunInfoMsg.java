package com.yihuacomputer.fish.web.atm.format;

import com.yihuacomputer.fish.api.monitor.business.RunStatus;
/**
 * C端应用状态信息
 * @author YiHua
 *
 */
public class AppRunInfoMsg {

	private String termId;
	private RunStatus runStatus;
	
	public void setTermId(String termId){
		this.termId = termId;
	}
	public String getTermId(){
		return this.termId;
	}
	public RunStatus getRunStatus() {
		return runStatus;
	}
	public void setRunStatus(RunStatus runStatus) {
		this.runStatus = runStatus;
	}
	
}
