package com.yihuacomputer.fish.system.service.mem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.system.entity.Param;
import com.yihuacomputer.fish.system.service.base.DomainParamService;

@Service
public class MemoryParamService extends DomainParamService {

	private BaseMemoryService memService = new BaseMemoryService();

	List<Param> entities = new ArrayList<Param>();

	@Override
	public IParam add(IParam param) {
		Param entity = memService.interface2Entity(param);
		entity.setId(memService.nextId());
		entities.add(entity);
		return entity;
	}

	@Override
	public IParam getParam(String paramKey) {
		for (Param item : entities) {
			if (paramKey.equals(item.getParamKey())) {
				return item;
			}
		}
		throw new NotFoundException(String.format("not found entity [Param.paramKey = %s]", paramKey));
	}

	@Override
	public Param get(long id) {
		for (Param item : entities) {
			if (id == item.getId()) {
				return item;
			}
		}
		throw new NotFoundException(String.format("not found entity [Param.id = %s]", id));
	}

	@Override
	public void remove(long id) {
		remove(get(id));
	}

	public void remove(IParam param) {
		entities.remove(param);
	}

	@Override
	public void remove(String paramKey) {
		remove(getParam(paramKey));
	}

	@Override
	public void update(IParam param) {
		Param org = get(param.getId());
		org.update(param);
	}

	@Override
	public Iterable<IParam> list() {
		return EntityUtils.<IParam> convert(entities);
	}

	@Override
	public Iterable<IParam> list(IFilter filter) {
		List<IParam> result = new ArrayList<IParam>();
		for (Param each : entities) {
			if (memService.isMacth(each, filter)) {
				result.add(each);
			}
		}
		return result;
	}

	@Override
	public IPageResult<IParam> page(int offset, int limit, IFilter filter) {
		List<IParam> params = new ArrayList<IParam>();
		EntityUtils.convert(list(filter),params);
		return EntityUtils.convert(new PageResult<IParam>(params,offset,limit));
	}
}
