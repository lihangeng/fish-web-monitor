package com.yihuacomputer.fish.api.fault;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;

/**
 * @author YiHua
 *
 */
public interface ICaseFaultService {

	/**
	 * 创建实体对象
	 * @return
	 */
	public ICaseFault make();

	/**
	 * 根据Id获得故障信息
	 * @param id
	 * @return
	 */
	public ICaseFault getFault(long id);

	/**
	 * 保存故障信息
	 * @param caseFault
	 */
	public void save(ICaseFault caseFault);

	/**
	 * 更新故障信息
	 * @param caseFault
	 */
	public void update(ICaseFault caseFault);

	/**
	 * 得到当前设备
	 * @param terminalId
	 * @return
	 */
	public List<ICaseFault> listOpenCaseFault(String terminalId);

	/**
	 * 创建故障通知
	 * @param caseFault
	 */
	public void createCaseFault(ICaseFault caseFault);

	/**
	 * 关闭故障信息
	 * @param caseFault
	 */
	public void closeCaseFault(ICaseFault caseFault);

	/**
	 * 根据条件得到故障列表
	 * @param filter
	 * @return
	 */
	public List<ICaseFault> list(IFilter filter);

	/**
	 * 分页显示故障列表
	 * @param offset
	 * @param limit
	 * @param filter
	 * @param orgId
	 * @return
	 */
	public IPageResult<ICaseFault> page(int offset, int limit, IFilter filter,long orgId);

	/**
	 * 判断模块是否已经存在故障
	 * @param openCaseList
	 * @param devMod
	 * @param faultClassify
	 * @return
	 */
	public boolean hasModCaseFault(List<ICaseFault> openCaseList,DeviceMod devMod,IFaultClassify faultClassify);

	/**
	 * 获取模块是否已经存在故障
	 * @param openCaseList
	 * @param faultClassify
	 * @return
	 */
	public ICaseFault getModCaseFault(List<ICaseFault> openCaseList,IFaultClassify faultClassify);


	/**
	 * 模块正常，关闭已经开启的故障
	 * @param openCaseList
	 * @param devMod
	 */
	public void closeHealthyModCase(List<ICaseFault> openCaseList,DeviceMod devMod);

	/**
	 * 模块正常，关闭已经开启的故障
	 * @param openCaseList
	 * @param devMod
	 * @param modType
	 */
	public void closeHealthyModCase(List<ICaseFault> openCaseList,DeviceMod devMod,String modType);

	/**
	 * 根据条件得出故障列表
	 * @param orgId 当前登录用户所属机构
	 * @param filter 条件
	 * @return
	 */
	public List<ICaseFault> list(long orgId, IFilter filter);
}
