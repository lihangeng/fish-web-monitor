package com.yihuacomputer.fish.api.version;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.charts.ChartsInfo;

/**
 * 处理版本图形上数据，及版本图形对应版本明细数据接口
 * @author GQ
 *
 */
public interface IVersionStaticsStautsService {
	
	/**
	 * 根据版本Id，和当前人所在机构获取版本下发明细信息
	 * @param versionId
	 * @param orgFlag
	 * @return
	 */
	List<ChartsInfo> getVersionDetailsInfo(long versionId,String orgFlag);
	
	/**
	 * 获取版本视图详情
	 * @param filter
	 * @return
	 */
	IPageResult<VersionChartsDetailForm> getVersionChartsDetailForm(int start,int limit,IFilter filter);
	
	/**
	 * 根据版本ID找到支持机型的Id
	 * @param versionId
	 * @return
	 */
	public List<Long> getAtmTypeIdsByVersionId(long versionId);
	
	/**
	 * 获取符合条件设备数量(条件一:机构,条件二：设备型号,条件三:版本)
	 * @param orgFlag
	 * @param versionId
	 * @return
	 */
	public long getOpeningDeviceTotal(String orgFlag,long versionId);
}
