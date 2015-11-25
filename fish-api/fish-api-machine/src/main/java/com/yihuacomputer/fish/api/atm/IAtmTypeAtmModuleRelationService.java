package com.yihuacomputer.fish.api.atm;

import java.util.List;

/**
 * 设备型号和设备模块关联服务
 * 
 * @author YH
 *
 */
public interface IAtmTypeAtmModuleRelationService {
    /**
     * 建立关联
     * 
     * @param atmModuleId
     * @param atmTypeId
     */
    public void link(long atmModuleId, long atmTypeId);

    /**
     * 取消关联
     * 
     * @param atmModuleId
     * @param atmTypeId
     */
    public void unlink(long atmModuleId, long atmTypeId);

    /**
     * 获取版本所包含的模块
     * 
     * @param atmTypeId
     * @return
     */
    public List<IAtmModule> findAtmModules(long atmTypeId);

    public List<Long> findAtmModuleIds(long atmTypeId);

    /**
     * 
     * @param atmTypeId
     * @param atmmoduleIds
     */
    public void link(long atmTypeId, List<Long> atmModuleIds);

    /**
     * 根据设备Id获得设备模块信息
     * 
     * @param atmTypeId
     * @return
     */
    public List<Long> getAtmModuleIdsByatmTypeId(long atmTypeId);

    /**
     * 查询所有型号与模块的关联信息<BR>
     * 
     * @return Object是一个数组,0=AtmType,1=AtmModule,2=AtmTypeAtmModuleRelation
     */
    public List<Object> list();

}
