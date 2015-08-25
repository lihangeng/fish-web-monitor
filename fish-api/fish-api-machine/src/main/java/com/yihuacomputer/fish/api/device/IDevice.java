package com.yihuacomputer.fish.api.device;

import java.util.Date;

import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IUser;

/**
 * 设备基本信息
 *
 * @author yantao
 *
 */
public interface IDevice {
	public long getId();

	public void setId(long id);

	public String getTerminalId();

	public void setTerminalId(String terminalId);

	public ITypeIP getIp();

	public void setIp(ITypeIP ip);

	public IAtmType getDevType();

	public void setDevType(IAtmType devType);

	public Status getStatus();

	public void setStatus(Status status);

	public IOrganization getDevService();

	public void setDevService(IOrganization devService);

	public String getAddress();

	public void setAddress(String address);

	public int getCashboxLimit();

	public void setCashboxLimit(int cashboxLimit);

	public IDeviceExtend getDeviceExtend();

	public void setDeviceExtend(IDeviceExtend deviceExtend);

	public void update(IDevice device);

	public void setOrganization(IOrganization organization);

	public IOrganization getOrganization();

	public CashType getCashType();

	public void setCashType(CashType cashType);

	public SetupType getSetupType();

	public void setSetupType(SetupType setupType);

	public String getAtmcSoft();

	public void setAtmcSoft(String atmcSoft);

	public String getSp();

	public void setSp(String sp);

	public AwayFlag getAwayFlag();

	public void setAwayFlag(AwayFlag awayFlag);

	public WorkType getWorkType();

	public void setWorkType(WorkType workType);

	public CareLevel getCareLevel();

	public void setCareLevel(CareLevel careLevel);
    /*
     * 虚拟柜员号
     */
	public String getVirtual();

	public void setVirtual(String Virtual);
	/*
     * 巡检周期在设备扩展表中
     */

    /*
     * mac地址
     */
	public String getMac();

	public void setMac(String mac);

	/*
     * 是否支持视频播放
     */
	public String getVideoType();

	public void setVideoType(String videoType);

	/*获得申请人*/
	public IUser getApplyId();

	/*设置申请人*/
	public void setApplyId(IUser applyId);

	/*获得申请时间*/
	public Date getApplyTime();

	/*设置申请时间*/
	public void setApplyTime(Date applyTime);

	/*获得审核人*/
	public IUser getAuditerId();

	/*设置审核人*/
	public void setAuditerId(IUser auditerId);

	/*获得审核时间*/
	public Date getAudTime();

	/*设置审核时间*/
	public void setAudTime(Date audTime);

	/*设置布放位置类型*/
	public PlaceType getPlaceType();

	/*获得布放位置类型*/
	public void setPlaceType(PlaceType placeType);

	/*
     * 申请备注
     */
	public String getApplyRemark();

	public void setApplyRemark(String applyRemark);

	/*
     * 审核备注
     */
	public String getAuditerRemark();

	public void setAuditerRemark(String auditerRemark);

	/*
     * 审核状态
     */
	public Status getCheckStatus();

	public void setCheckStatus(Status checkStatus);

}
