package com.yihuacomputer.fish.api.system.im;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * IM消息服务.
 * 内存版实现最多保留500条IM消息
 * @author yantao
 *
 */
public interface IMessageService {
	
	public void addListener(IMessageListener listener);
	
	public void removeListener(IMessageListener listener);
	
	public IMessage post(String user, String content);
	
	public List<IMessage> listLast(int count);
	
	public long getMinId();
	
	public long getLastId();
	
	public List<IMessage> list();
	
	public List<IMessage> list(int startId, int limit);
	
	public IPageResult<IMessage> list(int offset, int limit, IFilter filter);

	public IMessage make();

}
