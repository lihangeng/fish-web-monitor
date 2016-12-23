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
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @return
	 */
	public String getTerminalId();

	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * @return
	 */
	public String getSerial();

	/**
	 * @param serial
	 */
	public void setSerial(String serial);

	/**
	 * @return
	 */
	public Date getBuyDate();

	/**
	 * @param buyDate
	 */
	public void setBuyDate(Date buyDate);

	/**
	 * @return
	 */
	public Date getInstallDate();

	/**
	 * @param installDate
	 */
	public void setInstallDate(Date installDate);

	/**
	 * @return
	 */
	public Date getStartDate();

	/**
	 * @param startDate
	 */
	public void setStartDate(Date startDate);

	/**
	 * @return
	 */
	public Date getStopDate();

	/**
	 * @param stopDate
	 */
	public void setStopDate(Date stopDate);

	/**
	 * @return
	 */
	public String getOpenTime();

	/**
	 * @param openTime
	 */
	public void setOpenTime(String openTime);

	/**
	 * @return
	 */
	public String getCloseTime();

	/**
	 * @param closeTime
	 */
	public void setCloseTime(String closeTime);

	/**
	 * @return
	 */
	public Date getExpireDate();

	/**
	 * @param expireDate
	 */
	public void setExpireDate(Date expireDate);

	/**
	 * @return
	 */
	public int getPatrolPeriod();

	/**
	 * @param patrolPeriod
	 */
	public void setPatrolPeriod(int patrolPeriod);

	/**
	 * @return
	 */
	public Date getLastPmDate();

	/**
	 * @param lastPmDate
	 */
	public void setLastPmDate(Date lastPmDate);

	/**
	 * @return
	 */
	public Date getExpirePmDate();

	/**
	 * @param expirePmDate
	 */
	public void setExpirePmDate(Date expirePmDate);

	/**
	 * @return
	 */
	public String getCarrier();

	/**
	 * @param carrier
	 */
	public void setCarrier(String carrier);

	/**
	 * @return
	 */
	public String getMoneyOrg();

	/**
	 * @param moneyOrg
	 */
	public void setMoneyOrg(String moneyOrg);

	/**
	 * @return
	 */
	public double getPrice();

	/**
	 * @param price
	 */
	public void setPrice(double price);

	/**
	 * @return
	 */
	public double getDepreciationLife();

	/**
	 * @param depreciationLife
	 */
	public void setDepreciationLife(double depreciationLife);

	/**
	 * @return
	 */
	public double getDecoration();

	/**
	 * @param decoration
	 */
	public void setDecoration(double decoration);

	/**
	 * @return
	 */
	public double getDecorationCost();

	/**
	 * @param decorationCost
	 */
	public void setDecorationCost(double decorationCost);

	/**
	 * @return
	 */
	public double getGovernanceRent();

	/**
	 * @param governanceRent
	 */
	public void setGovernanceRent(double governanceRent);

	/**
	 * @return
	 */
	public double getGovernanceCost();

	/**
	 * @param governanceCost
	 */
	public void setGovernanceCost(double governanceCost);

	/**
	 * @return
	 */
	public double getNetCost();

	/**
	 * @param netCost
	 */
	public void setNetCost(double netCost);

	/**
	 * @return
	 */
	public double getPowerCost();

	/**
	 * @param powerCost
	 */
	public void setPowerCost(double powerCost);

	/**
	 * @return
	 */
	public double getMoneyCost();

	/**
	 * @param moneyCost
	 */
	public void setMoneyCost(double moneyCost);

	/**
	 * @return
	 */
	public String getCostInterest();

	/**
	 * @param costInterest
	 */
	public void setCostInterest(String costInterest);

	/**
	 * @return
	 */
	public NetType getNetType();

	/**
	 * @param netType
	 */
	public void setNetType(NetType netType);

	/**
	 * @return
	 */
	public IDevice getDevice();

	/**
	 * @param device
	 */
	public void setDevice(IDevice device);
	
    /**
     * @return
     */
    public String getOrgId();

	/**
	 * @param orgId
	 */
	public void setOrgId(String orgId) ;

	/**
	 * @return
	 */
	public String getOffice() ;

	/**
	 * @param office
	 */
	public void setOffice(String office) ;

	/**
	 * @return
	 */
	public String getInstallWay() ;

	/**
	 * @param installWay
	 */
	public void setInstallWay(String installWay) ;

	/**
	 * @return
	 */
	public String getDeviceModel() ;

	/**
	 * @param deviceModel
	 */
	public void setDeviceModel(String deviceModel) ;

	/**
	 * @return
	 */
	public String getAddress() ;

	/**
	 * @param address
	 */
	public void setAddress(String address) ;
}
