package com.student.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceManager {
	private static HikariConfig configmysql = new HikariConfig();
	
//	@Autowired
//	PropertiesConfigurationBean propertiesConfigurationBean;

	public enum ServerAddress {
		MYSQL_LOCAL,MYSQL_LOCAL2, MYSQL_TEST, MYSQL_PROD
	}

	// Code to establish HikariDataSource for jdbc session from properties in application.properties
//	@Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public HikariConfig hikariConfigmysql_session() {
//        return new HikariConfig();
//    }
//
//	@Autowired
//    private Environment env;
//    @Bean(name = "mysqlDataSource_session")
//    @Primary
//    public HikariDataSource dataSource_session() {
//    	 //System.out.println(propertiesConfigurationBean.getPort()+"...............propertiesConfigurationBean..............."+env.getProperty("spring.datasource.pool-name"));
//        return new HikariDataSource(hikariConfigmysql_session());
//       
//    }
    
    
		// Code to establish HikariDataSource and JdbcTemplate from properties in application.properties
	 	@Bean
	 	//@Primary
	    @ConfigurationProperties(prefix = "datasource.student")
	    public HikariConfig hikariConfigmysql4() {
	        return new HikariConfig();
	    }

	    @Bean(name = "mysqlDataSource4")
	   // @Primary
	    public HikariDataSource dataSource4() {
	        return new HikariDataSource(hikariConfigmysql4());
	    }
	    @Bean(name = "mysqlTemplate4")
	    //@Primary
		public NamedParameterJdbcTemplate mysqlJdbcTemplate4(@Qualifier("mysqlDataSource4") HikariDataSource dataSource) {
			return new NamedParameterJdbcTemplate(dataSource);
		}
	    
	    @Bean(name = "mysqlcall4")
	    //@Primary
		public SimpleJdbcCall mysqlcall4(@Qualifier("mysqlDataSource4") HikariDataSource dataSource) {
			return  new SimpleJdbcCall(dataSource);
		}
	   
	    
	    
	    
	    
	    @Bean
	    @ConfigurationProperties(prefix = "datasource.marks")
	    public HikariConfig hikariConfigmysql5() {
	        return new HikariConfig();
	    }

	    @Bean(name = "mysqlDataSource5")
	    public HikariDataSource dataSource5() {
	        return new HikariDataSource(hikariConfigmysql5());
	    }
	    @Bean(name = "mysqlTemplate5")
		public NamedParameterJdbcTemplate mysqlJdbcTemplate5(@Qualifier("mysqlDataSource5") HikariDataSource dataSource) {
			return new NamedParameterJdbcTemplate(dataSource);
		}
	    
//	    @Bean
//	    @ConfigurationProperties(prefix = "datasource.test")
//	    public HikariConfig hikariConfigmysql6() {
//	        return new HikariConfig();
//	    }
//
//	    @Bean(name = "mysqlDataSource6")
//	    public HikariDataSource dataSource6() {
//	        return new HikariDataSource(hikariConfigmysql6());
//	    }
//	    @Bean(name = "mysqlTemplate6")
//		public NamedParameterJdbcTemplate mysqlJdbcTemplate6(@Qualifier("mysqlDataSource6") HikariDataSource dataSource) {
//			return new NamedParameterJdbcTemplate(dataSource);
//		}
	    
	    
	    
	 // Code to establish HikariDataSource and JdbcTemplate from properties with in the code-serverConnection()
//	 @Bean(name = "mysqlDataSource")
//	 @Primary
//	    public HikariDataSource mysqlDataSource() 
//	    {
//		 serverConnection(ServerAddress.MYSQL_LOCAL);
//		HikariDataSource hikariDataSource = new HikariDataSource( configmysql );
//		//hikariDataSource.close();
//		return hikariDataSource;
//	    }
//	 
//	 @Bean(name = "mysqlTemplate")
//		public NamedParameterJdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") HikariDataSource dataSource) {
//			return new NamedParameterJdbcTemplate(dataSource);
//		}
//	 
//	 @Bean(name = "mysqlDataSource2")
//	    public HikariDataSource mysqlDataSource2() 
//	    {
//		 serverConnection(ServerAddress.MYSQL_LOCAL2);
//		HikariDataSource hikariDataSource = new HikariDataSource( configmysql );
//		return hikariDataSource;
//	    }
//	 
//	 @Bean(name = "mysqlTemplate2")
//		public NamedParameterJdbcTemplate mysqlJdbcTemplate2(@Qualifier("mysqlDataSource2") HikariDataSource dataSource) {
//			return new NamedParameterJdbcTemplate(dataSource);
//		}
	 
	 

	    
	    
	    
//	 //code to establish DataSource from the Tomcat pooling production
//	 @Bean(name = "mysqlDataSource3")
//	    public DataSource DataSource() 
//	    {
//		 Context initContext;
//			DataSource ds=null;
//			try {
//				initContext = new InitialContext();
//				Context envContext = (Context) initContext.lookup("java:comp/env");
//				 ds = (DataSource) envContext.lookup("jdbc/student");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return ds;
//	    }
//	 
//	//NamedParameterJdbcTemplate from the tomcat pooling
//	 @Bean(name = "mysqlTemplate3")
//		public NamedParameterJdbcTemplate mysqlJdbcTemplate3(@Qualifier("mysqlDataSource3") DataSource dataSource) {
//			return new NamedParameterJdbcTemplate(dataSource);
//		}
//	 
//	 

	public  void serverConnection(ServerAddress connection) {
				
		switch (connection) {
			case MYSQL_LOCAL:
				configmysql.setDriverClassName("com.mysql.cj.jdbc.Driver");
				configmysql.setJdbcUrl( "jdbc:mysql://127.0.0.1:3306/student" );
		        configmysql.setUsername( "root" );
		        configmysql.setPassword( "Nagu@9046N" );
		        configmysql.setMaximumPoolSize(5);
		        configmysql.setAutoCommit(true);
		        configmysql.setConnectionTestQuery("SELECT 1");
		        configmysql.setIdleTimeout(10000);
		        configmysql.setPoolName("pool-1");
		        configmysql.addDataSourceProperty( "cachePrepStmts" , "true" );
		        configmysql.addDataSourceProperty( "prepStmtCacheSize" , "250" );
		        configmysql.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
				break;

			case MYSQL_LOCAL2:
				configmysql.setDriverClassName("com.mysql.cj.jdbc.Driver");
				configmysql.setJdbcUrl( "jdbc:mysql://127.0.0.1:3306/student" );
		        configmysql.setUsername( "root" );
		        configmysql.setPassword( "Nagu@9046N" );
		        configmysql.setMaximumPoolSize(5);
		        configmysql.setAutoCommit(true);
		        configmysql.setConnectionTestQuery("SELECT 1");
		        configmysql.setIdleTimeout(10000);
		        configmysql.setPoolName("pool-2");
		        configmysql.addDataSourceProperty( "cachePrepStmts" , "true" );
		        configmysql.addDataSourceProperty( "prepStmtCacheSize" , "250" );
		        configmysql.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
				break;

				
			case MYSQL_TEST:
				configmysql.setJdbcUrl( "jdbc:mysql://127.0.0.1:3306/student" );
		        configmysql.setUsername( "root" );
		        configmysql.setPassword( "Nagu@9046N" );
		        configmysql.setMaximumPoolSize(5);
		        configmysql.setAutoCommit(true);
		        configmysql.setConnectionTestQuery("SELECT 1");
		        configmysql.addDataSourceProperty( "cachePrepStmts" , "true" );
		        configmysql.addDataSourceProperty( "prepStmtCacheSize" , "250" );
		        configmysql.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
				break;

			case MYSQL_PROD:
				configmysql.setJdbcUrl( "jdbc:mysql://127.0.0.1:3306/student" );
		        configmysql.setUsername( "root" );
		        configmysql.setPassword( "Nagu@9046N" );
		        configmysql.setMaximumPoolSize(5);
		        configmysql.setAutoCommit(true);
		        configmysql.setConnectionTestQuery("SELECT 1");
		        configmysql.addDataSourceProperty( "cachePrepStmts" , "true" );
		        configmysql.addDataSourceProperty( "prepStmtCacheSize" , "250" );
		        configmysql.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
				break;
		}
	}

}
