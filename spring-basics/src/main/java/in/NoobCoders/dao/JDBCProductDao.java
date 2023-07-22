package in.NoobCoders.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Repository("jdbcDao")
@Setter
@Getter
@NoArgsConstructor
public class JDBCProductDao implements ProductDao
{
	private String driverClassName;
	private String url;
	private String user;
	private String password;
	
	@Autowired(required = false)
	private Connection connection;
	
	@Autowired(required = false)
	private DataSource datasource;
	
	
	private Connection createConnection() throws ClassNotFoundException, SQLException 
	{
		if(datasource != null)
		{
			return datasource.getConnection();
		}
		
		if(connection != null && connection.isClosed() == false) {
			return connection;
		}
		Class.forName(driverClassName);
		return DriverManager.getConnection(url,user,password);
	}
	
	@Override
	public long count() 
	{
		try (Connection con = createConnection();
			 PreparedStatement pstmt = con.prepareStatement("select count(*) from products");
			 ResultSet rs = pstmt.executeQuery();
		) {
			rs.next();
			return rs.getLong(1);

		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

} 
