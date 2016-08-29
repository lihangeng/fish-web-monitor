package com.yihuacomputer.fish.report.service.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.transaction.ISettlementCashInRpt;
import com.yihuacomputer.fish.api.report.transaction.ISettlementCashInRptService;

@Service
@Transactional(readOnly = true)
public class SettlementCashInRptService implements ISettlementCashInRptService {

    // private String SETT_CASH_HQL =
    // "select o.code, o.name, d.terminalId, d.devType.name, sett.date, cash.date, cash.amt, sett.leftAmt "
    // +
    // "from Settlement sett, CashInit cash, Organization o, Device d" +
    // "where o.id = d.organization " +
    // "and d.terminalId = sett.terminalId" +
    // "and sett.terminalId = cash.terminalId " +
    // "and sett.uuId=cash.uuid ";

    @Autowired
    private IGenericDao dao;

    @Override
    public List<ISettlementCashInRpt> listSettlementCashInRpt(IFilter filter) {
        StringBuffer hql = new StringBuffer();
        List<Object> valueObj = new ArrayList<Object>();

        StringBuffer upHql = new StringBuffer(); // 查询上一次加钞时间，上次加钞金额HQL
        List<Object> upValueObj = new ArrayList<Object>();

        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag"); // 机构
        IFilterEntry endDate = filter.getFilterEntry("endDate"); // 结束清机时间
        IFilterEntry startDate = filter.getFilterEntry("startDate"); // 开始清机时间
        IFilterEntry terminalId = filter.getFilterEntry("terminalId"); // 设备号
        IFilterEntry devVendorId = filter.getFilterEntry("devTypeId"); // 机型
        IFilterEntry startAmt = filter.getFilterEntry("startAmt"); // 开始加钞金额
        IFilterEntry endAmt = filter.getFilterEntry("endAmt"); // 结束加钞金额

        String SETT_CASH_HQL = "select o.code, o.name, d.terminalId, d.devType.name, sett.date, cash.date, cash.amt, sett.leftAmt "
                + " from Settlement sett, CashInit cash, Organization o, Device d "
                + " where o.id = d.organization "
                + " and d.terminalId = sett.terminalId "
                + " and sett.terminalId = cash.terminalId "
                + " and sett.uuId=cash.uuId ";

        hql.append(SETT_CASH_HQL);

        upHql.append(SETT_CASH_HQL);

        if (orgFlag != null) {
            hql.append(" and o.orgFlag like ?");
            valueObj.add(orgFlag.getValue() + "%");

//            upHql.append(" and o.orgFlag like ?");
//            upValueObj.add("%" + orgFlag.getValue());

        }
        if (terminalId != null) {
            hql.append(" and d.terminalId like ?");
            valueObj.add("%" + terminalId.getValue() + "%");

//            upHql.append(" and d.terminalId like ?");
//            upValueObj.add("%" + terminalId.getValue() + "%");
        }
        if (endDate != null) {
            hql.append(" and sett.date<=?");
            valueObj.add(endDate.getValue() + " 23:59:59");
        }
        if (startDate != null) {
            hql.append(" and sett.date>=?");
            valueObj.add(startDate.getValue() + " 00:00:00");
        }
        if (endAmt != null) {
            hql.append(" and cash.amt<=?");
            valueObj.add(endAmt.getValue());

//            upHql.append(" and cash.amt<=?");
//            upValueObj.add(endAmt.getValue());

        }
        if (startAmt != null) {
            hql.append(" and cash.amt>=?");
            valueObj.add(startAmt.getValue());

//            upHql.append(" and cash.amt>=?");
//            upValueObj.add(startAmt.getValue());
        }
        if (devVendorId != null) {
            hql.append(" and d.devType.id=?");
            valueObj.add(Long.valueOf(devVendorId.getValue().toString()));

//            upHql.append(" and d.devType.id=?");
//            upValueObj.add(Long.valueOf(devVendorId.getValue().toString()));
        }
        hql.append(" order by sett.date ");

        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());
        List<ISettlementCashInRpt> settlementCashInRptList = new ArrayList<ISettlementCashInRpt>();
        for (int i = 0; i < list.size(); i++) {
            Object[] o = (Object[]) list.get(i);
            ISettlementCashInRpt settlementRpt = new SettlementCashInRpt();

            settlementRpt.setOrgCode(objectToString(o[0]));
            settlementRpt.setOrgName(objectToString(o[1]));
            settlementRpt.setTerminalId(objectToString(o[2]));
            settlementRpt.setDevTypeName(objectToString(o[3]));
            settlementRpt.setSettleDate(objectToString(o[4]));
            settlementRpt.setCashDate(objectToString(o[5]));
            settlementRpt.setCashAmt(Double.valueOf(objectToString(o[6])));
            settlementRpt.setLeftAmt(Double.valueOf(objectToString(o[7])));

            if (i <= 0) {
                // 如果是第一条记录，则需要求出当前记录的上次加钞时间与金额
                if (!settlementRpt.getSettleDate().isEmpty()) {
                    upHql.append(" and sett.date<?");
                    upValueObj.add(settlementRpt.getSettleDate());

                    upHql.append(" order by sett.date desc ");

                    List<Object> upList = dao.findByHQL(upHql.toString(), upValueObj.toArray());
                    if (upList == null || upList.isEmpty()) {
                        // 没有前一次加钞金额与时间，则给默认值
                        settlementRpt.setUpCashDate("/");
                        settlementRpt.setUpCashAmt(0.00D);

                    } else {
                        Object[] upObj = (Object[]) upList.get(0);

                        settlementRpt.setUpCashDate(objectToString(upObj[5]));
                        settlementRpt.setUpCashAmt(Double.valueOf(objectToString(upObj[6])));

                    }
                }
            }

            if (i > 0) {
                o = (Object[]) list.get(i - 1);
                settlementRpt.setUpCashDate(objectToString(o[5]));
                settlementRpt.setUpCashAmt(Double.valueOf(objectToString(o[6])));
            }

            settlementCashInRptList.add(settlementRpt);
        }
        return settlementCashInRptList;
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
