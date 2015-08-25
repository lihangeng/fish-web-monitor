package com.yihuacomputer.fish.report.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.base.ITransCountRpt;
import com.yihuacomputer.fish.api.report.base.ITransResultCountRpt;
import com.yihuacomputer.fish.api.report.base.ITransRptService;

@Service
public class TransRptService implements ITransRptService {

    @Autowired
    private IGenericDao dao;

    private final String transCountName = "交易笔数";

    private final String transAmtName = "交易金额";

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
            valueObj.add("%" + orgFlag.getValue());
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
            transCountRpt.setCountName(transCountName);
            transCountRpt.setTransCount(Long.valueOf(objectToString(o[0])));

            transAmtRpt.setOrgName(objectToString(o[1]));
            transAmtRpt.setTransType(objectToString(o[3]));
            transAmtRpt.setCountName(transAmtName);
            transAmtRpt.setTransCount(Double.valueOf(objectToString(o[2])));

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
            valueObj.add("%" + orgFlag.getValue());
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
            transCountRpt.setCountName(transCountName);
            transCountRpt.setTransCount(Long.valueOf(objectToString(o[3])));

            transAmtRpt.setOrgName(objectToString(o[0]));
            transAmtRpt.setTerminalId(objectToString(o[1]));
            transAmtRpt.setTransType(objectToString(o[2]));
            transAmtRpt.setCountName(transAmtName);
            transAmtRpt.setTransCount(Double.valueOf(objectToString(o[4])));

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

        hql.append("select count(tr.transId),o.name,sum(tr.amt),hr.name from Transaction tr,Device d,Organization o,HostRet hr,TransType tt ");
        hql.append("where tr.terminalId = d.terminalId and d.organization.id = o.id and tr.hostRet = hr.code and tr.transCode = tt.transCode");

        if (orgFlag != null) {
            hql.append(" and d.organization.orgFlag like ?");
            valueObj.add("%" + orgFlag.getValue());
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
        
//        hql.append(" group by d.organization,hr.code order by tr.dateTime desc");
        hql.append(" group by o.name, hr.name ");
        
        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());

        List<ITransResultCountRpt> countList = new ArrayList<ITransResultCountRpt>();

        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            TransResultCountRpt transResultCountRpt = new TransResultCountRpt();
            TransResultCountRpt transResultAmtRpt = new TransResultCountRpt();

            transResultCountRpt.setOrgName(objectToString(o[1]));
            transResultCountRpt.setResult(objectToString(o[3]));
            transResultCountRpt.setCountName(transCountName);
            transResultCountRpt.setTransCount(Long.valueOf(objectToString(o[0])));

            transResultAmtRpt.setOrgName(objectToString(o[1]));
            transResultAmtRpt.setResult(objectToString(o[3]));
            transResultAmtRpt.setCountName(transAmtName);
            transResultAmtRpt.setTransCount(Double.valueOf(objectToString(o[2])));

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
}
