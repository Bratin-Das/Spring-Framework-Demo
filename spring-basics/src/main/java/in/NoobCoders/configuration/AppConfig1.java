package in.NoobCoders.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import in.NoobCoders.dao.DummyProductDao;
import in.NoobCoders.dao.JDBCProductDao;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class AppConfig1 
{
	@Value("${jdbc.driver}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;
	
	
	public AppConfig1() {
		System.out.println("AppConfig1 is instantiated ");
	}
	@Lazy
	@Bean
	public DummyProductDao dummydao()
	{
		System.out.println("AppConfig1.dummyDao() called");
		return new DummyProductDao();
	}
	//@Lazy
	@Scope("singleton")
	@Bean
	public JDBCProductDao jdbcDao() 
	{
		System.out.println("AppConfig1.jdbcDao() called");

		var dao = new JDBCProductDao();
		dao.setDriverClassName(driverClassName);
		dao.setUrl(url);
		dao.setUser(user);
		dao.setPassword(password);
		
		return dao;
	}
}

