package com.yihuacomputer.fish.api.report.trans;

/**
 * 交易结果统计
 * @author YiHua
 *
 */
public interface ITransResultCountRpt {

	/**
	 * 机构名称
	 * @return
	 */
	public String getOrgName();
	
	/**
	 * 交易结果
	 * @return
	 */
	public String getResult();
	
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
    
    
    
    /**
     *  设置结果
     * @param result
     */
    public void setResult(String result);
	

}
