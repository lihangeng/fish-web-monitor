package com.yihuacomputer.domain.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;

/**
 * @author YiHua
 *
 */
public class BooleanUserType implements UserType {

	@Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

	@Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

	@Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

	@Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if (x == null || y == null) {
            return false;
        }
        return x.equals(y);
    }

	@Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

	@Override
    public boolean isMutable() {
        return false;
    }

	@Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

	@Override
    @SuppressWarnings("rawtypes")
    public Class returnedClass() {
        return boolean.class;
    }

	@Override
    public int[] sqlTypes() {
        return new int[]{StandardBasicTypes.STRING.sqlType()};
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor arg2, Object arg3) throws HibernateException, SQLException {
        String value = resultSet.getString(names[0]);
        if (resultSet.wasNull()) {
            return null;
        }
        return "1".equals(value) ? true : false;
    }

    @Override
    public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor arg3) throws HibernateException, SQLException {
        if (value == null) {
            statement.setNull(index, StandardBasicTypes.STRING.sqlType());
        }
        else {
            Boolean b = (Boolean)value;
            statement.setString(index, b ? "1" : "0" );
        }
    }

}
