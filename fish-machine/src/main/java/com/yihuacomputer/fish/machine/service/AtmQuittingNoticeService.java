package com.yihuacomputer.fish.machine.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNotice;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNoticeService;
import com.yihuacomputer.fish.machine.entity.QuittingNotice;
/**
 * 报停服务
 * @author
 *
 */
@Service
@Transactional
public class AtmQuittingNoticeService implements IQuittingNoticeService
{
    @Autowired
    private IGenericDao dao;
    
    @Override
    public QuittingNotice make() {
        return new QuittingNotice();
    }

    @Autowired
	private IOrganizationService orgService;

    @Override
    @Transactional(readOnly = true)
    public IQuittingNotice get(long id)
    {
    	return dao.get(id, QuittingNotice.class);
    }

    @Override
    @Transactional(readOnly = true)
    public IQuittingNotice get(String deviceCode)
    {
    	IQuittingNotice quittingNotice = (IQuittingNotice) dao.getCriteria(QuittingNotice.class)
        .add(Restrictions.eq("deviceCode", deviceCode)).uniqueResult();
    	return quittingNotice;
    }

    @Override
    public IQuittingNotice add(IQuittingNotice quittingNotice)
    {
    	return dao.save(quittingNotice);
    }

    @Override
    public void remove(long id)
    {
    	dao.delete(id, QuittingNotice.class);
    }

    @Override
    public void remove(String deviceCode)
    {
    	IQuittingNotice entity = get(deviceCode);
    	dao.delete(entity.getId(), QuittingNotice.class);
    }

    @Override
    public void update(IQuittingNotice quittingNotice)
    {
    	dao.update(interface2Entity(quittingNotice,true));
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<IQuittingNotice> list()
    {
    	return dao.loadAll(IQuittingNotice.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<IQuittingNotice> list(IFilter filter)
    {
    	Filter db = new Filter();
    	Filter mem = new Filter();
    	for(IFilterEntry entry : filter.entrySet()){
    		if(entry.getKey().indexOf(".")>0){
    			mem.addFilterEntry(entry);
    		}else{
    			db.addFilterEntry(entry);
    		}
    	}
    	List<IQuittingNotice> atms = dao.findByFilter(db, IQuittingNotice.class);
    	List<IQuittingNotice> result = mem.filter(atms);
    	return result;
    }

    private QuittingNotice interface2Entity(IQuittingNotice quittingNotice, boolean load) {
		if (quittingNotice instanceof QuittingNotice) {
			return (QuittingNotice) quittingNotice;
		}
		return null;
	}


    @Transactional(readOnly = true)
    public Iterable<IQuittingNotice> list(IFilter filter,String orgId,boolean flag)
    {
    	StringBuffer hql = new StringBuffer();
    	IOrganization org = orgService.get(orgId);
    	hql.append("select t from QuittingNotice t ,Device t1 ");
    	//flag ==true 时，银行人员登录查询,否则为维护商登录
    	if(flag){
    		hql.append("where t.deviceCode = t1.terminalId and t1.organization.orgFlag like ? ");
    	}else{
    		hql.append("where t.deviceCode = t1.terminalId and t1.devService.orgFlag like ? ");
    	}
    	List<IQuittingNotice> quittingNotices = dao.findByHQL(hql.toString(),org.getOrgFlag() + "%");
    	List<IQuittingNotice> result = new ArrayList<IQuittingNotice>();
    	for(IQuittingNotice quittingNotice : quittingNotices){
    		if(filter.isMatch(quittingNotice)){
    			result.add(quittingNotice);
    		}
    	}
    	return result;
    }

    @Override
    public IPageResult<IQuittingNotice> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, QuittingNotice.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public IPageResult<IQuittingNotice> page(int offset, int limit,
			IFilter filter, String orgId, boolean flag) {
        StringBuffer hql = new StringBuffer();
        hql.append("select quittingNotice from QuittingNotice quittingNotice ,Device device ");
        if(flag){
            hql.append("where quittingNotice.deviceCode = device.terminalId and device.organization.orgFlag like ? ");
        }else{
            hql.append("where quittingNotice.deviceCode = device.terminalId and device.devService.orgFlag like ? ");
        }
        IOrganization org = orgService.get(orgId);
        return (IPageResult<IQuittingNotice>)dao.page(offset, limit, filter, hql.toString(),org.getOrgFlag() + "%");
	}
	
	@Override
	@Transactional(readOnly = true)
	public IQuittingNotice get(String deviceCode, Date openTime){
        StringBuffer hql = new StringBuffer();
        hql.append("from QuittingNotice quittingNotice where quittingNotice.deviceCode = ? ");
        hql.append(" and (quittingNotice.openTime > ?");
        hql.append(" or quittingNotice.openTime = null )");
    	List<Object> valueObj = new ArrayList<Object>();
    	valueObj.add(deviceCode);
    	valueObj.add(openTime);
    	List<IQuittingNotice> quittingNoticeList = dao.findByHQL(hql.toString(), valueObj.toArray());
    	IQuittingNotice quittingNotice  = null;
    	if(!quittingNoticeList.isEmpty())
    	{
    		quittingNotice = quittingNoticeList.get(0);
    	}
		return quittingNotice;
	}

}
