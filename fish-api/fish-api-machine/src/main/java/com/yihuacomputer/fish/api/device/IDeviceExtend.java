package com.yihuacomputer.fish.api.device;

import java.util.Date;

/**
 * 设备扩展信息
 * 
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午02:11:38
 */
public interface IDeviceExtend {
	public long getId();

	public void setId(long id);

	public String getTerminalId();

	public void setTerminalId(String terminalId);

	public String getSerial();

	public void setSerial(String serial);

	public Date getBuyDate();

	public void setBuyDate(Date buyDate);

	public Date getInstallDate();

	public void setInstallDate(Date installDate);

	public Date getStartDate();

	public void setStartDate(Date startDate);

	public Date getStopDate();

	public void setStopDate(Date stopDate);

	public String getOpenTime();

	public void setOpenTime(String openTime);

	public String getCloseTime();

	public void setCloseTime(String closeTime);

	public Date getExpireDate();

	public void setExpireDate(Date expireDate);

	public int getPatrolPeriod();

	public void setPatrolPeriod(int patrolPeriod);

	public Date getLastPmDate();

	public void setLastPmDate(Date lastPmDate);

	public Date getExpirePmDate();

	public void setExpirePmDate(Date expirePmDate);

	public String getCarrier();

	public void setCarrier(String carrier);

	public String getMoneyOrg();

	public void setMoneyOrg(String moneyOrg);

	public double getPrice();

	public void setPrice(double price);

	public double getDepreciationLife();

	public void setDepreciationLife(double depreciationLife);

	public double getDecoration();

	public void setDecoration(double decoration);

	public double getDecorationCost();

	public void setDecorationCost(double decorationCost);

	public double getGovernanceRent();

	public void setGovernanceRent(double governanceRent);

	public double getGovernanceCost();

	public void setGovernanceCost(double governanceCost);

	public double getNetCost();

	public void setNetCost(double netCost);

	public double getPowerCost();

	public void setPowerCost(double powerCost);

	public double getMoneyCost();

	public void setMoneyCost(double moneyCost);

	public String getCostInterest();

	public void setCostInterest(String costInterest);

	public NetType getNetType();

	public void setNetType(NetType netType);

	public IDevice getDevice();

	public void setDevice(IDevice device);
	
    public String getOrgId();

	public void setOrgId(String orgId) ;

	public String getOffice() ;

	public void setOffice(String office) ;

	public String getInstallWay() ;

	public void setInstallWay(String installWay) ;

	public String getDeviceModel() ;

	public void setDeviceModel(String deviceModel) ;

	public String getAddress() ;

	public void setAddress(String address) ;
}
