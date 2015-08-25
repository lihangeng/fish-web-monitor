package com.yihuacomputer.fish.api.atm;

import java.util.List;

import com.yihuacomputer.fish.api.device.CashType;
import com.yihuacomputer.fish.api.device.TypeStatus;

/**
 * 设备型号
 * @author xuxiang
 *
 */
public interface IAtmType
{
    public long getId();

    public void setId(long id);

    public String getName();

    public void setName(String name);

    public IAtmVendor getDevVendor();

    /**
     * 设置所属的设备品牌
     * @param devVendor
     */
    public void setDevVendor(IAtmVendor devVendor);

    /**
     * 设置所属的设备类型
     * @return
     */
    public IAtmCatalog getDevCatalog();

    public void setDevCatalog(IAtmCatalog devCatalog);

    public String getSpec();

    public void setSpec(String spec);

    public String getWeight();

    public void setWeight(String weight);

    public String getWatt();

    public void setWatt(String watt);

    public CashType getCashtype();

    /**
     * 设置现金类型标识
     * @param cashtype
     */
    public void setCashtype(CashType cashtype);

    /**
     * 获得该设备型号包含设备模块
     * @return
     */
    public List<IAtmModule> getAtmModules();

    /**
     * 设置该设备型号包含设备模块
     * @return
     */
    public void setAtmModules(List<IAtmModule> atmModules);

    /**
     * 增加一个设备模块
     * @param atmModule
     */
    public void addAtmModule(IAtmModule atmModule);

    /**
     * 删除一个设备模块
     * @param atmModule
     */
    public void removeAtmModule(IAtmModule atmModule);

    /**
     * 备注
     * @return
     */
    public String getRemark();

    public void setRemark(String remark);

    /**
     * 状态
     * @return
     */
    public TypeStatus getTypeStatus();

    public void setTypeStatus(TypeStatus typeStatus);
}
