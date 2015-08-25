package com.yihuacomputer.fish.api.fault;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.person.IPerson;

public interface ICaseNotify {

	public long getId();

	public void setId(long id);

	/**
	 * 故障ID
	 * @return
	 */
	public long getFaultId();

	public void setFaultId(long faultId);

	/**
	 * 设备号
	 * @return
	 */
	public String getTerminalId();

	public void setTerminalId(String terminalId);

	/**
	 * 通知创建时间
	 * @return
	 */
	public Date getCreateTime();

	public void setCreateTime(Date createTime);

	/**
	 * 通知内容
	 * @return
	 */
	public String getContent();

	public void setContent(String content);

	/**
	 * 通知方式
	 * @return
	 */
	public NotifyWay getNotifyWay();

	public void setNotifyWay(NotifyWay notifyWay);

	/**
	 * 手机号
	 * @return
	 */
	public String getMobile();

	public void setMobile(String mobile);

	/**
	 * E-Mail
	 * @return
	 */
	public String getMail();

	public void setMail(String mail);

	/**
	 * 通知次数
	 * @return
	 */
	public int getNotifyTimes();

	public void setNotifyTimes(int notifyTimes);

	/**
	 * 发送次数
	 * @return
	 */
	public int getSendTimes();

	public void setSendTimes(int sendTimes);

	/**
	 * 发送时间间隔
	 * @return
	 */
	public int getSendInterval();

	public void setSendInterval(int sendInterval);


	/**
	 * 发送时间
	 * @return
	 */
	public Date getSendTime();

	public void setSendTime(Date sendTime);


	/**
	 * 银行联系人
	 * @return
	 */
	public List<IPerson> getBankPerson();
	public void setBankPerson(List<IPerson> list);

	/**
	 * 维护商联系人
	 * @return
	 */
	public List<IPerson> getSerPerson();
	public void setSerPerson(List<IPerson> list);

	/**
	 * 短信发送人
	 * @return
	 */
	public String getSendPerson();

	public void setSendPerson(String personName);

}
