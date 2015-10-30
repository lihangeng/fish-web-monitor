package com.yihuacomputer.fish.api.openplan;

import java.util.Date;
import java.util.List;

public interface IDeviceOpenPlan {

    public long getId();

    public void setId(long id);

    /**
     * 方案名称
     * 
     * @return
     */
    public String getName();

    public void setName(String name);

    /**
     * 有效开始时间
     * 
     * @return
     */
    public Date getStartDate();

    public void setStartDate(Date startDate);

    /**
     * 有效结束时间
     * 
     * @return
     */
    public Date getEndDate();

    public void setEndDate(Date endDate);

    /**
     * 方案描述
     * 
     * @return
     */
    public String getDesc();

    public void setDesc(String desc);

    /**
     * 方案类型
     * 
     * @return
     */
    public PlanType getPlanType();

    public void setPlanType(PlanType planType);

    /**
     * 方案状态
     * 
     * @return
     */
    public PlanState getPlanState();

    public void setPlanState(PlanState planState);

    /**
     * 获取方法详情
     * 
     * @return
     */
    public List<IOpenPlanDetail> getOpenPlanDetail();

    public void setOpenPlanDetail(List<IOpenPlanDetail> details);

    /**
     * 方案创建时间
     * 
     * @return
     */
    public String getCreateDateTime();

    public void setCreateDateTime(String createDateTime);
    
    public int getDeviceCount();

    public void setDeviceCount(int deviceCount);
    
    public PlanStateType getPlanStateType();

    public void setPlanStateType(PlanStateType planStateType);
}
