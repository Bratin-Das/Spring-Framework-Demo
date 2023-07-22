package in.NoobCoders.configuration;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import in.NoobCoders.dao.JDBCProductDao;


@ComponentScan(basePackages = {"in.NoobCoders.dao"})
@Configuration
@PropertySource("classpath:jdbc.properties")
public class AppConfig3 
{
	@Value("${jdbc.driver}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;
	
	@Bean
	 public DataSource datasource()
	 {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(driverClassName);
		bds.setUrl(url);
		bds.setUsername(user);
		bds.setPassword(password);
		
		bds.setInitialSize(10);
		bds.setMaxTotal(100);
		bds.setMaxWaitMillis(500);
		bds.setMaxIdle(50);
		bds.setMinIdle(2);
		
		return bds;
	 }
	
//	@Bean
//	public JDBCProductDao jdbcDao() 
//	{
////		Manual Wiring
////		var dao = new JDBCProductDao();
////		dao.setConnection(connection);
//		
//		return new JDBCProductDao();
//	}
}

