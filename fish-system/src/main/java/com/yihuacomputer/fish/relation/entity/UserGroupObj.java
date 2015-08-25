package com.yihuacomputer.fish.relation.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 主帐号与用户组间的关系实体
 *
 * @author SuYang
 *
 * @date 2010-8-25
 *
 */
//@Entity
//@Table(name="SM_USER_GROUP")
public class UserGroupObj implements Serializable{
	
	/**serialVersionUID**/
	private static final long serialVersionUID = -5617345814883228243L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO,generator="SEQ_SM_USER_GROUP")
	@SequenceGenerator(name="SEQ_SM_USER_GROUP", sequenceName="SEQ_SM_USER_GROUP") 
	@Column(name="USER_GROUP_ID")
	private Long id;
	
	@Column(name = "USER_ID")
	private Long masterId;
	
	@Column(name = "GROUP_ID")
	private Long groupId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * 根据指定的主帐号和用户组实体创建关系实体
	 * @param master
	 * @param group
	 * @return
	 */
	public static UserGroupObj make(Long masterId,Long groupId){
		UserGroupObj obj = new UserGroupObj();
		obj.masterId = masterId;
		obj.groupId = groupId;
		return obj;
	}
}
