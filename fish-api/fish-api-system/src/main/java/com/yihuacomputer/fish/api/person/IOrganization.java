package com.yihuacomputer.fish.api.person;

/**
 * 机构信息：
 *
 * @author huxiaobao
 *
 */
public interface IOrganization extends Comparable<IOrganization> {

	public long getId();
    /**
     * 主键
     *
     * @return
     */
    public String getGuid();

    public void setGuid(String guid);

    /**
     * 机构编号,不区分大小写
     *
     * @return
     */
    public String getCode();

    public void setCode(String code);

    /**
     * 返回机构名称
     *
     * @return
     */
    public String getName();

    /**
     * 设置机构名称
     */
    public void setName(String name);

    /**
     * 返回机构所在地
     */
    public String getAddress();

    /**
     * 设置机构所在地
     *
     * @param address
     */
    public void setAddress(String address);

    /**
     * 返回邮政编号
     *
     * @param zip
     */
    public String getZip();

    /**
     * 返回邮政编号
     */
    public void setZip(String zip);

    /**
     * 返回机构备注
     *
     * @return
     */
    public String getDescription();

    /**
     * 设置机构备注
     */
    public void setDescription(String description);

    /**
     * 设置机构管理员
     *
     * @param manager
     */
    public void setManager(IPerson person);

    /**
     * 获取机构管理员 找不到返回null
     *
     * @return IPerson 人员
     */
    public IPerson getManager();

    /**
     * 列出该机构下的所有人员 没有返回一个空的集合
     *
     * @return
     */
    public Iterable<IPerson> listAllPerson();

    /**
     * 返回父机构（上级机构） 找不到返回null
     *
     * @return
     */
    public IOrganization getParent();

    /**
     * 设置父机构（上级机构）
     *
     * @param organization
     */
    public void setParent(IOrganization organization);

    // /**
    // * 返回机构级别
    // * @return
    // */
    // public OrganizationLevel getOrganizationLevel();
    //
    // /**
    // * 设置机构级别
    // * @param level
    // */
    // public void setOrganizationLevel(OrganizationLevel level);
    //
    /**
     * 返回机构类型
     *
     * @return
     */
    public OrganizationType getOrganizationType();

    /**
     * 返回机构类型
     *
     * @param type
     */
    public void setOrganizationType(OrganizationType type);

    /**
     * 返回机构状态
     *
     * @return
     */
    public OrganizationState getOrganizationState();

    /**
     * 设置机构状态
     */
    public void setOrganizationState(OrganizationState state);

    /**
     * 根据机构编号找到其子机构 找不到返回null
     *
     * @param code
     * @return
     */
    public IOrganization getChild(String code);

    /**
     * 列出所有的子机构
     *
     * @return
     */
    public Iterable<IOrganization> listChildren();

    /**
     * 根据类型列出其子机构：
     *
     * @return
     */
    public Iterable<IOrganization> listChildren(
            OrganizationType organizationType);

    /**
     * 根据编号和名字增加一个子机构
     *
     * @param code
     * @param name
     * @return
     */
    public IOrganization addChild(String code, String name);

    public String getOrgFlag();

    public void setOrgFlag(String orgFlag);

    public IOrganization getServiceObject();

    public void setServiceObject(IOrganization serviceObject);

    /**
     * 机构级别
     * @param orgLevel
     */
    public void setOrganizationLevel(OrganizationLevel orgLevel);
    public OrganizationLevel getOrganizationLevel();

    /**
     * 返回银联地区标识码
     *
     * @return
     */
    public String getAreaFlag();

    /**
     * 设置银联地区标识码
     */
    public void setAreaFlag(String areaFlag);
    /**
     * 设置是否需要短信群发的菜单
     */
    public String getIsNoteSend();
    /**
     * 返回是否需要短信群发的菜单
     */
    public void setIsNoteSend(String isNoteSend);
    
    /**
     * 返回审批建议
     *
     * @param suggest
     */
    public String getSuggest();

    /**
     * 返回审批建议
     */
    public void setSuggest(String suggest);
    
    /**
     * 返回法人编号
     *
     * @param LegalPerCode
     */
    public String getLegalPerCode();

    /**
     * 返回法人编号
     */
    public void setLegalPerCode(String legalPerCode);

    
    /**
     * 返回机构申请提交人
     *
     * @param applicationPer
     */
    public String getApplicationPer();

    /**
     * 返回机构申请提交人
     */
    public void setApplicationPer(String applicationPer);
    /**
	 * 是否为叶子机构（默认为叶子机构）;update之前判断上一级机构更改后是否为叶子机构；update之后判断上一级机构是否为叶子机构
	 */
	public boolean isLeaf();
	/**
	 * 是否为叶子机构（默认为叶子机构）;update之前判断上一级机构更改后是否为叶子机构；update之后判断上一级机构是否为叶子机构
	 */
	public void setLeaf(boolean leaf);
}
