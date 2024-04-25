package org.zerock.jdbcex.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum ConnectionUtil {
    INSTANCE;
    private HikariDataSource ds;    //array 대신에 사용할 예정

    ConnectionUtil() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3309/webdb");
        config.setUsername("webUser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts", "true");     //preparedstatement를 사용하겠다.
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");


        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
