package com.yihuacomputer.fish.report.service.trans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.trans.ICashInRpt;
import com.yihuacomputer.fish.api.report.trans.ICashInRptService;

@Service
@Transactional(readOnly = true)
public class CashInRptService implements ICashInRptService {

    @Autowired
    private IGenericDao dao;

    @Override
    public List<ICashInRpt> listCashInRpt(IFilter filter) {

        StringBuffer hql = new StringBuffer();
        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
        IFilterEntry endDate = filter.getFilterEntry("endDate");
        IFilterEntry startDate = filter.getFilterEntry("startDate");
        IFilterEntry terminalId = filter.getFilterEntry("terminalId");
        IFilterEntry devVendorId = filter.getFilterEntry("devVendorId");
        IFilterEntry startAmt = filter.getFilterEntry("startAmt");
        IFilterEntry endAmt = filter.getFilterEntry("endAmt");

        hql.append("select c.terminalId,c.amt,o.name,c.date from CashInit c,Device d,Organization o ");
        hql.append("where c.terminalId = d.terminalId and d.organization.id = o.id");

        if (orgFlag != null) {
            hql.append(" and o.orgFlag like ?");
            valueObj.add(orgFlag.getValue() + "%");
        }
        if (terminalId != null) {
            hql.append(" and c.terminalId like ?");
            valueObj.add("%" + terminalId.getValue() + "%");
        }
        if (endDate != null) {
            hql.append(" and c.date<=?");
            valueObj.add(endDate.getValue() + " 23:59:59");
        }
        if (startDate != null) {
            hql.append(" and c.date>=?");
            valueObj.add(startDate.getValue() + " 00:00:00");
        }
        if (endAmt != null) {
            hql.append(" and c.amt<=?");
            valueObj.add(endAmt.getValue());
        }
        if (startAmt != null) {
            hql.append(" and c.amt>=?");
            valueObj.add(startAmt.getValue());
        }
        if (devVendorId != null) {
            hql.append(" and d.devType.devVendor.id=?");
            valueObj.add(devVendorId.getValue());
        }
        hql.append(" order by c.date desc");

        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());
        List<ICashInRpt> cashInRptList = new ArrayList<ICashInRpt>();
        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            CashInRpt cashInRpt = new CashInRpt();
            cashInRpt.setTerminalId(objectToString(o[0]));
            cashInRpt.setCashInAmt(Long.valueOf(objectToString(o[1])));
            cashInRpt.setOrgName(objectToString(o[2]));
            cashInRpt.setDate(objectToString(o[3]));
            cashInRptList.add(cashInRpt);
        }
        return cashInRptList;
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
