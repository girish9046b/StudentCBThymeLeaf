package com.student.app.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
	@EnableJpaRepositories(basePackages = {"com.student.app.repository"},
	        entityManagerFactoryRef = "studentEntityManager",
	        transactionManagerRef = "studentTransactionManager")
	public class StudentDataSource {
	    @Autowired
	    private Environment env;
	    @Bean
	   // @Primary
	    public LocalContainerEntityManagerFactoryBean studentEntityManager() {
	        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	        em.setDataSource(studentDatasource());
	        em.setPackagesToScan(new String[]{"com.student.app.model"});
	        em.setPersistenceUnitName("studentEntityManager");
	        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	        em.setJpaVendorAdapter(vendorAdapter);
	        HashMap<String, Object> properties = new HashMap<>();
	        properties.put("hibernate.dialect",env.getProperty("hdatasource.sql-dialect"));
	        properties.put("hibernate.show-sql",env.getProperty("hdatasource.jdbc.show-sql"));
	        em.setJpaPropertyMap(properties);
	        return em;
	    }

	    
	    @Bean
	   // @ConfigurationProperties(prefix = "hdatasource.marks")
	    @ConfigurationProperties(prefix = "hdatasource.student")
	    public HikariConfig hikariConfigmysql_student() {
	        return new HikariConfig();
	    }

	    @Bean(name = "mysqlDataSource_student")
	    public HikariDataSource studentDatasource() {
	        return new HikariDataSource(hikariConfigmysql_student());
	    }
//	    @Primary
//	    @Bean
//	    public DataSource studentDatasource() {
//
//	        DriverManagerDataSource dataSource
//	                = new DriverManagerDataSource();
//	        dataSource.setDriverClassName(env.getProperty("datasource.student.driver-class-name"));
//	        dataSource.setUrl(env.getProperty("db1.datasource.url"));
//	        dataSource.setUsername(env.getProperty("db1.datasource.username"));
//	        dataSource.setPassword(env.getProperty("db1.datasource.password"));
//
//	        return dataSource;
//	    }

	    //@Primary
	    @Bean
	    public PlatformTransactionManager studentTransactionManager() {

	        JpaTransactionManager transactionManager
	                = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(
	        		studentEntityManager().getObject());
	        return transactionManager;
	    }
	    
	}
