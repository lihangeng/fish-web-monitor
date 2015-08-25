package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * C端应用加钞服务
 * 
 * @author YiHua
 * 
 */
public interface ICashInitService {

    /**
     * ATMC端进行加钞时向后端发送加钞报文
     * 
     * @param cashInit
     */
    public void save(ICashInit cashInit);

    /**
     * 获取ATMC端进行加钞时向后端发送的加钞报文
     * 
     * @param terminalId
     * @return
     */
    public ICashInit load(String terminalId);

    /**
     * 创建加钞信息实体
     * 
     * @return
     */
    public ICashInit make();

    /**
     * 通过id,获取加钞信息
     * 
     * @param id
     *            设备id
     * @return 加钞对象
     */
    public ICashInit get(long id);

    /**
     * 通过设备号,获取加钞信息
     * 
     * @param code
     *            设备号
     * @return　加钞对象
     */
    public ICashInit get(String code);

    /**
     * 获取所有的加钞信息
     * 
     * @return　加钞集合
     */
    public List<ICashInit> list();

    /**
     * 获取符合条件的加钞信息
     * 
     * @param filter
     *            条件
     * @return　加钞集合
     */
    public List<ICashInit> list(IFilter filter);

    /**
     * 获取加钞的分页信息
     * 
     * @param offset
     *            开始
     * @param limit
     *            结束
     * @param filter
     *            过滤
     * @return　分页信息　
     */
    public IPageResult<ICashInit> page(int offset, int limit, IFilter filter);
}
