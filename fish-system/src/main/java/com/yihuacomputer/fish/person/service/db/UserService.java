package com.yihuacomputer.fish.person.service.db;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.yihuacomputer.fish.person.entity.User;
import com.yihuacomputer.fish.person.service.base.DomainUserService;

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
        return dao.save(interface2Entity(user, false));
    }

    /**
     * 根据账号得到账户信息
     */
    @Override
    public User get(String code) {
        User user = (User)dao.getCriteria(User.class)
                        .add(Restrictions.eq("code",code)).uniqueResult();
        if(user == null){
            throw new NotFoundException(String.format("输入的用户名[%s]不存在,请重新输入！",code));
        }
        return user;
    }

    /**
     * 根据人员ID得到账户信息
     */
    @Override
    public User getByPerson(String id) {
        User user = (User)dao.getCriteria(User.class)
                        .add(Restrictions.eq("personId",id)).uniqueResult();
        if(user == null){
            throw new NotFoundException(String.format("不存在人员ID[%s]",id));
        }
        return user;
    }

    /**
     * 根据ID获得账户信息
     */
    @Override
    @Transactional(readOnly=true)
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
         dao.update(interface2Entity(user,true));
    }

     /**
     * 根据ID删除一条账户信息
     */
    @Override
    public void remove(long id) {
        try{
            dao.delete(id,User.class);
        }catch(NotFoundException nfe){
            throw nfe;
        }catch(Exception ex){
            throw new ServiceException(String.format("删除主账号[%l]失败",id),ex);
        }
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
            throw new ServiceException(String.format("删除账号[%s]失败",code),ex);
        }
    }

    /**
     * 删除账户信息
     */
    public void remove(User user){
        dao.delete(user.getId());
    }

    /**
     * 格式化账户信息
     */
    public User convert(IUser user){
        return this.interface2Entity(user, true);
    }

    /**
     * 保证操作的账户信息正确
     * @param User
     * @param load
     * @return
     */
    private User interface2Entity(IUser user,boolean load){
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
        IPageResult<IUser> result = (IPageResult<IUser>) this.dao.page(offset, limit,filter, hql.toString(), "%" + org.getOrgFlag());
        return result;
    }

    @Transactional(noRollbackFor = {AppException.class,PasswordErrorException.class})
    public IUser login(String userCode,String plainText){
        User user = null;
        try{
            //验证用户名是否存在
            user = dao.findUniqueByHql("from User user where user.code = ? and user.userState <> ?", userCode,UserState.REMOVED);
            if(user == null){
                throw new NotFoundException(String.format("输入的用户名[%s]不存在,请重新输入！",userCode));
            }

            isValidUser(user,plainText);
            return user;
        }catch(AppException e){
            //记录失败
            if(user != null){
                 user.setAccessAccount(user.getAccessAccount() + 1);
                 if(e instanceof PasswordErrorException){
                     user.setLoginFailCount(user.getLoginFailCount() + 1);
                 }
                 dao.update(user);
            }
            throw e;
        }
    }

    public void isValidUser(User user,String plainText){
//@since 2.0.0.0 删除此逻辑
//        //验证锁定
//        if(user.getState().equals(UserState.LOCK)){
//            throw new AppException("密码超过60天未修改,用户已被锁定，请联系管理员！");
//        }

        //验证是否被冻结
        Date currentDate = new Date();
        if(user.getFreezeTime() != null){
            if(currentDate.getTime()-user.getFreezeTime().getTime()>300000){//锁定时间大于5分钟,解冻
                 user.setFreezeTime(null);
                 user.setLoginFailCount(0);
                 dao.update(user);
            }else{
                throw new AppException("密码错误超过5次，用户已被冻结，5分钟后再操作！");
            }
        }

        //验证密码
        String pwd = MsgDigestAlgorithm.getMD5Str(plainText);
        if(!user.getPassword().equals(pwd)){
			//@since 2.0.0.0 将连续3次输入密码错误修改成连续5次输入密码错误。
            if(user.getLoginFailCount() >= 4){//连续5次输入密码错误,冻结5分钟
                 user.setFreezeTime(currentDate);
                 dao.update(user);
                 throw new AppException("密码错误超过5次，用户已被冻结，5分钟后再操作!");
            }else{
                throw new PasswordErrorException(messageSource.getMessage("login.pwderrorfive", new Object[]{user.getLoginFailCount()+1}, FishCfg.locale));//"连续5次密码错误用户将被冻结5分钟！已输错" + (user.getLoginFailCount() + 1) + "次"
            }
        }
//@since 2.0.0.0 删除此逻辑
//        //验证是否被锁定
//        if(!user.getCode().equals("admin") && user.getAccessTime()!=null
//                && (currentDate.getTime()-user.getAccessTime().getTime()>1000l*60*60*24*60)){//超过60天未修改密码，锁定用户
//            user.setState(UserState.LOCK);
//            dao.update(user);
//            throw new AppException("密码超过60天未修改,用户已被锁定，请联系管理员！");
//        }

        //正常登陆，清空登陆失败次数
        user.setAccessAccount(user.getAccessAccount() + 1);
        user.setLoginFailCount(0);
        dao.update(user);
    }

    @Override
    public boolean isExist(String personGuid) {
        User user = null;
        user = dao.findUniqueByHql("from User user where user.personId = ? ", personGuid);
        if(user==null){
            //存在
            return false;
        }
        //不存在
        return true;
    }


}
