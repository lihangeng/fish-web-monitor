package com.yihuacomputer.fish.person.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.yihuacomputer.common.util.MsgDigestAlgorithm;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.UserState;
import com.yihuacomputer.fish.api.person.UserType;
import com.yihuacomputer.fish.person.service.api.IDomainUserService;

/**
 * 账户信息:（信息实体对应数据库表SM_USER）
 * @author xuxigang
 * @version
 * @since
 * @date 2010-7-31
 */
@Entity
@Table(name = "SM_USER")
public class User implements IUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_USER")
	@SequenceGenerator(name = "SEQ_SM_USER", sequenceName = "SEQ_SM_USER")
	@Column(name = "ID")
	private long id;

	/**
	 * 账号（使用人员信息的编号）
	 */
	@Column(name = "CODE", unique = true, nullable = false, length = 40)
	private String code;

	/**
	 * 密码
	 */
	@Column(name = "PWD", nullable = false, length = 40)
	private String password;

	/**
	 * 状态
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATE", nullable = false, length = 10)
	private UserState userState;

	/**
	 * 是否启用
	 */
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "ENABLED", nullable = true, columnDefinition="CHAR",length=1)
	private boolean enabled = true;

	/**
	 * 登录失败次数
	 */
	@Column(name = "LOGIN_FAIL_COUNT", nullable = false)
	private int loginFailCount;

	/**
	 * 冻结的时间点
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FREEZE_TIME", nullable = true)
	private Date freezeTime;

	/**
	 * 最后修改密码时间点
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACCESS_TIME", nullable = true)
	private Date accessTime;

	/**
	 * 访问次数
	 */
	@Column(name = "ACCESS_COUNT", nullable = false)
	private int accessAccount;

	@Transient
	private IPerson person;

	@Column(name = "PERSON_ID", nullable = false, length = 20)
	private String personId;

	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "IS_SYSTEM", nullable = false,columnDefinition="CHAR",length=1)
	private boolean system;

	@Column(name = "CHECK_REMARK")
	private String checkRemark;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "USER_TYPE", nullable = false, length = 3)
	private UserType userType;

	@Transient
	private IDomainUserService service;

	@Transient
	private List<IRole> roles;

	/*
	 *  密码的明文
	 */
	@Transient
	private String plainText;

	public User() {
	    this.system = false;
        this.userState = UserState.NEW;
        setPlainText("888888");
	}

	public User(IDomainUserService service) {
	    this();
		this.service = service;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		if (StringUtils.isEmpty(this.code)) {
			this.code = getPerson().getCode();
		}
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return getPerson().getName();
	}

	public String getEmail() {
		return getPerson().getEmail();
	}

	public String getMobile() {
		return getPerson().getMobile();
	}

	@Override
	public IPerson getPerson() {
		if (person == null && this.personId != null) {
			this.person = service.getPersonService().get(this.personId);
		}
		return this.person;
	}

	@Override
	public void setPerson(IPerson user) {
		this.person = user;
		this.personId = user.getGuid();
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserState getState() {
		return this.userState;
	}

	public void setState(UserState state) {
		this.userState = state;
	}

	@Override
	public IOrganization getOrganization() {
		return getPerson().getOrganization();
	}

	public int getLoginFailCount() {
		return this.loginFailCount;
	}

	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    //此处注意：
	public String getPlainText() {
		if (this.password != null) {
			this.plainText = this.password;
		}
		return this.plainText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
		this.password = MsgDigestAlgorithm.getMD5Str(plainText);
	}

	public Date getFreezeTime() {
		return this.freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
	}

	public int getAccessAccount() {
		return this.accessAccount;
	}

	public void setAccessAccount(int accessAccount) {
		this.accessAccount = accessAccount;
	}

	public IDomainUserService getService() {
		return this.service;
	}

	public void setService(IDomainUserService service) {
		this.service = service;
	}

	public String getUserId() {
		return this.personId;
	}

	public void setUserId(String userId) {
		this.personId = userId;
	}

	@Override
	public void moveTo(IOrganization organization) {
		service.move(this, organization);
	}

	@Override
	public String getPhone() {
		return getPerson().getPhone();
	}

    public boolean isSystem()
    {
        return system;
    }

    public void setSystem(boolean system)
    {
        this.system = system;
    }

	@Override
	public void setRoles(List<IRole> roles) {
		this.roles = roles;
	}

	@Override
	public List<IRole> getRoles() {
		return this.roles;
	}

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}