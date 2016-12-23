package com.yihuacomputer.fish.api.fault;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.person.IPerson;

/**
 * @author YiHua
 *
 */
public interface ICaseNotify {

	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * 故障ID
	 * @return
	 */
	public long getFaultId();

	/**
	 * @param faultId
	 */
	public void setFaultId(long faultId);

	/**
	 * 设备号
	 * @return
	 */
	public String getTerminalId();

	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * 通知创建时间
	 * @return
	 */
	public Date getCreateTime();

	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime);

	/**
	 * 通知内容
	 * @return
	 */
	public String getContent();

	/**
	 * @param content
	 */
	public void setContent(String content);

	/**
	 * 通知方式
	 * @return
	 */
	public NotifyWay getNotifyWay();

	/**
	 * @param notifyWay
	 */
	public void setNotifyWay(NotifyWay notifyWay);

	/**
	 * 手机号
	 * @return
	 */
	public String getMobile();

	/**
	 * @param mobile
	 */
	public void setMobile(String mobile);

	/**
	 * E-Mail
	 * @return
	 */
	public String getMail();

	/**
	 * @param mail
	 */
	public void setMail(String mail);

	/**
	 * 通知次数
	 * @return
	 */
	public int getNotifyTimes();

	/**
	 * @param notifyTimes
	 */
	public void setNotifyTimes(int notifyTimes);

	/**
	 * 发送次数
	 * @return
	 */
	public int getSendTimes();

	/**
	 * @param sendTimes
	 */
	public void setSendTimes(int sendTimes);

	/**
	 * 发送时间间隔
	 * @return
	 */
	public int getSendInterval();

	/**
	 * @param sendInterval
	 */
	public void setSendInterval(int sendInterval);


	/**
	 * 发送时间
	 * @return
	 */
	public Date getSendTime();

	/**
	 * @param sendTime
	 */
	public void setSendTime(Date sendTime);


	/**
	 * 银行联系人
	 * @return
	 */
	public List<IPerson> getBankPerson();
	/**
	 * @param list
	 */
	public void setBankPerson(List<IPerson> list);

	/**
	 * 维护商联系人
	 * @return
	 */
	public List<IPerson> getSerPerson();
	/**
	 * @param list
	 */
	public void setSerPerson(List<IPerson> list);

	/**
	 * 短信发送人
	 * @return
	 */
	public String getSendPerson();

	/**
	 * @param personName
	 */
	public void setSendPerson(String personName);

}
