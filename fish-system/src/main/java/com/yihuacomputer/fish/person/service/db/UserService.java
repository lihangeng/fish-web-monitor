package com.yihuacomputer.fish.person.service.db;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.exception.ServiceException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.MsgDigestAlgorithm;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.domain.util.DBType;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.UserState;
import com.yihuacomputer.fish.person.service.base.DomainUserService;
import com.yihuacomputer.fish.system.entity.User;

/**
 * 账户信息服务
 * @author xuxigang
 * @version
 * @since
 * @date 2010-7-31
 */
@Service
@Transactional
public class UserService extends DomainUserService {

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IOrganizationService orgService;

    @Autowired
    private LocalSessionFactoryBean sf;

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	private MessageSource messageSourceVersion;

	private final long FREEZEMILLISECONDS =300000l;
	
	private final long PERMINMILLISECONDS =60000l;
    /**
     * 增加一条账户信息并初始化账号
     */
    @Override
    public  IUser add(String code, IPerson person) {
        User user = make();
        user.setCode(code);
        user.setPerson(person);
        return add(user);
    }

    /**
     * 给一存在的人员分配一个账户（注意：该人员的编号要存在）
     */
    @Override
    public IUser add(IUser user){
        return dao.save(interface2Entity(user));
    }

    /**
     * 根据账号得到账户信息
     */
    @Override
    public User get(String code) {
        User user = (User)dao.getCriteria(User.class)
                        .add(Restrictions.eq("code",code)).uniqueResult();
        if(user == null){
            throw new NotFoundException(messageSourceVersion.getMessage("system.user.notExistUserName", new Object[]{code}, FishCfg.locale));
        }
        return user;
    }

    /**
     * 根据人员ID得到账户信息
     */
    @Override
    public User getByPerson(String id) {
        User user = (User)dao.getCriteria(User.class).add(Restrictions.eq("personId",id)).uniqueResult();
        if(user == null){
            throw new NotFoundException(messageSourceVersion.getMessage("system.user.notExistPersonId", new Object[]{id}, FishCfg.locale));
        }
        return user;
    }

    /**
     * 根据ID获得账户信息
     */
    @Override
    @Transactional(readOnly=true)
    @Cacheable(value = "users",key = "#id")
    public IUser get(long id) {
        return dao.get(id,User.class);
    }

    /**
     * 获得所有的账户信息
     */
    @Override
    @Transactional(readOnly=true)
    public Iterable<IUser> list() {
        return dao.loadAll(IUser.class);
    }

    /**
     * 更新账户信息
     */
    @Override
    public void update(IUser user) {
         dao.update(interface2Entity(user));
    }

     /**
     * 根据ID删除一条账户信息
     */
    @Override
    @CacheEvict(value = "users",key = "#id")
    public void remove(long id) {
       dao.delete(id,User.class);
    }

    /**
     * 根据账号删除一条账户信息
     */
    @Override
    public void remove(String code) {
        try{
            dao.delete(get(code));
        }catch(NotFoundException nfe){
            throw nfe;
        }catch(Exception ex){
            throw new ServiceException(messageSourceVersion.getMessage("system.user.deleteFailByCode", new Object[]{code}, FishCfg.locale),ex);
        }
    }

    /**
     * 删除账户信息
     */
    @Override
    public void remove(User user){
        dao.delete(user.getId());
    }

    /**
     * 格式化账户信息
     */
    @Override
    public User convert(IUser user){
        return this.interface2Entity(user);
    }

    /**
     * 保证操作的账户信息正确
     * @param User
     * @param load
     * @return
     */
    private User interface2Entity(IUser user){
        if(user instanceof User){
            return (User)user;
        }
        return null;
    }

    /**
     * 根据条件查询账户信息
     */
    @Override
    @Transactional(readOnly=true)
    public Iterable<IUser> list(IFilter filter) {
        Filter db = new Filter();
        Filter mem = new Filter();
        db.addFilterEntry(FilterFactory.eq("system", false));//过滤掉系统内置账号
        for(IFilterEntry entry :filter.entrySet()){
            if(entry.getKey().indexOf(".") > 0){
                mem.addFilterEntry(entry);
            }else{
                db.addFilterEntry(entry);
            }
        }
        List<IUser> users =  dao.findByFilter(db, IUser.class);
        return mem.filter(users);
    }

    /**
     * 根据条件分页显示人员信息
     */
	@Override
    @Transactional(readOnly=true)
    public IPageResult<IUser> page(int offset, int limit,IFilter filter) {
	     return dao.page(offset, limit, filter, User.class);
    }

     /**
     * 根据机构深度查询该机构和下属机构下的用户
     */
    @Override
    @SuppressWarnings("unchecked")
    public IPageResult<IUser> page(int offset, int limit, IFilter filter ,String orgId) {
        StringBuffer hql = new StringBuffer();
        IOrganization org = orgService.get(orgId);
        hql.append("select user from User user ,Person person where 1=1 ");
        DBType dbType = new DBType(sf.getHibernateProperties());
        if(dbType.isSybase()){
            hql.append(" and user.personId = convert(varchar,person.id) ");
        }else{
            hql.append(" and user.personId = person.id ");
        }
        hql.append(" and person.organization.orgFlag like ? order by user.id desc");
        return (IPageResult<IUser>) this.dao.page(offset, limit,filter, hql.toString(),org.getOrgFlag()  + "%");
    }

    /**
     * 
     */
    @Transactional(noRollbackFor = {AppException.class,PasswordErrorException.class})
    @Override
    public IUser login(String userCode,String plainText){
        User user = null;
        try{
            //验证用户名是否存在
            user = dao.findUniqueByHql("from User user where user.code = ? and user.userState <> ?", userCode,UserState.REMOVED);
            if(user == null){
                throw new NotFoundException(messageSourceVersion.getMessage("system.user.userNameNotExistRedo", new Object[]{userCode}, FishCfg.locale));
            }

            isValidUser(user,plainText);
            return user;
        }catch(PasswordErrorException pe){
        	if(user != null){
        		 user.setAccessAccount(user.getAccessAccount() + 1);
                 user.setLoginFailCount(user.getLoginFailCount() + 1);
                 dao.update(user);
        	}
        	throw pe;
        }catch(AppException e){
            //记录失败
            if(user != null){
                 user.setAccessAccount(user.getAccessAccount() + 1);
                 dao.update(user);
            }
            throw e;
        }
    }

    /**
     * 
     * @param user
     * @param plainText
     */
    public void isValidUser(User user,String plainText){
        //验证是否被冻结
        Date currentDate = new Date();
        if(user.getFreezeTime() != null){
            if(currentDate.getTime()-user.getFreezeTime().getTime()>FREEZEMILLISECONDS){//锁定时间大于5分钟,解冻
                 user.setFreezeTime(null);
                 user.setLoginFailCount(0);
                 dao.update(user);
            }else{
                throw new AppException(messageSourceVersion.getMessage("system.user.passwdErrorToFreeze", new Object[]{user.getLoginFailCount()+1,FREEZEMILLISECONDS/PERMINMILLISECONDS}, FishCfg.locale));
            }
        }

        //验证密码
        String pwd = MsgDigestAlgorithm.getMD5Str(plainText);
        if(!user.getPassword().equals(pwd)){
			//@since 2.0.0.0 将连续3次输入密码错误修改成连续5次输入密码错误。
            if(user.getLoginFailCount() >= 4){//连续5次输入密码错误,冻结5分钟
                 user.setFreezeTime(currentDate);
                 dao.update(user);
                 throw new AppException(messageSourceVersion.getMessage("system.user.passwdErrorToFreeze", new Object[]{user.getLoginFailCount()+1,FREEZEMILLISECONDS/PERMINMILLISECONDS}, FishCfg.locale));
            }else{
                throw new PasswordErrorException(messageSource.getMessage("login.pwderrorfive", new Object[]{user.getLoginFailCount()+1}, FishCfg.locale));//"连续5次密码错误用户将被冻结5分钟！已输错" + (user.getLoginFailCount() + 1) + "次"
            }
        }
        //正常登陆，清空登陆失败次数
        user.setAccessAccount(user.getAccessAccount() + 1);
        user.setLoginFailCount(0);
        dao.update(user);
    }

    @Override
    public boolean isExist(String personGuid) {
        User user = dao.findUniqueByHql("from User user where user.personId = ? ", personGuid);
        if(user==null){
            //存在
            return false;
        }
        //不存在
        return true;
    }


}
