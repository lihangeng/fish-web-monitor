package com.yihuacomputer.fish.person.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.domain.util.DBType;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IUserLog;
import com.yihuacomputer.fish.api.person.IUserLogService;
import com.yihuacomputer.fish.system.entity.UserLog;

/**
 * 数据库版UserLogService实现：
 *
 * @author shixiaolong
 *
 */
@Service
@Transactional
public class UserLogService implements IUserLogService {

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IOrganizationService orgService;

    @Autowired
    private LocalSessionFactoryBean sf;

    @Override
    public UserLog make() {
        return new UserLog();
    }
    
    @Override
    public UserLog add(IUserLog entity) {
        return dao.save(interface2Entity(entity, false));
    }

    /**
     * 根据过滤条件分页显示公告信息
     */
    @Override
    @Transactional(readOnly = true)
    public IPageResult<IUserLog> page(int offset, int limit) {
        return page(offset, limit, new Filter());
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IUserLog> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, UserLog.class);
    }

    @SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly = true)
    public IPageResult<IUserLog> page(int offset, int limit, IFilter filter, String orgId) {
        StringBuffer hql = new StringBuffer();
        IOrganization org = orgService.get(orgId);
        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry operTimeStart = filter.getFilterEntry("operTimeStart");
        IFilterEntry operTimeEnd = filter.getFilterEntry("operTimeEnd");
        IFilterEntry operCode = filter.getFilterEntry("operCode");
        hql.append("select userLog from UserLog userLog,User user,Person person ");
        hql.append(" where userLog.operCode = user.code");
        DBType dbType = new DBType(sf.getHibernateProperties());
        if(dbType.isSybase()){
            hql.append(" and user.personId = convert(varchar,person.id) ");
        }else{
            hql.append(" and user.personId = person.id ");
        }
        hql.append(" and person.organization.orgFlag like ? ");
        valueObj.add("%" + org.getOrgFlag());
        if(operTimeStart!=null){
            hql.append(" and userLog.operTime >= ? ");
            valueObj.add(DateUtils.getTimestamp(operTimeStart.getValue().toString()));
        }
        if(operTimeEnd!=null){
            hql.append(" and userLog.operTime <= ? ");
            valueObj.add(DateUtils.getTimestamp(operTimeEnd.getValue().toString()));
        }
        if(operCode!=null){
            hql.append(" and userLog.operCode = ? ");
            valueObj.add(operCode.getValue());
        }
        hql.append(" order by userLog.operTime desc");
        IPageResult<IUserLog> result = (IPageResult<IUserLog>)this.dao.page(offset,limit,hql.toString(), valueObj.toArray());
        return result;
    }


    /**
     * 保证信息准确
     *
     * @param person
     * @param load
     * @return
     */
    private UserLog interface2Entity(IUserLog entity, boolean load) {
        if (entity instanceof UserLog) {
            return (UserLog) entity;
        }
        return null;
    }

    @Override
    public void remove(String code) {
        Filter filter = new Filter();
        filter.addFilterEntry(FilterFactory.eq("operCode",code));
        List<UserLog> result = dao.findByFilter(filter, UserLog.class);
        for(UserLog log : result){
            dao.delete(log);
        }
    }

   //邯郸银行删除操作日志功能
	@Override
	public String deleteUserLogs(String currentTime) {
		String hqlDelete = "delete UserLog where operTime < ?";
		String hqlSearch = " from  UserLog where operTime < ?";
		List<IUserLog> userLog = dao.findByHQL(hqlSearch, DateUtils.getTimestamp(currentTime));
		if(userLog.isEmpty())
		{
            return "noElements";
		}
		dao.batchUpdate(hqlDelete,  DateUtils.getTimestamp(currentTime));
		   return "deleteSuccess";
	}

    //邯郸操作员日志分等级查询使用
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public IPageResult<IUserLog> searchLog(int offset, int limit, IFilter filter, String orgFlag) {
        StringBuffer hql = new StringBuffer();
        List<Object> objvalue = new ArrayList<Object>();
        hql.append("select userLog from UserLog userLog,User user,Person person ");
        hql.append(" where userLog.operCode = user.code");
        hql.append(" and user.personId = person.id ");
        hql.append(" and person.organization.orgFlag like ? ");
        objvalue.add("%" + orgFlag);
        hql.append(" and person.organization.orgFlag  <> ?");
        objvalue.add(orgFlag);
        IPageResult<IUserLog> result = (IPageResult<IUserLog>) dao.page(offset, limit, filter, hql.toString(),
                objvalue.toArray());
        return result;

    }

}
