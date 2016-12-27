package com.yihuacomputer.fish.fault.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.fault.INotifyMould;
import com.yihuacomputer.fish.api.fault.INotifyMouldService;
import com.yihuacomputer.fish.api.fault.INotifyMouldSet;
import com.yihuacomputer.fish.api.fault.NotifyType;
import com.yihuacomputer.fish.api.fault.NotifyWay;
import com.yihuacomputer.fish.fault.entity.NotifyMould;
import com.yihuacomputer.fish.fault.entity.NotifyMouldSet;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class NotifyMouldService implements INotifyMouldService{

	@Autowired
	private IGenericDao dao;
	
	@Override
	public INotifyMould make(){
		return new NotifyMould();
	}
	
	@Override
	public void save(INotifyMould notifyMould){
		this.dao.save(notifyMould);
	}
	
	@Override
	public void updateNotifyMould(INotifyMould notifyMould) {
		dao.update(interface2Entity(notifyMould));
	}
	
	private NotifyMould interface2Entity(INotifyMould notifyMould){
		if(notifyMould instanceof NotifyMould){
			return (NotifyMould) notifyMould;
		}
		return null;
	}
	
	@Override
	public String getNotifyMould(String classifyName,NotifyType notifyType, NotifyWay notifyWay) {
		String hql = "from NotifyMould n where n.faultClassify.id = ? and n.notifyType = ? and n.notifyWay = ?";
		List<INotifyMould> result = dao.findByHQL(hql,classifyName,notifyType,notifyWay);
		
		for(INotifyMould notifyMould:result){
			return notifyMould.getNotifySet();
		}
		return null;
	}

	@Override
	public List<INotifyMould> listNotifyMould() {
		return dao.loadAll(INotifyMould.class);
	}

	@Override
	public IPageResult<INotifyMould> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, NotifyMould.class);
	}

	@Override
	public INotifyMouldSet makeNotifySet() {
		return new NotifyMouldSet();
	}

	@Override
	public INotifyMould getNotifyMouldById(long id) {
		return dao.get(id, NotifyMould.class);
	}
}
