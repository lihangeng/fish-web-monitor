package com.yihuacomputer.fish.fault.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.EmailHandle;
import com.yihuacomputer.common.util.MailUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.fault.FaultStatus;
import com.yihuacomputer.fish.api.fault.ICaseFault;
import com.yihuacomputer.fish.api.fault.ICaseNotify;
import com.yihuacomputer.fish.api.fault.ICaseNotifyService;
import com.yihuacomputer.fish.api.fault.IFaultClassify;
import com.yihuacomputer.fish.api.fault.INotifyContentService;
import com.yihuacomputer.fish.api.fault.INotifyMailSenderManager;
import com.yihuacomputer.fish.api.fault.INotifyMouldService;
import com.yihuacomputer.fish.api.fault.INotifyMouldSet;
import com.yihuacomputer.fish.api.fault.INotifySmsSenderManager;
import com.yihuacomputer.fish.api.fault.NotifyType;
import com.yihuacomputer.fish.api.fault.NotifyWay;
import com.yihuacomputer.fish.api.fault.ResponsorType;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.PersonType;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.fault.entity.CaseNotify;
import com.yihuacomputer.fish.fault.entity.NotifyContent;

@Service
@Transactional
public class CaseNotifyService implements ICaseNotifyService {

	@Autowired
	private IGenericDao dao;

	@Autowired
	private INotifyMouldService notifyMouldService;

	@Autowired
	private IDevicePersonRelation devicePersonRelation;

	@Autowired(required = false)
	private INotifySmsSenderManager notifySmsSenderManager;
	
	@Autowired(required = false)
	private INotifyMailSenderManager notifyMailSenderManager;

	@Autowired
	private IOrganizationService orgService;

	@Autowired(required = false)
	private INotifyContentService notifyContentService;
	
	
	@Override
	public ICaseNotify make() {
		return new CaseNotify();
	}

	@Override
	public void saveCaseNotify(ICaseNotify caseNotify) {
		dao.save(caseNotify);
	}

	@Override
	public void updateCaseNotify(ICaseNotify caseNotify) {
		dao.update(caseNotify);
	}

	@Override
	public void createCaseNotify(IFaultClassify faultClassify, ICaseFault caseFault) {
		List<IPerson> personList = getPersonList(caseFault.getTerminalId(), faultClassify.getResponsorType());
		this.createCaseNotify(faultClassify, caseFault, personList);
	}

	/**
	 * 处理故障通知内容
	 *
	 * @param faultClassify
	 * @param notifyMouldSet
	 * @return
	 */
	public NotifyContent getNotifyContent(IFaultClassify faultClassify, INotifyMouldSet notifyMouldSet) {
		NotifyContent notifyContent = new NotifyContent();
		switch (faultClassify.getNotifyWay()) {
			case SMS: {
				notifyContent.setSmsNotify(notifyMouldService.getNotifyMould(faultClassify.getId(), NotifyType.CREATE, NotifyWay.SMS));
				break;
			}
			case MAIL: {
				notifyContent.setMailNotify(notifyMouldService.getNotifyMould(faultClassify.getId(), NotifyType.CREATE, NotifyWay.MAIL));
				break;
			}
			case BOTH: {
				notifyContent.setSmsNotify(notifyMouldService.getNotifyMould(faultClassify.getId(), NotifyType.CREATE, NotifyWay.SMS));
				notifyContent.setMailNotify(notifyMouldService.getNotifyMould(faultClassify.getId(), NotifyType.CREATE, NotifyWay.MAIL));
				break;
			}
			default:{
				break;
			}
		}

		if (notifyContentService != null) {
			if (notifyContent.getSmsNotify() != null) {
				notifyContent.setSmsNotify(notifyContentService.handleNotifyContent(notifyContent.getSmsNotify(), notifyMouldSet));
			}

			if (notifyContent.getMailNotify() != null) {
				notifyContent.setMailNotify(notifyContentService.handleNotifyContent(notifyContent.getMailNotify(), notifyMouldSet));
			}
		}
		return notifyContent;
	}

	/**
	 * 获取责任人列表
	 *
	 * @param terminalId
	 * @param responsorType
	 * @return
	 */
	private List<IPerson> getPersonList(String terminalId, ResponsorType responsorType) {
		switch (responsorType) {
		case ADMIN: {
			return devicePersonRelation.listAdminMaintainPersonByDevice(terminalId, PersonType.MANAGE);
		}
		case MAINTAIN: {
			return devicePersonRelation.listAdminMaintainPersonByDevice(terminalId, PersonType.FIXMAN);
		}
		case BOTH: {
			return devicePersonRelation.listPersonByDevice(terminalId);
		}
		default: {
			return null;
		}
		}
	}

	@Override
	public List<ICaseNotify> listNotifyByFaultId(long faultId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select cn  from CaseNotify cn where cn.faultId = ? ");
		return dao.findByHQL(hql.toString(), faultId);
	}

	@Override
	public List<ICaseNotify> listNotifyByDevice(String terminalId, Date createDate) {
		return null;
	}

	@Override
	public List<ICaseNotify> listNotify(IFilter filter) {
		return dao.findByFilter(filter, ICaseNotify.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public IPageResult<ICaseNotify> page(int offset, int limit, IFilter filter, long orgId) {
		StringBuffer hql = new StringBuffer();
		IOrganization org = orgService.get(String.valueOf(orgId));
		List<Object> valueObj = new ArrayList<Object>();
		IFilterEntry faultId = filter.getFilterEntry("faultId");
		IFilterEntry terminalId = filter.getFilterEntry("terminalId");
		IFilterEntry createTime = filter.getFilterEntry("createTime");
		IFilterEntry notifyWay = filter.getFilterEntry("notifyWay");
		IFilterEntry mobile = filter.getFilterEntry("mobile");
		IFilterEntry mail = filter.getFilterEntry("mail");
		IFilterEntry notifyTimes = filter.getFilterEntry("notifyTimes");
		IFilterEntry sendTimes = filter.getFilterEntry("sendTimes");
		IFilterEntry sendTime = filter.getFilterEntry("sendTime");
		hql.append("select caseNotify from CaseNotify caseNotify ,Device device ");
		hql.append("where (caseNotify.terminalId = device.terminalId) and device.organization.orgFlag like ? ");
		valueObj.add(org.getOrgFlag() + "%");
		if (faultId != null) {
			hql.append(" and caseNotify.faultId = ?");
			valueObj.add(Long.parseLong(faultId.getValue().toString()));
		}
		if (terminalId != null) {
			hql.append(" and caseNotify.terminalId like ?");
			valueObj.add("%" + terminalId.getValue() + "%");
		}
		if (mobile != null) {
			hql.append(" and caseNotify.mobile like ?");
			valueObj.add("%" + mobile.getValue() + "%");
		}
		if (mail != null) {
			hql.append(" and caseNotify.mail like ?");
			valueObj.add("%" + mail.getValue() + "%");
		}
		if (notifyTimes != null) {
			hql.append(" and caseNotify.notifyTimes = ?");
			valueObj.add(Integer.parseInt(notifyTimes.getValue().toString()));
		}
		if (notifyWay != null) {
			hql.append(" and caseNotify.notifyWay = ?");
			valueObj.add(NotifyWay.valueOf(notifyWay.getValue().toString()));
		}
		if (sendTimes != null) {
			hql.append(" and caseNotify.sendTimes = ?");
			valueObj.add(Integer.parseInt(sendTimes.getValue().toString()));
		}
		if (createTime != null) {
			hql.append(" and caseNotify.createTime >= ?");
			valueObj.add(DateUtils.getTimestamp(createTime.getValue().toString() + " 00:00:00"));
			hql.append(" and caseNotify.createTime <= ?");
			valueObj.add(DateUtils.getTimestamp(createTime.getValue().toString() + " 23:59:59"));
		}
		if (sendTime != null) {
			hql.append(" and caseNotify.sendTime >= ?");
			valueObj.add(DateUtils.getTimestamp(sendTime.getValue().toString() + " 00:00:00"));
			hql.append(" and caseNotify.sendTime <= ?");
			valueObj.add(DateUtils.getTimestamp(sendTime.getValue().toString() + " 23:59:59"));
		}
		hql.append(" order by caseNotify.createTime desc");
		IPageResult<ICaseNotify> result = (IPageResult<ICaseNotify>) this.dao.page(offset, limit, hql.toString(), valueObj.toArray());
		return result;
	}

	@Override
	public void createCaseNotify(IFaultClassify faultClassify, ICaseFault caseFault, List<IPerson> personList) {
		if (caseFault == null || faultClassify == null) {
			return;
		}
		//如果故障状态为关闭，则不创建短信
		if(caseFault.getFaultStatus().equals(FaultStatus.CLOSED)){
			return;
		}

		if (personList == null || personList.isEmpty()) {
			return;
		}
		INotifyMouldSet notifyMouldSet = notifyMouldService.makeNotifySet();
		notifyMouldSet.setTerminalId(caseFault.getTerminalId());
		notifyMouldSet.setAppStatus(caseFault.getAppStatus());
		notifyMouldSet.setFaultClassify(faultClassify.getClassifyName());
		notifyMouldSet.setFaultMod(caseFault.getDevMod());
		notifyMouldSet.setHwCode(caseFault.getVendorHwCode());

		NotifyContent notifyContent = getNotifyContent(faultClassify, notifyMouldSet);
		 try {
			 List<String> perList=new ArrayList<String>();
			 
		for (IPerson person : personList) {
			ICaseNotify caseNotify = make();
			if (caseFault.getId() != 0) {
				caseNotify.setFaultId(caseFault.getId());
			}
			caseNotify.setTerminalId(caseFault.getTerminalId());
			caseNotify.setMobile(person.getMobile());
			caseNotify.setMail(person.getEmail());
			caseNotify.setNotifyTimes(faultClassify.getNotifyTimes());
			caseNotify.setCreateTime(new Date());
			caseNotify.setSendTime(new Date());

			// 只做短信
			if (faultClassify.getNotifyWay().equals(NotifyWay.SMS) && notifyContent.getSmsNotify() != null) {
				if (person.getMobile() == null || person.getMobile().isEmpty()) {// 手机号未设置
					continue;
				}
				caseNotify.setContent(notifyContent.getSmsNotify());
				caseNotify.setNotifyWay(NotifyWay.SMS);
				saveCaseNotify(caseNotify);
				if (notifySmsSenderManager != null) {
					notifySmsSenderManager.notifySmsSend(caseNotify);
				}
			}
			
			// 只做邮件
			if (faultClassify.getNotifyWay().equals(NotifyWay.MAIL) && notifyContent.getMailNotify() != null) {
				if (person.getEmail()== null || person.getEmail().isEmpty()) {// 邮件地址未设置
					continue;
				}
				caseNotify.setContent(notifyContent.getMailNotify());
				caseNotify.setNotifyWay(NotifyWay.MAIL);
				saveCaseNotify(caseNotify);
				perList.add(caseNotify.getMail());
//				if (notifyMailSenderManager != null) {
//					notifyMailSenderManager.notifyMailSend(caseNotify);
//				}
			}
		
			
		}
		//收件人集合不为空，则发送硬件故障通知邮件
		if(!perList.isEmpty()){
			MailUtils.sendEmail(caseFault.getFaultClassify().getClassifyName(),notifyContent.getMailNotify(),perList,null);
		}
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<ICaseNotify> getNotifyWay(IFilter filter) {
		StringBuffer hql = new StringBuffer();
		IFilterEntry createTime = filter.getFilterEntry("createTime");
		IFilterEntry notifyWay = filter.getFilterEntry("notifyWay");
		IFilterEntry notifyTimes = filter.getFilterEntry("notifyTimes");
		IFilterEntry sendTimes = filter.getFilterEntry("sendTimes");
		List<Object> valueObj = new ArrayList<Object>();
		hql.append("select caseNotify  from CaseNotify caseNotify where caseNotify.sendPerson  is not null ");
		if (createTime != null) {
			hql.append(" and caseNotify.createTime >= ?");
			valueObj.add(DateUtils.getTimestamp(createTime.getValue().toString() + " 00:00:00"));
			hql.append(" and caseNotify.createTime <= ?");
			valueObj.add(DateUtils.getTimestamp(createTime.getValue().toString() + " 23:59:59"));
		}
		if (notifyTimes != null) {
			hql.append(" and caseNotify.notifyTimes = ?");
			valueObj.add(Integer.parseInt(notifyTimes.getValue().toString()));
		}
		if (notifyWay != null) {
			hql.append(" and caseNotify.notifyWay = ?");
			valueObj.add(NotifyWay.valueOf(notifyWay.getValue().toString()));
		}
		if (sendTimes != null) {
			hql.append(" and caseNotify.sendTimes = ?");
			valueObj.add(Integer.parseInt(sendTimes.getValue().toString()));
		}
		hql.append(" order by caseNotify.createTime desc ");
		return dao.findByHQL(hql.toString(), valueObj.toArray());
	}

	@Override
	public List<ICaseNotify> page(IFilter filter, long orgId) {
		StringBuffer hql = new StringBuffer();
		IOrganization org = orgService.get(String.valueOf(orgId));
		List<Object> valueObj = new ArrayList<Object>();
		IFilterEntry faultId = filter.getFilterEntry("faultId");
		IFilterEntry terminalId = filter.getFilterEntry("terminalId");
		IFilterEntry createTime = filter.getFilterEntry("createTime");
		IFilterEntry notifyWay = filter.getFilterEntry("notifyWay");
		IFilterEntry mobile = filter.getFilterEntry("mobile");
		IFilterEntry mail = filter.getFilterEntry("mail");
		IFilterEntry notifyTimes = filter.getFilterEntry("notifyTimes");
		IFilterEntry sendTimes = filter.getFilterEntry("sendTimes");
		IFilterEntry sendTime = filter.getFilterEntry("sendTime");
		hql.append("select caseNotify from CaseNotify caseNotify ,Device device ");
		hql.append("where (caseNotify.terminalId = device.terminalId) and device.organization.orgFlag like ? ");
		valueObj.add(org.getOrgFlag() + "%");
		if (faultId != null) {
			hql.append(" and caseNotify.faultId = ?");
			valueObj.add(Long.parseLong(faultId.getValue().toString()));
		}
		if (terminalId != null) {
			hql.append(" and caseNotify.terminalId like ?");
			valueObj.add("%" + terminalId.getValue() + "%");
		}
		if (mobile != null) {
			hql.append(" and caseNotify.mobile like ?");
			valueObj.add("%" + mobile.getValue() + "%");
		}
		if (mail != null) {
			hql.append(" and caseNotify.mail like ?");
			valueObj.add("%" + mail.getValue() + "%");
		}
		if (notifyTimes != null) {
			hql.append(" and caseNotify.notifyTimes = ?");
			valueObj.add(Integer.parseInt(notifyTimes.getValue().toString()));
		}
		if (notifyWay != null) {
			hql.append(" and caseNotify.notifyWay = ?");
			valueObj.add(NotifyWay.valueOf(notifyWay.getValue().toString()));
		}
		if (sendTimes != null) {
			hql.append(" and caseNotify.sendTimes = ?");
			valueObj.add(Integer.parseInt(sendTimes.getValue().toString()));
		}
		if (createTime != null) {
			hql.append(" and caseNotify.createTime >= ?");
			valueObj.add(DateUtils.getTimestamp(createTime.getValue().toString() + " 00:00:00"));
			hql.append(" and caseNotify.createTime <= ?");
			valueObj.add(DateUtils.getTimestamp(createTime.getValue().toString() + " 23:59:59"));
		}
		if (sendTime != null) {
			hql.append(" and caseNotify.sendTime >= ?");
			valueObj.add(DateUtils.getTimestamp(sendTime.getValue().toString() + " 00:00:00"));
			hql.append(" and caseNotify.sendTime <= ?");
			valueObj.add(DateUtils.getTimestamp(sendTime.getValue().toString() + " 23:59:59"));
		}
		hql.append(" order by caseNotify.createTime desc");
		List<ICaseNotify> result = this.dao.findByHQL(hql.toString(), valueObj.toArray());
		return result;
	}

}
