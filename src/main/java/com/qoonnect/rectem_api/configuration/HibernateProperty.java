package com.qoonnect.rectem_api.configuration;


import lombok.Getter;

import java.util.Properties;

public class HibernateProperty {
    public static final String HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String HIBERNATE_CONNECTION_DATASOURCE = "hibernate.connection.datasource";
    public static final String HIBERNATE_TRANSACTION_JTA_PLATFORM = "hibernate.transaction.jta.platform";
    @Getter
    private static final Properties properties;

    static{
        properties = new Properties();
//        properties.setProperty(HIBERNATE_DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.setProperty(HIBERNATE_SHOW_SQL, "true");
        properties.setProperty(HIBERNATE_FORMAT_SQL, "true");
        properties.setProperty(HIBERNATE_HBM2DDL_AUTO,"update");//create-drop create update
        properties.setProperty(HIBERNATE_TRANSACTION_JTA_PLATFORM, "org.hibernate.transaction.jta.platform");
    }

    private HibernateProperty() {}

}
