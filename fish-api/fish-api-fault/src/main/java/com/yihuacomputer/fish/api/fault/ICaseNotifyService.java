package com.yihuacomputer.fish.api.fault;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.person.IPerson;

public interface ICaseNotifyService {

	public ICaseNotify make();

	/**
	 * 保存通知信息
	 * @param caseNotify
	 */
	public void saveCaseNotify(ICaseNotify caseNotify);

	/**
	 * 更新通知信息
	 * @param caseNotify
	 */
	public void updateCaseNotify(ICaseNotify caseNotify);

	/**
	 * 根据故障ID查找对应的故障通知列表
	 *
	 * @param faultId
	 * @return
	 */
	public List<ICaseNotify> listNotifyByFaultId(long faultId);

	/**
	 * 根据设备号日期查找对应的故障通知列表
	 * @param terminalId
	 * @param createDate
	 * @return
	 */
	public List<ICaseNotify> listNotifyByDevice(String terminalId,Date createDate);

	/**
	 * 根据过滤条件查找对应的的故障通知列表
	 *
	 * @param faultId
	 * @return
	 */
	public List<ICaseNotify> listNotify(IFilter filter);

	public IPageResult<ICaseNotify> page(int offset, int limit, IFilter filter,long orgId);

	public List<ICaseNotify> page(IFilter filter,long orgId);

	/**
	 * 创建设备通知内容
	 * @param fauleClassify
	 * @param caseFault
	 */
	public void createCaseNotify(IFaultClassify faultClassify,ICaseFault caseFault);

	public void createCaseNotify(IFaultClassify faultClassify,ICaseFault caseFault,List<IPerson> personList);

	/**
	 * 查询通知类型的故障通知
	 *
	 * @param faultId
	 * @return
	 */
	public List<ICaseNotify> getNotifyWay(IFilter filter);
}
