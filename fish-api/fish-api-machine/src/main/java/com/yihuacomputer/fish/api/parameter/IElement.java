package com.yihuacomputer.fish.api.parameter;

import java.util.Date;

public interface IElement {
    public long getId();

    public void setId(long id);

    public String getName();

    public void setName(String name);

    public String getValue();

    public void setValue(String value);

    public String getType();

    public void setType(String type);

    public String getClassify();

    public void setClassify(String classify);

    public String getVersionNo();

    public void setVersionNo(String no);

    public String getRights();

    public void setRights(String rights);

    public String getBelongs();

    public void setBelongs(String belongs);

    public Date getCreateTime();

    public void setCreateTime(Date createTime);

    public Date getLastModifyTime();

    public void setLastModifyTime(Date lastModifyTime);

    public String getRemark();

    public void setRemark(String remark);



}
