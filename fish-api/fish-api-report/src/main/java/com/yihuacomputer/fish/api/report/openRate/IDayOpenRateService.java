package com.yihuacomputer.fish.api.report.openRate;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IDayOpenRateService {
    /**
     * @return
     */
    public IDayOpenRate make();

    /**
     * @param dayOpenRate
     */
    public void save(IDayOpenRate dayOpenRate);

    /**
     * @return
     */
    public List<IDayOpenRate> list();

    /**
     * @param filter
     * @return
     */
    public List<IDayOpenRate> list(IFilter filter);

    /**
     * 获取开机率的分页信息
     * 
     * @param offset
     *            开始
     * @param limit
     *            结束
     * @param filter
     *            过滤
     * @return　分页信息　
     */
    public IPageResult<IDayOpenRate> page(int offset, int limit, IFilter filter);

    /**
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IDayOpenRate> pageType(int offset, int limit, IFilter filter);

    /**
     * @param filter
     * @return
     */
    public List<IDayOpenRate> listType(IFilter filter);

    /**
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IDayOpenRate> pageOrg(int offset, int limit, IFilter filter);

    /**
     * @param filter
     * @return
     */
    public List<IDayOpenRate> listOrg(IFilter filter);

    /**
     * @param filter
     * @return
     */
    public List<IDayOpenRate> listDev(IFilter filter);

    /**
     * 根据设备号获取近一个周的开机率信息
     * @param terminalId
     * @return
     */
    public List<IDayOpenRate> listByDev(String terminalId);
    
    /**
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IDayOpenRate> pageDev(int offset, int limit, IFilter filter);
}
