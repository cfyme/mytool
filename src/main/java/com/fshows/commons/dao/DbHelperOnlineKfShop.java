package com.fshows.commons.dao;
 
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class DbHelperOnlineKfShop {
    private static DataSource dataSource;
    private DbHelperOnlineKfShop(){
    }


//    spring.datasource.url=jdbc:mysql://drds169d4025i2uv.drds.aliyuncs.com:3306/kf_shop_platform_v2?zeroDateTimeBehavior=convertToNull
//    spring.datasource.username=kf_shop_platform_v2
//    spring.datasource.password=CfN8tCdsxP9nooJyt8yY
    public static QueryRunner getQueryRunner(){
        if(DbHelperOnlineKfShop.dataSource==null){
            //配置dbcp数据源
            BasicDataSource dbcpDataSource = new BasicDataSource();
            dbcpDataSource.setUrl("jdbc:mysql://drds169d4025i2uv.drds.aliyuncs.com:3306/kf_shop_platform_v2?zeroDateTimeBehavior=convertToNull");
            dbcpDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dbcpDataSource.setUsername("kf_shop_platform_v2");
            dbcpDataSource.setPassword("CfN8tCdsxP9nooJyt8yY");
            dbcpDataSource.setDefaultAutoCommit(true);
            dbcpDataSource.setMaxActive(1);
            dbcpDataSource.setInitialSize(1);
            dbcpDataSource.setMaxIdle(1);
            dbcpDataSource.setMaxWait(5000);
            DbHelperOnlineKfShop.dataSource = (DataSource)dbcpDataSource;
            System.out.println("Initialize 99 dbcp...");
        }
        return new QueryRunner(DbHelperOnlineKfShop.dataSource);
    }
}