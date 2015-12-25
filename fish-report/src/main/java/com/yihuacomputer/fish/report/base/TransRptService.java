package com.yihuacomputer.fish.report.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.IHostRet;
import com.yihuacomputer.fish.api.report.base.ITransCountRpt;
import com.yihuacomputer.fish.api.report.base.ITransResultCountRpt;
import com.yihuacomputer.fish.api.report.base.ITransRptService;

@Service
public class TransRptService implements ITransRptService {

    @Autowired
    private IGenericDao dao;

	@Autowired
	private MessageSource messageSource;



//    private final String transTipsName = "手续费";

    @Override
    public List<ITransCountRpt> listOrgTransCount(IFilter filter) {

        StringBuffer hql = new StringBuffer();
        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
        IFilterEntry endData = filter.getFilterEntry("endData");
        IFilterEntry startData = filter.getFilterEntry("startData");
        IFilterEntry transCode = filter.getFilterEntry("transCode");
        IFilterEntry terminalId = filter.getFilterEntry("terminalId");
        IFilterEntry orgLevel = filter.getFilterEntry("orgLevel");

        hql.append("select count(tr.transId),o.name,sum(tr.amt),tt.codeDesc,sum(tr.tipFee) from Transaction tr,Device d,Organization o,TransType tt ");
        hql.append("where tr.terminalId = d.terminalId and d.organization.id = o.id and tr.transCode = tt.transCode ");

        if (orgFlag != null) {
            hql.append(" and o.orgFlag like ?");
            valueObj.add(orgFlag.getValue() + "%");
        }
        if (endData != null) {
            hql.append(" and tr.dateTime<=?");
            valueObj.add(endData.getValue());
        }
        if (startData != null) {
            hql.append(" and tr.dateTime>=?");
            valueObj.add(startData.getValue());
        }
        if (transCode != null) {
            hql.append(" and tt.transCode=?");
            valueObj.add(transCode.getValue());
        }
        if (terminalId != null) {
            hql.append(" and r.terminalId like ?");
            valueObj.add("%" + terminalId.getValue() + "%");
        }

        if (orgLevel != null) {
            hql.append(" and o.organizationLevel <= ? ");
            valueObj.add(orgLevel.getValue());
        }
//        hql.append(" group by d.organization,tr.transCode order by tr.dateTime desc");

        hql.append(" group by o.name,tt.codeDesc ");

        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());

        List<ITransCountRpt> countList = new ArrayList<ITransCountRpt>();

        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            TransCountRpt transCountRpt = new TransCountRpt();
            TransCountRpt transAmtRpt = new TransCountRpt();
//            TransCountRpt transTpsRpt = new TransCountRpt();

            transCountRpt.setOrgName(objectToString(o[1]));
            transCountRpt.setTransType(objectToString(o[3]));
            transCountRpt.setCountName(messageSource.getMessage("report.trans.transCountName", null, FishCfg.locale));
            transCountRpt.setTransCount(Long.valueOf(objectToString(o[0])));

            transCountRpt.setOrgNameColumn(messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
            transCountRpt.setSubtotalColumn(messageSource.getMessage("report.devTypeCount.subTotal", null, FishCfg.locale));
            transCountRpt.setTotalColumn(messageSource.getMessage("report.devTypeCount.total", null, FishCfg.locale));
            transCountRpt.setTransTypeColumn(messageSource.getMessage("atmLog.transType", null, FishCfg.locale));


            transAmtRpt.setOrgName(objectToString(o[1]));
            transAmtRpt.setTransType(objectToString(o[3]));
            transAmtRpt.setCountName(messageSource.getMessage("report.trans.transAmtName", null, FishCfg.locale));
            transAmtRpt.setTransCount(Double.valueOf(objectToString(o[2])));

            transAmtRpt.setOrgNameColumn(messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
            transAmtRpt.setSubtotalColumn(messageSource.getMessage("report.devTypeCount.subTotal", null, FishCfg.locale));
            transAmtRpt.setTotalColumn(messageSource.getMessage("report.devTypeCount.total", null, FishCfg.locale));
            transAmtRpt.setTransTypeColumn(messageSource.getMessage("atmLog.transType", null, FishCfg.locale));

//            transTpsRpt.setOrgName(objectToString(o[1]));
//            transTpsRpt.setTransType(objectToString(o[3]));
//            transTpsRpt.setCountName(transTipsName);
//            transTpsRpt.setTransCount(Double.valueOf(objectToString(o[4])));

            countList.add(transCountRpt);
            countList.add(transAmtRpt);
//            countList.add(transTpsRpt);
        }

        return countList;
    }

    @Override
    public List<ITransCountRpt> listDeviceTransCount(IFilter filter) {

        StringBuffer hql = new StringBuffer();
        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
        IFilterEntry endData = filter.getFilterEntry("endData");
        IFilterEntry startData = filter.getFilterEntry("startData");
        IFilterEntry transCode = filter.getFilterEntry("transCode");
        IFilterEntry terminalId = filter.getFilterEntry("terminalId");
        IFilterEntry orgLevel = filter.getFilterEntry("orgLevel");

        hql.append("select o.name, d.terminalId, tt.codeDesc, count(tr.transId), sum(tr.amt), sum(tr.tipFee) from Transaction tr,Device d,Organization o,TransType tt ");
        hql.append("where tr.terminalId = d.terminalId and d.organization.id = o.id and tr.transCode = tt.transCode ");

        if (orgFlag != null) {
            hql.append(" and o.orgFlag like ?");
            valueObj.add(orgFlag.getValue() + "%");
        }
        if (endData != null) {
            hql.append(" and tr.dateTime<=?");
            valueObj.add(endData.getValue());
        }
        if (startData != null) {
            hql.append(" and tr.dateTime>=?");
            valueObj.add(startData.getValue());
        }
        if (transCode != null) {
            hql.append(" and tt.transCode=?");
            valueObj.add(transCode.getValue());
        }
        if (terminalId != null) {
            hql.append(" and r.terminalId like ?");
            valueObj.add(terminalId.getValue() + "%");
        }

        if (orgLevel != null) {
            hql.append(" and o.organizationLevel <= ? ");
            valueObj.add(orgLevel.getValue());
        }
//        hql.append(" group by d.organization,tr.transCode order by tr.dateTime desc");

        hql.append(" group by o.name,d.terminalId,tt.codeDesc ");

        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());

        List<ITransCountRpt> countList = new ArrayList<ITransCountRpt>();

        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            TransCountRpt transCountRpt = new TransCountRpt();
            TransCountRpt transAmtRpt = new TransCountRpt();
//            TransCountRpt transTpsRpt = new TransCountRpt();

            transCountRpt.setOrgName(objectToString(o[0]));
            transCountRpt.setTerminalId(objectToString(o[1]));
            transCountRpt.setTransType(objectToString(o[2]));
            transCountRpt.setCountName(messageSource.getMessage("report.trans.transCountName", null, FishCfg.locale));
            transCountRpt.setTransCount(Long.valueOf(objectToString(o[3])));


            transCountRpt.setOrgNameColumn(messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
            transCountRpt.setSubtotalColumn(messageSource.getMessage("report.devTypeCount.subTotal", null, FishCfg.locale));
            transCountRpt.setTotalColumn(messageSource.getMessage("report.devTypeCount.total", null, FishCfg.locale));
            transCountRpt.setTransTypeColumn(messageSource.getMessage("atmLog.transType", null, FishCfg.locale));
            transCountRpt.setDevNoColumn(messageSource.getMessage("device.terminalId", null, FishCfg.locale));

            transAmtRpt.setOrgName(objectToString(o[0]));
            transAmtRpt.setTerminalId(objectToString(o[1]));
            transAmtRpt.setTransType(objectToString(o[2]));
            transAmtRpt.setCountName(messageSource.getMessage("report.trans.transAmtName", null, FishCfg.locale));
            transAmtRpt.setTransCount(Double.valueOf(objectToString(o[4])));


            transAmtRpt.setOrgNameColumn(messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
            transAmtRpt.setSubtotalColumn(messageSource.getMessage("report.devTypeCount.subTotal", null, FishCfg.locale));
            transAmtRpt.setTotalColumn(messageSource.getMessage("report.devTypeCount.total", null, FishCfg.locale));
            transAmtRpt.setTransTypeColumn(messageSource.getMessage("atmLog.transType", null, FishCfg.locale));
            transAmtRpt.setDevNoColumn(messageSource.getMessage("device.terminalId", null, FishCfg.locale));

//            transTpsRpt.setOrgName(objectToString(o[1]));
//            transTpsRpt.setTransType(objectToString(o[3]));
//            transTpsRpt.setCountName(transTipsName);
//            transTpsRpt.setTransCount(Double.valueOf(objectToString(o[4])));

            countList.add(transCountRpt);
            countList.add(transAmtRpt);
//            countList.add(transTpsRpt);
        }

        return countList;
    }


    @Override
    public List<ITransResultCountRpt> listTransResultCount(IFilter filter) {
        StringBuffer hql = new StringBuffer();
        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
        IFilterEntry endData = filter.getFilterEntry("endData");
        IFilterEntry startData = filter.getFilterEntry("startData");
        IFilterEntry transCode = filter.getFilterEntry("transCode");

        hql.append("select count(tr.transId),o.name,sum(tr.amt),tr.hostRet from TransactionView tr,Device d,Organization o,TransType tt ");
        hql.append("where tr.terminalId = d.terminalId and d.organization.id = o.id and tr.transCode = tt.transCode");

        if (orgFlag != null) {
            hql.append(" and d.organization.orgFlag like ?");
            valueObj.add(orgFlag.getValue() + "%");
        }
        if (endData != null) {
            hql.append(" and tr.dateTime<?");
            valueObj.add(endData.getValue());
        }
        if (startData != null) {
            hql.append(" and tr.dateTime>=?");
            valueObj.add(startData.getValue());
        }
        if (transCode != null) {
            hql.append(" and tt.transCode=?");
            valueObj.add(transCode.getValue());
        }

//        hql.append(" group by d.organization,hr.code order by tr.dateTime desc");
        hql.append(" group by o.name, tr.hostRet ");

        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());

        List<ITransResultCountRpt> countList = new ArrayList<ITransResultCountRpt>();

        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            TransResultCountRpt transResultCountRpt = new TransResultCountRpt();
            TransResultCountRpt transResultAmtRpt = new TransResultCountRpt();

            transResultCountRpt.setOrgName(objectToString(o[1]));
            transResultCountRpt.setResult(objectToString(o[3]));
            transResultCountRpt.setCountName(messageSource.getMessage("report.trans.transCountName", null, FishCfg.locale));
            transResultCountRpt.setTransCount(Long.valueOf(objectToString(o[0])));


            transResultCountRpt.setOrgNameColumn(messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
            transResultCountRpt.setSubtotalColumn(messageSource.getMessage("report.devTypeCount.subTotal", null, FishCfg.locale));
            transResultCountRpt.setTransResultColumn(messageSource.getMessage("report.trans.transResult", null, FishCfg.locale));
            transResultCountRpt.setTotalColumn(messageSource.getMessage("report.devTypeCount.total", null, FishCfg.locale));


            transResultAmtRpt.setOrgName(objectToString(o[1]));
            transResultAmtRpt.setResult(objectToString(o[3]));
            transResultAmtRpt.setCountName(messageSource.getMessage("report.trans.transAmtName", null, FishCfg.locale));
            transResultAmtRpt.setTransCount(Double.valueOf(objectToString(o[2])));


            transResultAmtRpt.setOrgNameColumn(messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
            transResultAmtRpt.setTotalColumn(messageSource.getMessage("report.devTypeCount.total", null, FishCfg.locale));
            transResultAmtRpt.setSubtotalColumn(messageSource.getMessage("report.devTypeCount.subTotal", null, FishCfg.locale));
            transResultAmtRpt.setTransResultColumn(messageSource.getMessage("report.trans.transResult", null, FishCfg.locale));

            countList.add(transResultCountRpt);
            countList.add(transResultAmtRpt);
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

	@Override
	public List<IHostRet> listHostRetCode() {
		String hql = "from HostRet";
		List<IHostRet> hostRet = dao.findByHQL(hql);
		return hostRet;
	}
}
