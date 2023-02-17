package com.docapp.Utils;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;


import java.io.IOException;

import java.util.Properties;
import java.util.TimeZone;

public class ConnPom {
    static java.util.logging.Logger logger = Logger.getLogger(ConnPom.class.getName());
    private static DataSource dataSource;

    public ConnPom() {
    }
    public static DataSource getDatasource(){
        if (dataSource == null) {
            FileResourcesUtils res = new FileResourcesUtils();
            Properties props = new Properties();
            try {
                props.load(res.getFileFromResourceAsStream("application.properties"));
            } catch (IOException e) {
                logger.warning(e.getMessage());
            }

            PoolProperties p = new PoolProperties();
            p.setUrl(props.getProperty("spring.datasource.url") + TimeZone.getDefault().getID());
            logger.info(p.getUrl());
            p.setDriverClassName(props.getProperty("jdbc.driver"));
            p.setUsername(props.getProperty("spring.datasource.username"));
            p.setPassword(props.getProperty("spring.datasource.password"));
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setTimeBetweenEvictionRunsMillis(-1);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            p.setLogAbandoned(true);
            dataSource = new DataSource();
            dataSource.setPoolProperties(p);
        }
        return dataSource;
    }
    public static Connection getConnection() throws SQLException {
        getDatasource();
        return dataSource.getConnection();
    }



}
