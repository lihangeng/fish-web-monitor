package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.CashSettleInit;
import com.yihuacomputer.fish.api.monitor.business.ISettlement;
import com.yihuacomputer.fish.api.monitor.business.ISettlementService;
import com.yihuacomputer.fish.monitor.entity.business.CashInit;
import com.yihuacomputer.fish.monitor.entity.business.Settlement;

@Service
@Transactional
public class SettlementService implements ISettlementService {

    @Autowired
    private IGenericDao dao;

    @Override
    public void save(ISettlement settlement) {
        this.dao.save(settlement);
    }

    @Override
    public ISettlement load(String terminalId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISettlement make() {
    	return new Settlement();
    }

    @Override
    public ISettlement get(long id) {
        return dao.get(id, Settlement.class);
    }

    @Override
    public ISettlement get(String code) {
        ISettlement settlement = (ISettlement) dao.getCriteria(Settlement.class)
                .add(Restrictions.eq("terminalId", code)).uniqueResult();
        return settlement;
    }

    @Override
    public List<ISettlement> list() {
        return list(new Filter());
    }

    @Override
    public List<ISettlement> list(IFilter filter) {
        List<Settlement> settlementList = dao.findByFilter(filter, Settlement.class);
        return EntityUtils.<ISettlement> convert(settlementList);
    }

    @Override
    public IPageResult<ISettlement> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, Settlement.class);
    }
    
    public List<CashSettleInit> getCashSettleInitListByDev(String terminalId){
    	StringBuilder sb = new StringBuilder();
    	sb.append("select sum(init.amt),sum(settle.leftAmt),sum(settle.depositAmt),sum(settle.deposit),");
    	sb.append("sum(settle.withdrawal),sum(settle.withdrawalAmt),settle.dates,init.dates from ")
    	.append(CashInit.class.getSimpleName()).append(" init,");
    	sb.append(Settlement.class.getSimpleName()).append(" settle ");
    	sb.append("where settle.uuId = init.uuId and  init.dates>=? and init.dates<? and ");
    	sb.append("settle.terminalId=init.terminalId and init.terminalId=? ");
    	sb.append(" group by settle.dates");
    	int lastWeekDate = Integer.parseInt(DateUtils.getDateShort(DateUtils.getLastWeek()));
    	int today = Integer.parseInt(DateUtils.getDateShort(new Date()));
    	List<Object> list = dao.findByHQL(sb.toString(), lastWeekDate,today,terminalId);
    	List<CashSettleInit> settleInitList = new ArrayList<CashSettleInit>();
    	for(Object obj:list){
    		Object objs[] = (Object[])obj;
    		CashSettleInit settleInit = new CashSettleInit();
    		settleInit.setInitAmt(objs[0]==null?0l:Long.parseLong(String.valueOf(objs[0])));
    		settleInit.setLeftAmt(objs[1]==null?0l:Long.parseLong(String.valueOf(objs[1])));
    		settleInit.setDepositAmt(objs[2]==null?0l:Long.parseLong(String.valueOf(objs[2])));
    		settleInit.setDeposit(objs[3]==null?0l:Long.parseLong(String.valueOf(objs[3])));
    		settleInit.setWithdrawal(objs[4]==null?0l:Long.parseLong(String.valueOf(objs[4])));
    		settleInit.setWithdrawalAmt(objs[5]==null?0l:Long.parseLong(String.valueOf(objs[5])));
    		settleInit.setDate(objs[7]==null?String.valueOf(objs[6]):String.valueOf(objs[7]));
    		settleInitList.add(settleInit);
    	}
    	return settleInitList;
    }
    
}
