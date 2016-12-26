package com.yihuacomputer.fish.api.monitor.xfs;

import java.util.List;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.report.IStatusMonitorMapOrg;
import com.yihuacomputer.fish.api.monitor.report.IStatusReport;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IXfsPropertise;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

/**
 * @author YiHua
 *
 */
public interface IXfsService {

    /**
     * 初始化设备状态
     * 
     * @param terminalId
     * @return
     */
    public IXfsStatus initXfsStatus(String terminalId);

    /**
     * 初始化设备属性
     * 
     * @param terminalId
     */
    public void initXfsProp(String terminalId);

    /**
     * 创建设备状态实体
     * 
     * @return
     */
    public IXfsStatus makeXfsStatus();
    
    /**
     * 创建离线设备状态实体
     * @return
     */
    public IXfsStatus makeOfflineXfsStatus();

    /**
     * 创建设备属性实体
     * 
     * @return
     */
    public IXfsPropertise makeXfsPropertise();

    /**
     * 保存设备状态信息
     * 
     * @param status
     */
    public void saveXfsStatus(IXfsStatus status);

    /**
     * 删除设备状态信息
     * 
     * @param status
     */
    public void deleteXfsStatus(IXfsStatus status);

    /**
     * 保存设备属性信息
     * 
     * @param prop
     */
    public void saveXfsProp(IXfsPropertise prop);

    /**
     * 删除设备属性信息
     * 
     * @param prop
     */
    public void deleteXfsProp(IXfsPropertise prop);

    /**
     * 取设备状态信息
     * 
     * @param terminalId
     * @return
     */
    public IXfsStatus loadXfsStatus(String terminalId);

    /**
     * 取设备属性信息
     * 
     * @param terminalId
     * @return
     */
    public IXfsPropertise loadXfsProp(String terminalId);

    /**
     * 更新设备属性信息
     * 
     * @param prop
     */
    public void updateXfsProp(IXfsPropertise prop);

    /**
     * 更新设备状态信息
     * 
     * @param status
     */
    public void updateXfsStatus(IXfsStatus status);

    /**
     * 根据设备ID和模块ID得到模块状态码
     * 
     * @param terminalId
     * @param mod
     * @return
     */
    public String getStatusCode(String terminalId, DeviceMod mod);

    /**
     * 批量获取设备状态信息
     * 
     * @param offset
     * @param limit
     * @param statusFilter
     * @return
     */
    public IPageResult<IStatusReport> pageStatus(int offset, int limit, IStatusFilter statusFilter);

    /**
     * 批量获取设备状态信息及地图信息
     * 
     * @param offset
     * @param limit
     * @param statusFilter
     * @param isMap
     *            是否需要地图信息
     * @return
     */
    public IPageResult<IStatusReport> pageStatus(int offset, int limit, IStatusFilter statusFilter, boolean isMap);

    /**
     * 批量获取固定时间内设备状态未更新的设备
     * 
     * @param offset
     * @param limit
     * @param dateTime
     * @return
     */
    public List<? extends Object> loadOfflineStatus(int offset, int limit, String dateTime);

    /**
     * 获取监控地图时的机构数据
     * 
     * @param offset
     * @param limit
     * @param orgId
     *            机构ID
     * @return
     */
    public IPageResult<IStatusMonitorMapOrg> pageStatusMapOrg(int offset, int limit, String orgId);
}
