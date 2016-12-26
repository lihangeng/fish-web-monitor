package com.yihuacomputer.fish.api.quittingNotice;

import java.util.Date;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
/**
 * 报停服务
 * @author xuxiang
 *
 */
public interface IQuittingNoticeService
{
    /**
     * @return
     */
    public IQuittingNotice make();

    /**
     * @param id
     * @return
     */
    public IQuittingNotice get(long id);

    /**
     * @param deviceCode
     * @return
     */
    public IQuittingNotice get(String deviceCode);
    /**
     * 增加停机信息
     * @param quittingNotice
     * @return
     */
    public IQuittingNotice add(IQuittingNotice quittingNotice);

    /**
     * @param id
     */
    public void remove(long id);

    /**
     * @param deviceCode
     */
    public void remove(String deviceCode);
    /**
     * 更新停机信息
     * @param quittingNotice
     */
    public void update(IQuittingNotice quittingNotice);

    /**
     * @return
     */
    public Iterable<IQuittingNotice> list();

    /**
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IQuittingNotice> page(int offset, int limit,IFilter filter);

    /**
     * @param offset
     * @param limit
     * @param filter
     * @param orgId
     * @param flag
     * @return
     */
    public IPageResult<IQuittingNotice> page(int offset, int limit,IFilter filter,String orgId,boolean flag);

    /**
     * @param filter
     * @return
     */
    public Iterable<IQuittingNotice> list(IFilter filter);
    
	/**
	 * @param deviceCode
	 * @param openTime
	 * @return
	 */
	public IQuittingNotice get(String deviceCode, Date openTime);

}
