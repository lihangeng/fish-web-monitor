package com.yihuacomputer.fish.api.parameter;

import java.util.Date;

public interface IElement {
    public long getId();

    public void setId(long id);

    public String getParamName();

    public void setParamName(String name);

    public String getParamValue();

    public void setParamValue(String value);

    public String getParamType();

    public void setParamType(String type);

    public IClassify getParamClassify();

    public void setParamClassify(IClassify classify);

    public String getVersionNo();

    public void setVersionNo(String no);

    public String getParamRights();

    public void setParamRights(String rights);

    public String getParamBelongs();

    public void setParamBelongs(String belongs);

    public Date getCreateTime();

    public void setCreateTime(Date createTime);

    public Date getLastModifyTime();

    public void setLastModifyTime(Date lastModifyTime);

    public String getRemark();

    public void setRemark(String remark);



}
