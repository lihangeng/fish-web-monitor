package com.yihuacomputer.fish.monitor.service.mem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.monitor.business.CardStatus;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.monitor.entity.business.Retaincard;
import com.yihuacomputer.fish.monitor.service.base.DomainRetaincardService;

@Service
public class MemoryRetaincardService extends DomainRetaincardService {
	private BaseMemoryService memService = new BaseMemoryService();

	private List<Retaincard> entities = new ArrayList<Retaincard>();

	@Override
	public void remove(long id) {
		entities.remove(get(id));
		
	}

	@Override
	public IRetaincard get(long id) {
		{
			for (Retaincard item : entities) {
				if (id == item.getId()) {
					return item;
				}
			}
			throw new NotFoundException(String.format(
					"not found entity [Retaincard.id = %s]", id));
		}
	}

	@Override
	public void update(IRetaincard retaincard) {
		IRetaincard result = get(retaincard.getId());
		result.update(retaincard);
		
	}

	@Override
	public Iterable<IRetaincard> list() {
		return EntityUtils.<IRetaincard>convert(entities);
	}

	@Override
	public Iterable<IRetaincard> list(IFilter filter) {
		List<IRetaincard> result = new ArrayList<IRetaincard>();
		for (Retaincard each : entities) {
			if (memService.isMacth(each, filter)) {
				result.add(each);
			}
		}
		return result;
	}

	@Override
	public IRetaincard add(IRetaincard retaincard) {
		Retaincard entity = memService.interface2Entity(retaincard);
		entity.setId(memService.nextId());
		entities.add(entity);
		return entity;
	}

	@Override
	public List<IRetaincard> getCardByTerminalId(String terminalId) {
		List<IRetaincard> result = new ArrayList<IRetaincard>();
		Iterable<IRetaincard> list = list();
		for(IRetaincard item : list){
			if(item.getTerminalId().equals(terminalId)){
				result.add(item);
			}
		}
		return result;
	}

	@Override
	public List<IRetaincard> listCardByOrgId(long orgId ,IFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPageResult<IRetaincard> page(int offset, int limit, IFilter filter,
			long orgId, CardStatus status1, CardStatus status2) {
		// TODO Auto-generated method stub
		return null;
	}

}
