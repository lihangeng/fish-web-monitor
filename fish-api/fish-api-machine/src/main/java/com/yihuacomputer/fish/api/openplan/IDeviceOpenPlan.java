package com.yihuacomputer.fish.api.openplan;

import java.util.Date;
import java.util.List;

/**
 * @author YiHua
 *
 */
public interface IDeviceOpenPlan {

    /**
     * @return
     */
    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * 方案名称
     * 
     * @return
     */
    public String getName();

    /**
     * @param name
     */
    public void setName(String name);

    /**
     * 有效开始时间
     * 
     * @return
     */
    public Date getStartDate();

    /**
     * @param startDate
     */
    public void setStartDate(Date startDate);

    /**
     * 有效结束时间
     * 
     * @return
     */
    public Date getEndDate();

    /**
     * @param endDate
     */
    public void setEndDate(Date endDate);

    /**
     * 方案描述
     * 
     * @return
     */
    public String getDesc();

    /**
     * @param desc
     */
    public void setDesc(String desc);

    /**
     * 方案类型
     * 
     * @return
     */
    public PlanType getPlanType();

    /**
     * @param planType
     */
    public void setPlanType(PlanType planType);

    /**
     * 方案状态
     * 
     * @return
     */
    public PlanState getPlanState();

    /**
     * @param planState
     */
    public void setPlanState(PlanState planState);

    /**
     * 获取方法详情
     * 
     * @return
     */
    public List<IOpenPlanDetail> getOpenPlanDetail();

    /**
     * @param details
     */
    public void setOpenPlanDetail(List<IOpenPlanDetail> details);

    /**
     * 方案创建时间
     * 
     * @return
     */
    public String getCreateDateTime();

    /**
     * @param createDateTime
     */
    public void setCreateDateTime(String createDateTime);
    
    /**
     * @return
     */
    public int getDeviceCount();

    /**
     * @param deviceCount
     */
    public void setDeviceCount(int deviceCount);
    
    /**
     * @return
     */
    public PlanStateType getPlanStateType();

    /**
     * @param planStateType
     */
    public void setPlanStateType(PlanStateType planStateType);
}
