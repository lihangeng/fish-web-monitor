package com.yihuacomputer.fish.api.report;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IDayOpenRateService {
    public IDayOpenRate make();

    public void save(IDayOpenRate dayOpenRate);

    public List<IDayOpenRate> list();

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

    public IPageResult<IDayOpenRate> pageType(int offset, int limit, IFilter filter);

    public List<IDayOpenRate> listType(IFilter filter);

    public IPageResult<IDayOpenRate> pageOrg(int offset, int limit, IFilter filter);

    public List<IDayOpenRate> listOrg(IFilter filter);

    public List<IDayOpenRate> listDev(IFilter filter);
    
    public IPageResult<IDayOpenRate> pageDev(int offset, int limit, IFilter filter);
}
