package com.yihuacomputer.fish.person.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.person.service.base.DomainPersonService;
import com.yihuacomputer.fish.system.entity.Person;

/**
 * 数据库版PersonService实现：
 *
 * @author huxiaobao
 *
 */
@Service
@Transactional
public class PersonService extends DomainPersonService {

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IOrganizationService orgService;

    /**
     * 根据名称增加人员信息
     */
    @Override
    public Person add(String name) {
        Person person = make();
        person.setName(name);
        return dao.save(person);
    }

    /**
     * 增加一条人员信息
     */
    @Override
    public Person add(IPerson person) {
        return dao.save(interface2Entity(person, false));
    }

    /**
     * 根据外部ID获得人员信息
     */
    @Override
    public Person get(String guid) {
        return dao.get(Long.valueOf(guid) , Person.class);
    }

    // /**
    // * 根据编号获得人员信息
    // */
    // @Override
    // public Person findByCode(String code) {
    // Person person =
    // (Person)dao.getCriteria(Person.class).add(Restrictions.eq("code",
    // code)).uniqueResult();
    // return person;
    // }

    /**
     * 获得所有人员信息列表
     */
    @Override
    @Transactional(readOnly = true)
    public Iterable<IPerson> list() {
        return dao.loadAll(IPerson.class);
    }

    /**
     * 获得跟节点的所有 人员信息
     */
    @Override
    public List<IPerson> getRoot() {
        List<IPerson> result = new ArrayList<IPerson>();
        for (IPerson each : list()) {
            if (each.getOrganization() == null) {
                result.add(each);
            }
        }
        return result;
    }

    /**
     * 分页显示人员信息
     */
    /**
     * 根据外部ID删除人员信息
     */
    @Override
    public void remove(String guid) {
        dao.delete(Long.valueOf(guid), Person.class);
    }

    /**
     * 更新人员信息
     */
    @Override
    public void update(IPerson person) {
        dao.update(interface2Entity(person, true));
    }

    /**
     * 保证操作的人员信息准确
     *
     * @param person
     * @param load
     * @return
     */
    private Person interface2Entity(IPerson person, boolean load) {
        if (person instanceof Person) {
            return (Person) person;
        }
        return null;
    }

    /**
     * 根据条件后的所有人员信息
     */
    @Override
    public List<IPerson> list(IFilter filter) {
        return dao.findByFilter(filter, IPerson.class);
    }

    /**
     * 根据过滤条件分页显示人员信息
     */
    @Override
    @Transactional(readOnly = true)
    public IPageResult<IPerson> page(int offset, int limit) {
        return page(offset, limit, new Filter());
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IPerson> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, Person.class);
    }

    /**
     * 根据过滤条件分页显示无账号的人员信息
     */
    @Override
    public IPageResult<IPerson> pageUnlinkUser(int offset, int limit, IFilter filter) {
        return null;
    }

    /**
     * 根据机构深度查询该机构和下属机构下的人员
     */
    @SuppressWarnings("unchecked")
	@Override
    public IPageResult<IPerson> page(int offset, int limit, String orgId) {
        StringBuffer sql = new StringBuffer();
        IOrganization org = orgService.get(orgId);
        sql.append("from Person p ");
        sql.append("where p.organization.orgFlag like ?");
        IPageResult<IPerson> result = (IPageResult<IPerson>) this.dao.page(offset, limit, sql.toString(), "%" + org.getOrgFlag());
        return result;
    }

    @SuppressWarnings("unchecked")
	@Override
    public IPageResult<IPerson> page(int offset, int limit, String orgId,
            IFilter filter) {
        StringBuffer sql = new StringBuffer();
        IOrganization org = orgService.get(orgId);
        sql.append("from Person p ");
        sql.append("where p.organization.orgFlag like ?");
        IPageResult<IPerson> result = (IPageResult<IPerson>) this.dao.page(offset, limit,filter, sql.toString(), "%" + org.getOrgFlag());
        return result;
    }

	@Override
	public IOrganizationService getOrgService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IPerson> getByOrg(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

}
