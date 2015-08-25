/**
 * 
 */
package com.yihuacomputer.fish.api.version;

/**
 * @author xuxigang
 * 版本分类的限制条件
 */
public interface IVersionTypeRestriction {
    public long getId();
    
    /**
     * 设置限制列，对应设备表(dev_info)中的字段
     * @param restrictionColumnName
     */
    public void setRestrictionColumn(RestrictionColumn restrictionColumn);
    public RestrictionColumn getRestrictionColumn();
    
    /**
     * 设置限制列对应的值
     * @param restrictionValue
     */
    public void setRestrictionValue(String restrictionValue);
    public String getRestrictionValue();
}
