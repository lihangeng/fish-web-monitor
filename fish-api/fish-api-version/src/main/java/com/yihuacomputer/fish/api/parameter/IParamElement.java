package com.yihuacomputer.fish.api.parameter;

import java.util.Date;


/**
 * @author YiHua
 *
 */
public interface IParamElement {
    /**
     * @return
     */
    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * @return
     */
    public String getParamName();

    /**
     * @param name
     */
    public void setParamName(String name);

    /**
     * @return
     */
    public String getParamValue();

    /**
     * @param value
     */
    public void setParamValue(String value);

    /**
     * @return
     */
    public String getParamType();

    /**
     * @param type
     */
    public void setParamType(String type);

    /**
     * @return
     */
    public IParamClassify getParamClassify();

    /**
     * @param classify
     */
    public void setParamClassify(IParamClassify classify);

    /**
     * @return
     */
    public String getParamRights();

    /**
     * @param rights
     */
    public void setParamRights(String rights);

    /**
     * @return
     */
    public IAppSystem getParamBelongs();

    /**
     * @param belongs
     */
    public void setParamBelongs(IAppSystem belongs);

    /**
     * @return
     */
    public Date getCreateTime();

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime);

    /**
     * @return
     */
    public Date getLastModifyTime();

    /**
     * @param lastModifyTime
     */
    public void setLastModifyTime(Date lastModifyTime);

    /**
     * @return
     */
    public String getRemark();

    /**
     * @param remark
     */
    public void setRemark(String remark);

    /**
     * @return
     */
    public long getParamTimestamp();

    /**
     * @param paramTimestamp
     */
    public void setParamTimestamp(long paramTimestamp);



}
