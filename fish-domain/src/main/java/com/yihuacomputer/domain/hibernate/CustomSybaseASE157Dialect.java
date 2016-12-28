package com.yihuacomputer.domain.hibernate;

import java.sql.Types;

import org.hibernate.dialect.SybaseASE157Dialect;
import org.hibernate.type.descriptor.sql.BooleanTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;


/**
 * @author YiHua
 *
 */
@Deprecated
public class CustomSybaseASE157Dialect extends SybaseASE157Dialect{

    /**
     * 初始化
     */
    public CustomSybaseASE157Dialect(){
        super();

        registerColumnType( Types.BOOLEAN, "boolean" );
    }

    @Override
    protected SqlTypeDescriptor getSqlTypeDescriptorOverride(int sqlCode) {
        return sqlCode == Types.BOOLEAN ? BooleanTypeDescriptor.INSTANCE : super.getSqlTypeDescriptorOverride( sqlCode );
    }
}
