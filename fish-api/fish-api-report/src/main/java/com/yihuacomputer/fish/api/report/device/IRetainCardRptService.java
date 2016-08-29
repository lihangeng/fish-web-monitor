package com.yihuacomputer.fish.api.report.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;

/**
 * 吞卡明细、统计报表服务接口
 * @author YiHua
 *
 */
public interface IRetainCardRptService {

	/**
	 * 吞卡明细
	 * @return
	 */
	public List<IRetainCardRpt> listRetainCardDetail(IFilter filter);
	
	/**
	 * 吞卡统计
	 * @return
	 */
	public List<IRetainCardCountRpt> listRetainCardCount(IFilter filter);
}
