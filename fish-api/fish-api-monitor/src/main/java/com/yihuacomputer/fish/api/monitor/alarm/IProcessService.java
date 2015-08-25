package com.yihuacomputer.fish.api.monitor.alarm;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
/**
 * 进程信息服务模块
 * @author YiHua
 *
 */
public interface IProcessService
{
    public IProcess make();

    public IIllegalProcess makeIlegProc();

    public IProcess get(long id);

    public IProcess add(IProcess process);

    public void remove(long id);

    public void update(IProcess process);

    public Iterable<IProcess> list();

    public IPageResult<IProcess> page(int offset, int limit,IFilter filter);

    public Iterable<IProcess> list(IFilter filter);

    public List<IProcess> getSchindlerList();

    public List <IProcess> findByHQL(String value);

    public void saveSchindlerAlarm(String terminalId,List<IIllegalProcess> processList);

    /**
     * 根据获得黑名单进程：
     * @param id
     * @return
     */
    public IIllegalProcess getSchindlerAlarm(long id);

    /**
     * 获得全部黑名单进程：
     * @return
     */
    public Iterable<IIllegalProcess> listSchindlerAlarm();

    /**
     * 根据条件获得黑名单进程列表：
     * @param filter
     * @return
     */
    public Iterable<IIllegalProcess> listSchindlerAlarm(IFilter filter);

    /**
     * 分页显示黑名单进程列表：
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IIllegalProcess> pageSchindlerAlarm(int offset, int limit,IFilter filter);

   // public void pushSchndlerAlarm(List<IProcess> processList);
}
