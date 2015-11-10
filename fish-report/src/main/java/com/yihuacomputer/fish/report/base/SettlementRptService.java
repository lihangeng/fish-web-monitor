package com.yihuacomputer.fish.report.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.base.ISettlementRpt;
import com.yihuacomputer.fish.api.report.base.ISettlementRptService;

@Service
@Transactional(readOnly = true)
public class SettlementRptService implements ISettlementRptService {

    @Autowired
    private IGenericDao dao;

    @Override
    public List<ISettlementRpt> listSettlementRpt(IFilter filter) {

        StringBuffer hql = new StringBuffer();
        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
        IFilterEntry endDate = filter.getFilterEntry("endDate");
        IFilterEntry startDate = filter.getFilterEntry("startDate");
        IFilterEntry terminalId = filter.getFilterEntry("terminalId");
        IFilterEntry devVendorId = filter.getFilterEntry("devVendorId");
        IFilterEntry startAmt = filter.getFilterEntry("startAmt");
        IFilterEntry endAmt = filter.getFilterEntry("endAmt");

        hql.append("select s.terminalId,s.date,o.name,s.depositAmt,s.withdrawalAmt,s.leftAmt from Settlement s,Device d,Organization o ");
        hql.append("where s.terminalId = d.terminalId and d.organization.id = o.id");

        if (orgFlag != null) {
            hql.append(" and o.orgFlag like ?");
            valueObj.add(orgFlag.getValue() + "%");
        }
        if (terminalId != null) {
            hql.append(" and s.terminalId like ?");
            valueObj.add("%" + terminalId.getValue() + "%");
        }
        if (endDate != null) {
            hql.append(" and s.date<=?");
            valueObj.add(endDate.getValue() + " 23:59:59");
        }
        if (startDate != null) {
            hql.append(" and s.date>=?");
            valueObj.add(startDate.getValue() + " 00:00:00");
        }
        if (endAmt != null) {
            hql.append(" and s.leftAmt<=?");
            valueObj.add(endAmt.getValue());
        }
        if (startAmt != null) {
            hql.append(" and s.leftAmt>=?");
            valueObj.add(startAmt.getValue());
        }
        if (devVendorId != null) {
            hql.append(" and d.devType.devVendor.id=?");
            valueObj.add(devVendorId.getValue());
        }
        hql.append(" order by s.date desc");

        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());
        List<ISettlementRpt> settlementRptList = new ArrayList<ISettlementRpt>();
        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            SettlementRpt settlementRpt = new SettlementRpt();
            settlementRpt.setTerminalId(objectToString(o[0]));
            settlementRpt.setDate(objectToString(o[1]));
            settlementRpt.setOrgName(objectToString(o[2]));
            settlementRpt.setDepositAmt(Long.valueOf(objectToString(o[3])));
            settlementRpt.setWithdrawalAmt(Long.valueOf(objectToString(o[4])));
            settlementRpt.setLeftAmt(Long.valueOf(objectToString(o[5])));
            settlementRptList.add(settlementRpt);
        }
        return settlementRptList;
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
