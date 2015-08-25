/**
 * 
 */
package com.yihuacomputer.fish.version.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.version.IVersionTypeRestriction;
import com.yihuacomputer.fish.api.version.RestrictionColumn;

/**
 * @author xuxigang
 * 
 */
@Entity
@Table(name ="VER_TYPE_RESTRICTION")
public class VersionTypeRestriction implements IVersionTypeRestriction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_TYPE_RESTRICTION")
    @SequenceGenerator(name = "SEQ_VER_TYPE_RESTRICTION", sequenceName = "SEQ_VER_TYPE_RESTRICTION")
    @Column(name = "ID")
    private long id;
    
    
    @Enumerated(EnumType.STRING)
    @Column(name = "RESTRICTION_COLUMN", length = 30,nullable = false)
    private RestrictionColumn restrictionColumn;

    @Column(name = "RESTRICTION_NAME", nullable = false, length = 30)
    private String restrictionValue;
    
   @Override
    public long getId() {
        return this.id;
    }
    
    public void setId(long id){
        this.id = id;
    }

    @Override
    public void setRestrictionColumn(RestrictionColumn restrictionColumn) {
        this.restrictionColumn = restrictionColumn;
    }

    @Override
    public RestrictionColumn getRestrictionColumn() {
        return this.restrictionColumn;
    }

    @Override
    public void setRestrictionValue(String restrictionValue) {
        this.restrictionValue = restrictionValue;
    }

    @Override
    public String getRestrictionValue() {
        return this.restrictionValue;
    }

    
}
