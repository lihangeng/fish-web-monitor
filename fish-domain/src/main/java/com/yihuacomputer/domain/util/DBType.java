package com.yihuacomputer.domain.util;

import java.util.Properties;

import org.hibernate.dialect.DB2Dialect;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.dialect.SybaseASE15Dialect;

/**
 * @author YiHua
 *
 */
public class DBType {

    private Properties properties ;
    private Dialect dialect = null;
   
    /**
     * @param properties
     */
    public DBType(Properties properties){
        this.properties = properties;
    }
    
    public Properties getProperties() {
        return properties;
    }

    private Dialect getDialect(){
       if(this.dialect == null) {
           dialect = Dialect.getDialect(this.properties);
       }
       return this.dialect;
    }

    public boolean isH2(){
        return (getDialect() instanceof H2Dialect); 
    }
    
    public boolean isMysql(){
        return (getDialect() instanceof MySQL5Dialect); 
    }
    
    public boolean isOracle(){
        return (getDialect() instanceof Oracle10gDialect); 
    }
    
    public boolean isDB2(){
        return (getDialect() instanceof DB2Dialect); 
    }
    
    public boolean isSybase(){
        return (getDialect() instanceof SybaseASE15Dialect);
    }
    
    public boolean isSqlServer(){
        return (getDialect() instanceof SQLServerDialect);
    }
    
    public boolean isMemDB(){
        return isH2();
    }
    
    
}
