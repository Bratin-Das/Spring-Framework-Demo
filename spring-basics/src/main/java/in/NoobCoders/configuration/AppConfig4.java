package in.NoobCoders.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import in.NoobCoders.entity.Category;
import in.NoobCoders.entity.Product;
import in.NoobCoders.entity.Supplier;

@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"in.NoobCoders.dao","in.NoobCoders.aspects"})
@Configuration
@PropertySource("classpath:jdbc.properties")
public class AppConfig4 
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
	public HibernateTransactionManager txMgr(SessionFactory  sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
	
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
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource datasource)
	{
		return new JdbcTemplate(datasource);
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(dataSource);		
		lsfb.setAnnotatedClasses(Category.class, Product.class, Supplier.class);
		
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//		props.setProperty("hibernate.show_sql", "true");
//		props.setProperty("hibernate.format_sql", "true");
		
		lsfb.setHibernateProperties(props);
		
		return lsfb;
	}
	

	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory)
	{
		
		return new HibernateTemplate(sessionFactory);
	}
	
	
	
	
	
	
	

}

