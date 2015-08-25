package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropIcc;

@Embeddable
public class PropIcc implements IPropIcc {

    @Enumerated(EnumType.STRING)
    @Column(name = "ICC_EXIST", columnDefinition = "CHAR", length = 1)
    private DeviceProp icc;

    public PropIcc() {
        this.icc = DeviceProp.F;
    }

    public DeviceProp getPropIcc() {
        return this.icc;
    }

    public void setPropIcc(DeviceProp icc) {
        this.icc = icc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((icc == null) ? 0 : icc.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PropIcc other = (PropIcc) obj;
        if (icc != other.icc) {
            return false;
        }
        return true;
    }
}
