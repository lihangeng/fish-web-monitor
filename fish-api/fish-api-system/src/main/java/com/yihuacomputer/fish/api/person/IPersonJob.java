package com.yihuacomputer.fish.api.person;

/**
 * 工作岗位
 *
 * @author pengwenchao
 *
 */
public interface IPersonJob {
    /**
     * ID
     *
     * @return
     */
    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * 岗位编码
     *
     * @return
     */
    public String getCode();

    /**
     * @param code
     */
    public void setCode(String code);

    /**
     * 岗位名称
     *
     * @return
     */
    public String getName();

    /**
     * @param name
     */
    public void setName(String name);

    /**
     * 备注
     *
     * @return
     */
    public String getRemark();

    /**
     * @param remark
     */
    public void setRemark(String remark);
}
