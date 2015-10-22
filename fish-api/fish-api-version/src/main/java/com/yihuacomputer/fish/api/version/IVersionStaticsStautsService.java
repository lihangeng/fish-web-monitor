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
	 * 根据版本Id，和当前人所在机构获取版本下发概况信息(图表)
	 * @param versionId
	 * @param orgFlag
	 * @return
	 */
	List<ChartsInfo> getVersionSummaryInfo(long versionId,String orgFlag,int start,int limit);
	
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
	
	public IPageResult<VersionChartsDetailForm> getMatchConditionDeviceSuccess(long versionId,String orgFlag,int start,int limit);
    public IPageResult<VersionChartsDetailForm> getMatchConditionDeviceTotal(long versionId,String orgFlag,int start,int limit);
    public IPageResult<VersionChartsDetailForm> getMatchConditionDeviceFatal(long versionId,String orgFlag,int start,int limit);
    public IPageResult<VersionChartsDetailForm> getMatchConditionDevicePush(long versionId,String orgFlag,int start,int limit);

}
