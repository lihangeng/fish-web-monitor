package com.yihuacomputer.fish.api.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.person.IOrganization;

public interface IDeviceService
{
    /**
     * 实例化设备对象
     *
     * @return 设备对象
     */
    public IDevice make();

    /**
     * 通过id,获取设备对象
     *
     * @param id
     *            　设备id
     * @return 设备对象
     */
    public IDevice get(long id);

    /**
     * 通过设备号,获取设备对象
     *
     * @param code
     *            设备号
     * @return　设备对象
     */
    public IDevice get(String code);

    /**
     * 增加设备
     *
     * @param device
     *            增加的设备信息
     * @return　增加的设备信息
     */
    public IDevice add(IDevice device);

    /**
     * 删除设备
     *
     * @param id
     *            设备id
     */
    public void remove(long id);

    /**
     * 设备设备
     *
     * @param code
     *            设备号
     */
    public void remove(String code);

    /**
     * 修改设备
     *
     * @param device
     *            需要修改的设备
     */
    public void update(IDevice device);

    /**
     * 获取所有的设备信息
     *
     * @return　设备集合
     */
    public List<IDevice> list();

    /**
     * 获取符合条件的设备
     *
     * @param filter
     *            条件
     * @return　设备集合
     */
    public List<IDevice> list(IFilter filter);

    /**
     * 获取符合条件的设备
     *
     * @param hql SQL语句
     * @param fixedFilters　值
     * @return
     */
    public List<IDevice> list(String hql, List<Object> fixedFilters);

    /**
     * 获取机构下符合条件的设备
     *
     * @param filter
     *            条件
     * @param id
     *            　机构
     * @return　设备集合
     */
    public List<IDevice> orgList(IFilter filter, long id);

    /**
     * 获取设备的分页信息
     *
     * @param offset
     *            开始
     * @param limit
     *            结束
     * @param filter
     *            过滤
     * @return　分页信息　
     */
    public IPageResult<IDevice> page(int offset, int limit, IFilter filter);

    /**
     * 分页获取机构下所有的设备信息
     *
     * @param offset
     *            开始
     * @param limit
     *            结束
     * @param filter
     *            条件
     * @param orgId
     *            机构id
     * @return 分页设备信息
     */
    public IPageResult<IDevice> page(int offset, int limit, IFilter filter,
            String orgId);

    /**
     * 根据设备型号找出符合条件的设备信息
     * @param atmType
     * @return
     */
    public List<IDevice> listDeviceByType(IAtmType atmType);

    /**
     * 得到开通的设备数量
     * @return
     */
    public long getOpeningDeviceTotal();

    /**
     * 得到当前机构及子机构下所有开通的设备数量
     * @param org
     * @return
     */
    public long getOpeningDeviceTotal(IOrganization org);

    /**
     * 增加设备监听器
     * @since 1.4.0
     * @param deviceListener
     */
    public void addDeviceListener(IDeviceListener deviceListener);
    /**
     * 删除设备监听器
     * @since 1.4.0
     * @param deviceListener
     */
    public void removeDeviceListener(IDeviceListener deviceListener);

    /**
     * 通过设备ip,获取设备对象
     *
     * @param code
     *            设备号
     * @return　设备对象
     */
    public IDevice getByIp(String ip);


    /**
     * 通过设备虚拟柜员号,获取设备对象
     *
     * @param code
     *            设备号
     * @return　设备对象
     */
    public IDevice getByVirtual(String virtual);
}
