package com.yihuacomputer.fish.api.monitor.software;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.util.PageResult;

/**
 * @author YiHua
 *
 */
public interface IRuntimeParamService {

    /**
     * 实例化设备运行参数对象
     * 
     * @return
     */
    public IDeviceParam make();

    /**
     * 通过id获取记录
     * 
     * @param id
     * @return
     */
    public IDeviceParam get(long id);

    /**
     * 保存设备参数
     * 
     * @param param
     */
    public void save(IDeviceParam param);

    /**
     * 修改设备参数
     * 
     * @param param
     */
    public void update(IDeviceParam param);

    /**
     * 通过设备号获取参数
     * 
     * @param terminalId
     *            设备号
     * @return
     */
    public IDeviceParam get(String terminalId);

    /**
     * 通过设备号获取参数列表
     * 
     * @return
     */
    public List<IDeviceParam> list();

    /**
     * 通过条件获取参数列表
     * 
     * @param filter
     *            条件
     * @return
     */
    public List<IDeviceParam> list(IFilter filter);

    /**
     * 设备参数列表分页
     * 
     * @param offset
     *            开始
     * @param limit
     *            结束
     * @param filter
     *            条件
     * @return
     */
    public PageResult<IDeviceParam> page(int offset, int limit, IFilter filter);

    /**
     * 通过设备号删除记录
     * 
     * @param terminalId
     *            设备号
     */
    public void remove(String terminalId);

    /**
     * 通过ID删除记录
     * 
     * @param id
     *            ID
     */
    public void remove(long id);

    /**
     * 根据设备号统计
     * 
     * @param terminalId
     *            设备号
     * @return
     */
    public int count(String terminalId);

    /**
     * 设备号分组
     * 
     * @param terminalId
     *            条件(default null)
     * @return
     */
    public List<IDeviceParam> listGroupByTerminalId(String terminalId);

    /**
     * 根据机构，查询此机构下所有设备
     * 
     * @param terminalId
     *            设备号
     * @param orgId
     *            机构ID
     * @return
     */
    public List<IDeviceParam> listGroupByTerminalId(String terminalId, String orgId);

}
