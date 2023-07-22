package in.NoobCoders.configuration;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import in.NoobCoders.dao.JDBCProductDao;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class AppConfig2 
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
	public Connection connection()throws Exception {
		Class.forName(driverClassName);
		return DriverManager.getConnection(url,user,password);
	}
	
	@Bean
	public JDBCProductDao jdbcDao(Connection connection) 
	{
//		Manual Wiring
//		var dao = new JDBCProductDao();
//		dao.setConnection(connection);
		
		return new JDBCProductDao();
	}
}

