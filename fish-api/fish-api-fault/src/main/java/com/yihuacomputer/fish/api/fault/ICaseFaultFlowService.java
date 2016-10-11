package com.yihuacomputer.fish.api.fault;

/**
 * 故障流程处理
 * @author GQ
 *
 */
public interface ICaseFaultFlowService {
	/**
	 * @param caseFault
	 * @param faultClassify
	 */
	void execute(IFaultClassify faultClassify,ICaseFault caseFault);
}
