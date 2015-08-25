package com.yihuacomputer.fish.api.quittingNotice;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
/**
 * 报停服务
 * @author xuxiang
 *
 */
public interface IQuittingNoticeService
{
    public IQuittingNotice make();

    public IQuittingNotice get(long id);

    public IQuittingNotice get(String deviceCode);
    /**
     * 增加停机信息
     * @param quittingNotice
     * @return
     */
    public IQuittingNotice add(IQuittingNotice quittingNotice);

    public void remove(long id);

    public void remove(String deviceCode);
    /**
     * 更新停机信息
     * @param quittingNotice
     */
    public void update(IQuittingNotice quittingNotice);

    public Iterable<IQuittingNotice> list();

    public IPageResult<IQuittingNotice> page(int offset, int limit,IFilter filter);

    public IPageResult<IQuittingNotice> page(int offset, int limit,IFilter filter,String orgId,boolean flag);

    public Iterable<IQuittingNotice> list(IFilter filter);

}
