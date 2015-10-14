package com.yihuacomputer.fish.api.person;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 组织机构服务接口
 *
 * @author huxiaobao
 *
 */
public interface IOrganizationService
{

    /**
     * 创建组织机构实例
     *
     * @return
     */
    public IOrganization make();

    /**
     * 根据主键guid返回组织机构
     *
     * @param guid
     * @return
     */
    public IOrganization get(String guid);

    /**
     * 根据编号返回组织机构实例,默认的组织类型是银行
     *
     * @param code
     * @return
     */
    public IOrganization getByCode(String code);

    /**
     * 根据组织的code和类型来获取组织
     * @param code
     * @param orgType
     * @since 1.0.0
     * @return
     */
    public IOrganization getByCode(String code,OrganizationType orgType);

    /**
     * 返回所有组织机构列表
     *
     * @return
     */
    public List<IOrganization> getRoot();

    /**
     * 根据编号和名称增加组织机构
     *
     * @param code
     * @param name
     * @return
     */
    public IOrganization add(String code, String name);

    /**
     * 增加一条组织机构信息
     *
     * @param entity
     * @return
     */
    public IOrganization add(IOrganization entity);

    /**
     * 根据主键删除组织机构
     *
     * @param guid
     */
    public void remove(String guid);

    /**
     * 更新组织机构信息
     *
     * @param organization
     */
    public void update(IOrganization organization);

    /**
     * 返回组织机构列表
     *
     * @return
     */
    public Iterable<IOrganization> list();

    /**
     * 根据条件返回组织机构列表
     *
     * @param filter
     *            条件
     * @return 机构列表
     */
    public Iterable<IOrganization> list(IFilter filter);

    /**
     * 根据机构id，返回组织机构列表
     * @param orgId 机构id
     * @return 机构列表
     */
    public Iterable<IOrganization> list(long orgId);

    /**
     * 根据机构Id，返回机构下属列表
     * @param orgId
     * @return
     */
    public List<Long> listSubOrgId(String orgId);

    /**
     * 根据机构Id，返回机构下属列表
     * @param org
     * @return
     */
    public List<IOrganization> listSubOrg(String orgId);

    /**
     * 分页
     *
     * @param offset
     * @param limit
     * @return
     */
    public IPageResult<IOrganization> page(int offset, int limit);

    /**
     * 根据条件分页查询
     *
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IOrganization> page(int offset, int limit, IFilter filter);

    /**
     * 根据条件和机构号查询该机构下所有满足条件的机构
     * @param offset
     * @param limit
     * @param filter
     * @param orgId
     * @return
     */
    public IPageResult<IOrganization> page(int offset, int limit, IFilter filter,String orgId);

    /**
	 * 取到当前机构子机构中最小的机构级别
	 * @param id
	 * @return
	 */
    public IOrganization getMinOrgLevel(String id);

    /**
     * 查找机构下属子机构
     * @param parentOrgId
     * @param orgType
     * @return
     */
    public List<IOrganization> listChildren(String parentOrgId,OrganizationType orgType);

    /**
     * 获得组织服务注入的组织操作监听器
     * @return
     * @since 0.26
     */
    public List<IOrganizationListener> getOrgListeners();
    /**
     *
     * @param orgListener
     * @since 0.26
     */
    public void addOrgListener(IOrganizationListener orgListener);
    /**
     *
     * @param orgListener
     * @since 0.26
     */
    public void removeOrgListener(IOrganizationListener orgListener);

    /**
     * 根据机构id查找机构的所有上级机构id列表（包含本机构不包含根）
     */
    public List<Long> listParent(String parentOrgId,OrganizationType orgType);

    /**
     * 根据机构id得到机构下有用户权限的人员列表
     */
    public List<IPerson> listByOrgId(String orgId);
    
    /**
     * 模糊匹配机构信息
     * @param filter
     * @return
     */
    public List<IOrganization> listMatching(IFilter filter);
}
