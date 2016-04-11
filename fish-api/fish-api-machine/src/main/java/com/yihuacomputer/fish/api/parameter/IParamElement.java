package com.yihuacomputer.fish.api.parameter;

import java.util.Date;


public interface IParamElement {
    public long getId();

    public void setId(long id);

    public String getParamName();

    public void setParamName(String name);

    public String getParamValue();

    public void setParamValue(String value);

    public String getParamType();

    public void setParamType(String type);

    public IParamClassify getParamClassify();

    public void setParamClassify(IParamClassify classify);

    public String getParamRights();

    public void setParamRights(String rights);

    public IAppSystem getParamBelongs();

    public void setParamBelongs(IAppSystem belongs);

    public Date getCreateTime();

    public void setCreateTime(Date createTime);

    public Date getLastModifyTime();

    public void setLastModifyTime(Date lastModifyTime);

    public String getRemark();

    public void setRemark(String remark);

    public long getParamTimestamp();

    public void setParamTimestamp(long paramTimestamp);



}
