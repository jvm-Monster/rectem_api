package com.qoonnect.rectem_api.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

public class HikarIcpDataSourceUtil {
    @Getter
    private static final HikariConfig config = new HikariConfig();
    @Getter
    private static final HikariDataSource dataSource;

    static{
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/rectem_db");
        config.setUsername("root");
        config.setPassword("Password123.");
        dataSource = new HikariDataSource(config);
    }

    private HikarIcpDataSourceUtil() {}

}


/*127.0.0.1*/