package com.yihuacomputer.fish.report.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.CardStatus;
import com.yihuacomputer.fish.api.report.base.IRetainCardCountRpt;
import com.yihuacomputer.fish.api.report.base.IRetainCardRpt;
import com.yihuacomputer.fish.api.report.base.IRetainCardRptService;

@Service
@Transactional(readOnly = true)
public class RetainCardRptService implements IRetainCardRptService {

	private String countName ;

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public List<IRetainCardRpt> listRetainCardDetail(IFilter filter) {

		StringBuffer hql = new StringBuffer();
		List<Object> valueObj = new ArrayList<Object>();
		IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
		IFilterEntry endData = filter.getFilterEntry("endData");
		IFilterEntry startData = filter.getFilterEntry("startData");
		IFilterEntry terminalId = filter.getFilterEntry("terminalId");
		IFilterEntry accountNo = filter.getFilterEntry("accountNo");
		IFilterEntry devVendorId = filter.getFilterEntry("devVendorId");
		IFilterEntry devTypeId = filter.getFilterEntry("devTypeId");
		IFilterEntry awayFlag = filter.getFilterEntry("awayFlag");
		IFilterEntry status = filter.getFilterEntry("status");

		hql.append("select r.terminalId,r.accountNo,r.cardRetainTime,r.reason,d.address,o.name, o.code, r.status from Retaincard r,Device d,Organization o ");
		hql.append("where r.terminalId = d.terminalId and d.organization.id = o.id");

		if (orgFlag != null) {
			hql.append(" and d.organization.orgFlag like ?");
			valueObj.add(orgFlag.getValue() + "%");
		}
		if (terminalId != null) {
			hql.append(" and r.terminalId like ?");
			valueObj.add("%" + terminalId.getValue() + "%");
		}
		if (endData != null) {
			hql.append(" and r.cardRetainTime<=?");
			valueObj.add(endData.getValue());
		}
		if (startData != null) {
			hql.append(" and r.cardRetainTime>=?");
			valueObj.add(startData.getValue());
		}
		if (accountNo != null) {
			hql.append(" and r.accountNo like ?");
			valueObj.add(accountNo.getValue() + "%");
		}
		if (devVendorId != null) {
			hql.append(" and d.devType.devVendor.id=?");
			valueObj.add(devVendorId.getValue());
		}
		if (devTypeId != null) {
			hql.append(" and d.devType.id=?");
			valueObj.add(devTypeId.getValue());
		}
		if (awayFlag != null) {
			hql.append(" and d.awayFlag=?");
			valueObj.add(awayFlag.getValue());
		}

		if (status != null) {
			hql.append(" and r.status=?");
			valueObj.add(status.getValue());
		}

		hql.append(" order by r.cardRetainTime desc");

		List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());

		List<IRetainCardRpt> retainCardList = new ArrayList<IRetainCardRpt>();
		for (Object obj : list) {
			Object[] o = (Object[]) obj;
			RetainCardRpt retainCard = new RetainCardRpt();
			retainCard.setTerminalId(objectToString(o[0]));
			retainCard.setAccount(objectToString(o[1]));
			retainCard.setRetainDate("" + DateUtils.getTimestamp(DateUtils.getTimestamp(objectToString(o[2]))));
			retainCard.setReason(objectToString(o[3]));
			retainCard.setAddress(objectToString(o[4]));
			retainCard.setOrgName(objectToString(o[5]));
			retainCard.setOrgCode(objectToString(o[6]));
			retainCard.setStatus(getEnumI18n(((CardStatus) o[7]).getText()));
			retainCardList.add(retainCard);
		}
		return retainCardList;
	}
	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
	@Override
	public List<IRetainCardCountRpt> listRetainCardCount(IFilter filter) {
		StringBuffer hql = new StringBuffer();
		List<Object> valueObj = new ArrayList<Object>();
		IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
		IFilterEntry endData = filter.getFilterEntry("endData");
		IFilterEntry startData = filter.getFilterEntry("startData");

		hql.append("select count(r.id),o.name,d.devType.name from Retaincard r,Device d,Organization o ");
		hql.append("where r.terminalId = d.terminalId and d.organization.id = o.id");

		if (orgFlag != null) {
			hql.append(" and o.orgFlag like ?");
			valueObj.add(orgFlag.getValue() + "%");
		}
		if (endData != null) {
			hql.append(" and r.cardRetainTime<=?");
			valueObj.add(endData.getValue());
		}
		if (startData != null) {
			hql.append(" and r.cardRetainTime>=?");
			valueObj.add(startData.getValue());
		}
		// hql.append(" group by d.organization,d.devType order by r.cardRetainTime desc");

		hql.append(" group by o.name, d.devType.name ");

		List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());

		List<IRetainCardCountRpt> countList = new ArrayList<IRetainCardCountRpt>();

		for (Object obj : list) {
			Object[] o = (Object[]) obj;
			RetainCardCountRpt retainCardCount = new RetainCardCountRpt();
			countName = messageSource.getMessage("report.retainCard.title", null, FishCfg.locale);
			retainCardCount.setCountName(countName);
			retainCardCount.setRetainCount(Long.valueOf(objectToString(o[0])));
			retainCardCount.setOrgName(objectToString(o[1]));
			retainCardCount.setDeviceType(objectToString(o[2]));
			
			retainCardCount.setOrgNameColumn(messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
			retainCardCount.setDevTypeNameColumn(messageSource.getMessage("report.devTypeCount.type", null, FishCfg.locale));
			retainCardCount.setSubtotalColumn(messageSource.getMessage("report.devTypeCount.subTotal", null, FishCfg.locale));
			retainCardCount.setTotalColumn(messageSource.getMessage("report.devTypeCount.total", null, FishCfg.locale));
			
			countList.add(retainCardCount);
		}
		return countList;
	}

	/**
	 * 对象转换为字符串，null转为""
	 *
	 * @param obj
	 *            目标对象
	 * @return 返回字符串
	 */
	private String objectToString(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}
}
