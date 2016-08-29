package com.yihuacomputer.fish.api.report.transaction;

/**
 * ATMC交易分类统计
 * @author YiHua
 *
 */
public interface ITransCountRpt {
    
    /**
     * 设备号
     * @return
     */
    public String getTerminalId();
                  
	/**
	 * 机构名称
	 * @return
	 */
	public String getOrgName();
		
	/**
	 * 交易类型
	 * @return
	 */
	public String getTransType();
	

	/**
	 * 统计名称
	 * @return
	 */
	public String getCountName();

	/**
	 *  统计数
	 * @return
	 */
	public double getTransCount();
	

}
