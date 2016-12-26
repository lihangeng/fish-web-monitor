package com.yihuacomputer.fish.api.person;


/**
 * @author YiHua
 *
 */
public enum UserState {
	/**
	 * 待审核
	 */
	WAITCHECK(0,"UserState.WAITCHECK"),
	/**
	 * 新建
	 */
	NEW(1,"UserState.NEW"),
	/**
	 * 正常
	 */
	NORMAL(2, "UserState.NORMAL"),
	/**
	 * 锁定
	 */
	LOCK(3, "UserState.LOCK"),//超过60天没有修改密码，会被锁定
	/**
	 * 已删除
	 */
	REMOVED(4, "UserState.REMOVED"),//假删除
	/**
	 * 冻结
	 */
	@Deprecated
	FREEZE(4, "UserState.FREEZE"),//用user.getFreezeTime()是否为null来判断用户是否被冻结
	/**
	 * 审核未通过
	 */
	NOTPASS(6,"UserState.NOTPASS");

	private int id;
	private String text;

	private UserState(int id ,String text){
		this.id = id;
		this.text= text;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	/**
	 * @param id
	 * @return
	 */
	public static UserState getById(int id){
		for(UserState each : UserState.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}

}
