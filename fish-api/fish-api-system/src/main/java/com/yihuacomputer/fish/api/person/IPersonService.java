package com.yihuacomputer.fish.api.person;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 人员服务接口
 * 
 * @author huxiaobao
 * 
 */
public interface IPersonService {

    /**
     * 创建一条人员信息
     * 
     * @return
     */
    public IPerson make();

    /**
     * 根据主键找到人员信息
     * 
     * @param guid
     * @return
     */
    public IPerson get(String guid);

    /**
     * 根据name增加一条人员信息
     * 
     * @param name
     * @return
     */
    public IPerson add(String name);

    /**
     * 增加人员信息
     * 
     * @param person
     * @return
     */
    public IPerson add(IPerson person);

    /**
     * 根据主键删除人员信息
     * 
     * @param guid
     */
    public void remove(String guid);

    /**
     * 更新人员信息
     * 
     * @param person
     */
    public void update(IPerson person);

    /**
     * 返回所有人员列表
     * 
     * @return
     */
    public List<IPerson> getRoot();

    /**
     * 返回人员列表
     * 
     * @return
     */
    public Iterable<IPerson> list();

    /**
     * 根据条件查找人员信息，返回用户列表
     * 
     * @param filter
     * @return
     */
    public List<IPerson> list(IFilter filter);

    public IPageResult<IPerson> page(int offset, int limit);

    public IPageResult<IPerson> page(int offset, int limit, IFilter filter);

    /**
     * 根据机构深度查询该机构和下属机构下的人员
     * 
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IPerson> page(int offset, int limit, String org);
    
    /**
     * 根据机构深度查询该机构和下属机构下的符合条件的人员
     * @param offset
     * @param limit
     * @param org
     * @param filter
     * @return
     */
    public IPageResult<IPerson> page(int offset, int limit, String org,IFilter filter);

    public IPageResult<IPerson> pageUnlinkUser(int offset, int limit, IFilter filter);

	public IOrganizationService getOrgService();
	
	/**
	 * 根据所属机构取人员
	 * @return
	 */
	public List<IPerson> getByOrg(String orgId);

}
