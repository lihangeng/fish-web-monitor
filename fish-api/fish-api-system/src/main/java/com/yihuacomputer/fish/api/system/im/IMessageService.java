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
	
	/**
	 * @param listener
	 */
	public void addListener(IMessageListener listener);
	
	/**
	 * @param listener
	 */
	public void removeListener(IMessageListener listener);
	
	/**
	 * @param user
	 * @param content
	 * @return
	 */
	public IMessage post(String user, String content);
	
	/**
	 * @param count
	 * @return
	 */
	public List<IMessage> listLast(int count);
	
	/**
	 * @return
	 */
	public long getMinId();
	
	/**
	 * @return
	 */
	public long getLastId();
	
	/**
	 * @return
	 */
	public List<IMessage> list();
	
	/**
	 * @param startId
	 * @param limit
	 * @return
	 */
	public List<IMessage> list(int startId, int limit);
	
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IMessage> list(int offset, int limit, IFilter filter);

	/**
	 * @return
	 */
	public IMessage make();

}
