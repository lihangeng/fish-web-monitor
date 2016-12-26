package com.yihuacomputer.fish.api.quittingNotice;

/**
 * 报停
 */
import java.util.Date;

import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.StopType;

/**
 * @author YiHua
 *
 */
public interface IQuittingNotice
{
    /**
     * @param id
     */
    public void setId(long id);

    /**
     * @return
     */
    public long getId();
    /**
     * 设备编号
     * @param deviceCode
     */
    public void setDeviceCode(String deviceCode);

    /**
     * @return
     */
    public String getDeviceCode();
    /**
     * 停机时间
     * @param stopTime
     */
    public void setStopTime(Date stopTime);
    /**
     *
     * @return
     */
    public Date getStopTime();
    /**
     * 恢复时间
     * @param openTime
     */
    public void setOpenTime(Date openTime);

    /**
     * @return
     */
    public Date getOpenTime();
    /**
     * 停机类型
     * @param stopType
     */
    public void setStopType(StopType stopType);

    /**
     * @return
     */
    public StopType getStopType();
    /**
     * 停机原因
     * @param stopReason
     */
    public void setStopReason(String stopReason);

    /**
     * @return
     */
    public String getStopReason();
    /**
     * 设置时间
     * @param setTime
     */
    public void setSetTime(Date setTime);

    /**
     * @return
     */
    public Date getSetTime();
    /**
     * 责任人
     * @param responsibilityName
     */
    public void setResponsiblilityName(String responsibilityName);

    /**
     * @return
     */
    public String getResponsibilityName();

    /**
     * 设置停机状态
     * @return
     */
	public DevStatus getDevStatus();

	/**
	 * @param devStatus
	 */
	public void setDevStatus(DevStatus devStatus);

}
