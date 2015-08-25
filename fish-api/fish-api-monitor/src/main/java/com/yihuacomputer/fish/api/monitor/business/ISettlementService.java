package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * C端应用清机服务
 * 
 * @author YiHua
 * 
 */
public interface ISettlementService {

    /**
     * ATMC端进行清机时向后端发送清机报文
     * 
     * @param settlement
     */
    public void save(ISettlement settlement);

    /**
     * 获取ATMC端进行清机时向后端发送的清机报文
     * 
     * @param terminalId
     * @return
     */
    public ISettlement load(String terminalId);

    /**
     * 创建清机结账实体
     * 
     * @return
     */
    public ISettlement make();

    /**
     * 通过id,获取清机信息
     * 
     * @param id
     *            设备id
     * @return 设备对象
     */
    public ISettlement get(long id);

    /**
     * 通过设备号,获取清机信息
     * 
     * @param code
     *            设备号
     * @return　设备对象
     */
    public ISettlement get(String code);

    /**
     * 获取所有的清机信息
     * 
     * @return　清机集合
     */
    public List<ISettlement> list();

    /**
     * 获取符合条件的清机信息
     * 
     * @param filter
     *            条件
     * @return　清机集合
     */
    public List<ISettlement> list(IFilter filter);

    /**
     * 获取清机的分页信息
     * 
     * @param offset
     *            开始
     * @param limit
     *            结束
     * @param filter
     *            过滤
     * @return　分页信息　
     */
    public IPageResult<ISettlement> page(int offset, int limit, IFilter filter);
}
