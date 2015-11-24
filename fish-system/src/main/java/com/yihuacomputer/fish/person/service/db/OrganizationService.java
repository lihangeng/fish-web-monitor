package com.yihuacomputer.fish.person.service.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationListener;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.person.service.base.DomainOrganizationService;
import com.yihuacomputer.fish.system.entity.Organization;

/**
 * 数据库版OrganizationService实现：
 *
 * @author huxiaobao
 *
 */
@Service
@Transactional
public class OrganizationService extends DomainOrganizationService {

    @Autowired
    private IGenericDao dao;

	@Autowired
	private SessionFactory sessionFactory;

    private List<IOrganizationListener> orgListeners = new ArrayList<IOrganizationListener>();

    private final String SUB_ORG_HQL = "select o.id from Organization o where o.orgFlag like ? ";
    
    /**
     * 如果为1则之前是根节点
     */
    private final static int ISLEAF=1;

    /**
     * 增加一条机构信息并初始化其编号和名称
     */
    @Override
    public IOrganization add(String code, String name) {
        Organization organization = make();
        organization.setCode(code);
        organization.setName(name);
        return add(code, name, null);
    }

    /**
     * 增加一条机构信息并初始化其编号、名称和其父机构
     */
    @Override
    public Organization add(String code, String name, Organization parent) {
        Organization organization = make();
        organization.setCode(code);
        organization.setName(name);
        organization.setParent(parent);
        return add(organization);
    }

    /**
     * 增加一条机构信息
     */
    @Override
    @Transactional
    public Organization add(IOrganization entity) {
        if (entity instanceof Organization) {
            Organization org = (Organization) entity;
            dao.save(entity);
            IOrganization parent = org.getParent();
            if(parent!=null){
	            if(parent.isLeaf()){
	            	parent.setLeaf(false);
	            	dao.update(parent);
	            }
            }
            autoSetOrgFlag(org);
            dao.update(entity);
            return org;
        }
        return null;
    }

    /**
     * 根据外部ID获得机构信息
     */
    @Override
    @Cacheable(value = "orgs",key = "#guid")
    public IOrganization get(String guid) {
        return dao.get(Long.valueOf(guid), Organization.class);
    }

    /**
     * 取到当前机构子机构中最小的机构级别
     *
     * @param id
     * @return
     */
    public IOrganization getMinOrgLevel(String code) {
        String byCode = "from Organization as org where org.code = ?";
        List<IOrganization> codeList = dao.findByHQL(byCode, code);

        // 查询不到数据时,返回null.
        if (codeList == null || codeList.isEmpty()) {
            return null;
        }

        String hql = "from Organization as org where org.parent = ?";

        List<IOrganization> subList = dao.findByHQL(hql, codeList.get(0));

        // 查询不到数据时,返回null.
        if (subList == null || subList.isEmpty()) {
            return null;
        }

        int minLevel = subList.get(0).getOrganizationLevel().getId();

        // 默认值
        IOrganization org = subList.get(0);

        // 循环比较最小机构值
        for (IOrganization organization : subList) {
            if (organization.getOrganizationLevel().getId() < minLevel) {
                org = organization;
            }
        }

        return org;
    }

    /**
     * 获得跟节点的所有机构信息
     */
    @Override
    public List<IOrganization> getRoot() {
        List<IOrganization> result = new ArrayList<IOrganization>();
        for (IOrganization each : list()) {
            if (each.getParent() == null) {
                result.add(each);
            }
        }
        return result;
    }

    /**
     * 获得所有机构信息
     */
    @Override
    @Transactional(readOnly = true)
    public Iterable<IOrganization> list() {
        return dao.loadAll(IOrganization.class);
    }

    /**
     * 根据外部ID删除机构信息
     */
    @Override
    @CacheEvict(value = "orgs",key = "#guid")
    public void remove(String guid) {
    	IOrganization org = this.get(guid);
    	if(org==null){
    		return;
    	}
    	IOrganization parent = org.getParent();
    	
    	 dao.delete(Long.valueOf(guid), Organization.class);
 		if(!parent.listChildren().iterator().hasNext()){
 			parent.setLeaf(true);
 			dao.update(parent);
 		}
    }

    /**
     * 更新机构信息
     */
    @Override
    public void update(IOrganization organization) {
        Organization entity = interface2Entity(organization, true);
        String oldOrgFlag = entity.getOrgFlag();
        autoSetOrgFlag(entity);
        //新的父节点
        IOrganization newParent = entity.getParent();
    	Session session = sessionFactory.getCurrentSession();
        session.clear();
        dao.update(entity);
        //父节点的子节点包含新增的机构
        List<IOrganization> childrenList = (ArrayList<IOrganization>)newParent.listChildren();
        String newOrgFlag = entity.getOrgFlag();
        // 级联修改子组织的orgFlag
        if (!oldOrgFlag.equalsIgnoreCase(newOrgFlag)) {
        	//迁移后的机构数量为1，则要更改当前新父节点的leaf属性为false,非叶子节点
            if(childrenList.size()==ISLEAF){
                session.clear();
            	newParent.setLeaf(false);
            	dao.update(newParent);
            }
        	String oldFlags[] = oldOrgFlag.split("-");
        	if(oldFlags.length>2){
        		IOrganization oldParentOrg = this.get(oldFlags[oldFlags.length-2]);
        		Iterator<IOrganization> orgIterable = oldParentOrg.listChildren().iterator();
//        		orgIterable.next();
        		if(!orgIterable.hasNext()){
                    session.clear();
        			oldParentOrg.setLeaf(true);
        			dao.update(oldParentOrg);
        		}
        	}
            for (IOrganization child : entity.listChildren()) {
                update(child);
            }
        }
    }

    private void autoSetOrgFlag(Organization entity) {
        String orgFlag =  entity.getGuid() +"-";
        if (entity.getParent() != null) {
            orgFlag = ((Organization) entity.getParent()).getOrgFlag() +orgFlag ;
        }
        entity.setOrgFlag(orgFlag);
    }

    /**
     * 保证操作的机构信息类的准确
     *
     * @param organization
     * @param load
     * @return
     */
    private Organization interface2Entity(IOrganization organization, boolean load) {
        if (organization instanceof Organization) {
            return (Organization) organization;
        }
        return null;
    }

    /**
     * 分页显示机构信息
     */
    @Override
    @Transactional(readOnly = true)
    public IPageResult<IOrganization> page(int offset, int limit) {
        return dao.page(offset, limit, new Filter(), IOrganization.class);
    }

    /**
     * 有条件的分页显示机构信息
     */
    @Override
    @Transactional(readOnly = true)
    public IPageResult<IOrganization> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, Organization.class);
    }

    @Override
    public Iterable<IOrganization> list(IFilter filter) {
        return dao.findByFilter(filter, IOrganization.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterable<IOrganization> list(long orgId) {
        IOrganization org = get(String.valueOf(orgId));
        return dao.getCriteria(Organization.class).add(Restrictions.like("orgFlag", org.getOrgFlag(), MatchMode.START))
                .list();
    }

    @Override
    public List<Long> listSubOrgId(String orgId) {
        IOrganization org = this.get(orgId);
        return this.dao.findByHQL(this.SUB_ORG_HQL,  org.getOrgFlag() +"%");
    }

    @SuppressWarnings("unchecked")
    @Override
    public IPageResult<IOrganization> page(int offset, int limit, IFilter filter, String orgId) {
        IOrganization org = this.get(orgId);
        String hql = "select o from Organization o where o.orgFlag like ? ";
        return (IPageResult<IOrganization>) this.dao.page(offset, limit, filter, hql, org.getOrgFlag() + "%");
    }

    @Override
    public List<IOrganization> listChildren(String parentOrgId, OrganizationType orgType) {
        IFilter filter = new Filter();
        IOrganization org = this.get(parentOrgId);
        filter.eq("parent", org);
        filter.eq("organizationType", orgType);
        List<IOrganization> listOrg = (List<IOrganization>) this.list(filter);
        return listOrg;
    }

    @Override
    public List<IOrganization> listSubOrg(String orgId) {
        IOrganization org = this.get(orgId);
        // 取得所有子机构
        IFilter filterOrg = new Filter();
        // 过滤所有子机构
        filterOrg.like("orgFlag",  org.getOrgFlag() +"%");
        Iterable<IOrganization> listOrg = this.list(filterOrg);
        List<IOrganization> list = new ArrayList<IOrganization>();
        for (IOrganization iorg : listOrg) {
            list.add((Organization) iorg);
        }
        return list;
    }

    @Override
    public List<IOrganizationListener> getOrgListeners() {
        return this.orgListeners;
    }

    @Override
    public void addOrgListener(IOrganizationListener orgListener) {
        this.orgListeners.add(orgListener);
    }

    @Override
    public void removeOrgListener(IOrganizationListener orgListener) {
        this.orgListeners.remove(orgListener);
    }

 /*   @Override
    public IOrganization getByCode(String code, OrganizationType orgType) {
        Organization organization = (Organization) dao
                .getCriteria(Organization.class)
                .add(Restrictions.eq("code", code))
                .add(Restrictions.or(Restrictions.eq("organizationType", orgType),
                Restrictions.isNull("organizationType"))).uniqueResult();
        return organization;
    }*/

    @Override
    public List<Long> listParent(String orgId, OrganizationType orgType) {
        IOrganization org = this.get(orgId);
        List<Long> list = new ArrayList<Long>();
        while (org.getParent() != null && org.getOrganizationType().equals(orgType)) {
            list.add(org.getId());
            org = org.getParent();
        }
        return list;
    }

	@Override
	public List<IPerson> listByOrgId(String orgId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select p from User u , Person p where u.personId = p.id and p.organization.id = ? ");
		return dao.findByHQL(hql.toString(), Long.valueOf(orgId));
	}
	@Override
	public List<IOrganization> listMatching(IFilter filter) {
		return dao.findByFilter(filter, IOrganization.class);
	}

}
