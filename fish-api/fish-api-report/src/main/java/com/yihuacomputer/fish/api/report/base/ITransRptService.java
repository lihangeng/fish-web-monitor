package com.yihuacomputer.fish.api.report.base;

import java.util.List;

import com.yihuacomputer.common.IFilter;

/**
 * 交易相关统计、图表服务
 * @author YiHua
 *
 */
public interface ITransRptService {

	/**
	 * 机构分类统计
	 * @return
	 */
	public List<ITransCountRpt> listOrgTransCount(IFilter filter);
	
	/**
     * 机构分类统计
     * @return
     */
    public List<ITransCountRpt> listDeviceTransCount(IFilter filter);
    
	
	/**
	 * 交易结果统计
	 * @return
	 */
	public List<ITransResultCountRpt> listTransResultCount(IFilter filter);

}
