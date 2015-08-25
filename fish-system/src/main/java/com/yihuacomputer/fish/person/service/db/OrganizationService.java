package com.yihuacomputer.fish.person.service.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.exception.ServiceException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationListener;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.person.entity.Organization;
import com.yihuacomputer.fish.person.service.base.DomainOrganizationService;

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
    public Organization add(IOrganization entity) {
        if (entity instanceof Organization) {
            Organization org = (Organization) entity;
            dao.save(entity);
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
     * 根据机构名称获得机构信息
     */
    @Override
    public IOrganization getByName(String name) {
        Organization organization = (Organization) dao.getCriteria(Organization.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
        if (organization == null) {
            throw new NotFoundException(String.format("不存在机构[%s]", name));
        }
        return organization;
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
     * 根据编号获得机构信息
     */
    @Override
    public IOrganization getByCode(String code) {
        return getByCode(code, OrganizationType.BANK);
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
    public void remove(String guid) {
        try {
            dao.delete(get(guid));
        } catch (NotFoundException nfe) {
            throw nfe;
        } catch (Exception ex) {
            throw new ServiceException(String.format("删除组织[%s]失败", guid), ex);
        }
    }

    /**
     * 根据编号删除机构信息
     */
    @Override
    public void removeByCode(String code) {
        try {
            dao.delete(getByCode(code));
        } catch (NotFoundException nfe) {
            throw nfe;
        } catch (Exception ex) {
            throw new ServiceException(String.format("删除组织[%s]失败", code), ex);
        }
    }

    /**
     * 删除机构信息
     */
    @Override
    public void remove(IOrganization organization) {
        dao.delete(organization);

    }

    /**
     * 更新机构信息
     */
    @Override
    public void update(IOrganization organization) {
        Organization entity = interface2Entity(organization, true);
        String oldOrgFlag = entity.getOrgFlag();
        autoSetOrgFlag(entity);
        //因为在hibernate中同一个session里面有了两个相同标识
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        dao.update(entity);
        String newOrgFlag = entity.getOrgFlag();
        // 级联修改子组织的orgFlag
        if (!oldOrgFlag.equalsIgnoreCase(newOrgFlag)) {
            for (IOrganization child : entity.listChildren()) {
                update(child);
            }
        }
    }

    private void autoSetOrgFlag(Organization entity) {
        String orgFlag = "-" + entity.getGuid();
        if (entity.getParent() != null) {
            orgFlag = orgFlag + ((Organization) entity.getParent()).getOrgFlag();
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
        return dao.getCriteria(Organization.class).add(Restrictions.like("orgFlag", org.getOrgFlag(), MatchMode.END))
                .list();
    }

    @Override
    public List<Long> listSubOrgId(String orgId) {
        IOrganization org = this.get(orgId);
        return this.dao.findByHQL(this.SUB_ORG_HQL, "%" + org.getOrgFlag());
    }

    @SuppressWarnings("unchecked")
    @Override
    public IPageResult<IOrganization> page(int offset, int limit, IFilter filter, String orgId) {
        IOrganization org = this.get(orgId);
        String hql = "select o from Organization o where o.orgFlag like ? ";
        return (IPageResult<IOrganization>) this.dao.page(offset, limit, filter, hql, "%" + org.getOrgFlag());
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
        filterOrg.like("orgFlag", "%" + org.getOrgFlag());
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

    @Override
    public IOrganization getByCode(String code, OrganizationType orgType) {
        Organization organization = (Organization) dao
                .getCriteria(Organization.class)
                .add(Restrictions.eq("code", code))
                .add(Restrictions.or(Restrictions.eq("organizationType", orgType),
                        Restrictions.isNull("organizationType"))).uniqueResult();

        // 注释代码，查询不到数据，直接返回null,不需要抛异常
        // if (organization == null) {
        // throw new NotFoundException(String.format("不存在组织[%s]", code));
        // }

        return organization;
    }

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
