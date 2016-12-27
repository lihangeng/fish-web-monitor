package com.yihuacomputer.fish.api.report.device.etl;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;

/**
 * @author GQ
 * 设备按型号进行品牌月统计(cds6040w,cds6040t...)服务
 */
public interface IDeviceTypeSummaryMonthService {
	/**
	 * @return
	 */
	IDeviceTypeSummaryMonth make();
	/**
	 * @param dcsm
	 * @return
	 */
	IDeviceTypeSummaryMonth update(IDeviceTypeSummaryMonth dcsm);
	/**
	 * @param dcsm
	 * @return
	 */
	IDeviceTypeSummaryMonth save(IDeviceTypeSummaryMonth dcsm);
	/**
	 * @param filter
	 * @return
	 */
	List<IDeviceTypeSummaryMonth> list(IFilter filter);
	/**
	 * @param catalogName
	 * @param date
	 * @return
	 */
	IDeviceTypeSummaryMonth get(String catalogName,String date);
	/**
	 * 根据日期得到品牌统计信息
	 * @param date
	 * @return
	 */
	Map<String,IDeviceTypeSummaryMonth> get(String date);

}
