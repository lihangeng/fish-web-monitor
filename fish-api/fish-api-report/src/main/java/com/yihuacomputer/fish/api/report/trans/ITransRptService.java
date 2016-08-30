package com.yihuacomputer.fish.api.report.trans;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.fish.api.monitor.business.IHostRet;

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
	
	
	
	/**
	 * 查询所有返回码
	 * @return
	 */
	public List<IHostRet> listHostRetCode();

}
