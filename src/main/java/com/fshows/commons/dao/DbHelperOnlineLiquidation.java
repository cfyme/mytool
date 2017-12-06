package com.fshows.commons.dao;
 
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
 
public class DbHelperOnlineLiquidation {
    private static DataSource dataSource;
    private DbHelperOnlineLiquidation(){
    }

//    spring.datasource.url=jdbc:mysql://drds93d3kt40v3z1.drds.aliyuncs.com:3306/fs_liquidation_platform?zeroDateTimeBehavior=convertToNull
//    spring.datasource.username=fs_liquidation_platform
//    spring.datasource.password=GU6u_Pk3Cszj_IiTNbghld

    public static QueryRunner getQueryRunner(){
        if(DbHelperOnlineLiquidation.dataSource==null){
            //配置dbcp数据源
            BasicDataSource dbcpDataSource = new BasicDataSource();
            dbcpDataSource.setUrl("jdbc:mysql://drds93d3kt40v3z1.drds.aliyuncs.com:3306/fs_liquidation_platform?zeroDateTimeBehavior=convertToNull");
            dbcpDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dbcpDataSource.setUsername("fs_liquidation_platform");
            dbcpDataSource.setPassword("GU6u_Pk3Cszj_IiTNbghld");
            dbcpDataSource.setDefaultAutoCommit(true);
            dbcpDataSource.setMaxActive(1);
            dbcpDataSource.setInitialSize(1);
            dbcpDataSource.setMaxIdle(1);
            dbcpDataSource.setMaxWait(5000);
            DbHelperOnlineLiquidation.dataSource = (DataSource)dbcpDataSource;
            System.out.println("Initialize 99 dbcp...");
        }
        return new QueryRunner(DbHelperOnlineLiquidation.dataSource);
    }
}