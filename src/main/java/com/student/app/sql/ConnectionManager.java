package com.student.app.sql;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.zaxxer.hikari.HikariDataSource;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConnectionManager {

	@Autowired
	@Qualifier("mysqlDataSource") 
	HikariDataSource hikariDataSourcemysql;
	
	public  Connection getConnection() throws SQLException {
        return hikariDataSourcemysql.getConnection();
    }
	
}
