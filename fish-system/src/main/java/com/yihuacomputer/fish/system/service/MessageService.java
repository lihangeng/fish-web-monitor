package com.yihuacomputer.fish.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.system.im.IMessage;
import com.yihuacomputer.fish.api.system.im.IMessageListener;
import com.yihuacomputer.fish.api.system.im.IMessageService;
import com.yihuacomputer.fish.system.entity.Message;

@Service
@Transactional
public class MessageService implements IMessageService {

	private final BaseMemoryService memService = new BaseMemoryService();

	private final List<IMessageListener> listener = new ArrayList<IMessageListener>();

	private final List<Message> entities = new ArrayList<Message>();

	@Override
	public Message make() {
		Message msg = new Message(this);
		msg.setCreateTime(new Date());
		return msg;
	}

	@Override
	public void addListener(IMessageListener listener) {
		this.listener.add(listener);
	}

	@Override
	public void removeListener(IMessageListener listener) {
		this.listener.remove(listener);
	}

	@Override
	public IMessage post(String user, String content) {
		Message msg = this.make();
		msg.setUser(user);
		msg.setContent(content);
		Message entity = memService.interface2Entity(msg);
		entity.setId(memService.nextId());
		if (this.entities.size() > 500) {
			this.entities.remove(0);
		}
		entities.add(entity);
		return entity;
	}

	@Override
	public List<IMessage> listLast(int count) {
		if (count >= this.entities.size()) {
			return EntityUtils.<IMessage> convert(entities);
		} else {
			List<IMessage> result = new ArrayList<IMessage>();
			for (int i = this.entities.size() - count; i <= this.entities.size(); i++) {
				result.add(this.entities.get(i));
			}
			return result;
		}
	}

	@Override
	public long getMinId() {
		IMessage msg = this.entities.get(0);
		if (msg != null) {
			return msg.getId();
		} else {
			return 0;
		}
	}

	@Override
	public long getLastId() {
		if (this.entities.size() != 0) {
			IMessage msg = this.entities.get(this.entities.size());
			return msg.getId();
		} else {
			return 0;
		}
	}

	@Override
	public List<IMessage> list() {
		return EntityUtils.<IMessage> convert(entities);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<IMessage> list(int startId, int limit) {
		List<IMessage> lists = new ArrayList<IMessage>();
		// EntityUtils.convert(lists);
		// TODO: 错误！！！
		return (List<IMessage>) new PageResult(lists, startId, limit);

	}

	@Override
	public IPageResult<IMessage> list(int offset, int limit, IFilter filter) {
		List<IMessage> lists = new ArrayList<IMessage>();
		EntityUtils.convert(list(filter), lists);
		return new PageResult<IMessage>(lists, offset, limit);

	}

	public Iterable<IMessage> list(IFilter filter) {
		List<IMessage> result = new ArrayList<IMessage>();
		for (Message each : entities) {
			if (memService.isMacth(each, filter)) {
				result.add(each);
			}
		}
		return result;
	}

}
