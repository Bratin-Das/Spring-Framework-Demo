package in.NoobCoders.programs;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import in.NoobCoders.configuration.AppConfig4;
import in.NoobCoders.entity.Category;

public class P02_TestingJdbcTemplate 
{
	static JdbcTemplate template;
	static RowMapper<Category> rowMapper =(rs , rowNum) ->
		{
			Category c = new Category();
			c.setCategoryId(rs.getInt("category_id"));
			c.setCategoryName(rs.getString("category_name"));
			c.setDescription(rs.getString("description"));
			c.setPicture(rs.getBytes("picture"));
			return c;
		};
	
	public static void main(String[] args) 
	{
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		template = ctx.getBean(JdbcTemplate.class);
		
		//insertShipper();
		//updateShipperPhone();
		//printProductCount();	
		//printShipperName(4);
		//printProductDetails(33);
		//printAllShippers();
		//printAllShipperName();
		//getCategory(1);
		getAllCategories();
		
		ctx.close();
	}

	static void getAllCategories() 
	{
		List<Category> list = template.query("select * from categories", rowMapper);
		for (Category c : list) {
			System.out.println(c);
			
		}
	}

	static void getCategory(int categoryId)
	{
		String sql = "select * from categories where category_id = ?";
		
		Category cat = template.queryForObject(sql, rowMapper, categoryId);
		System.out.println("Id = "+cat.getCategoryId());
		System.out.println("Name= "+cat.getCategoryName());
		System.out.println("Description = "+cat.getDescription());
	}

	
	
	static void printAllShipperName() 
	{
		String sql = "select company_name from shippers";
		List<String> list = template.queryForList(sql, String.class);
		for(String cName : list)
		{
			System.out.println(cName);
		}
	}

	static void printAllShippers() 
	{
		String sql = "select * from shippers";
		List<Map<String , Object>> list = template.queryForList(sql);
		System.out.println(list);
		
		for(Map<String, Object> data : list)
		{
			System.out.println(data.get("company_name")+ " ----> "+data.get("phone"));
		}
		
	}

	static void printProductDetails(int productId) 
	{
		String sql = "select * from products where product_id = ?";
		Map<String, Object> data = template.queryForMap(sql,productId);
		//System.out.println(data);
		for(String key : data.keySet()) {
			System.out.println(key + " ----> "+data.get(key));
		}
		
	}

	static void printShipperName(int ShipperId) 
	{
		String sql = "select company_name from shippers where shipper_id = ?";
		String sName = template.queryForObject(sql, String.class, ShipperId);
		System.out.println("Shipper Name = "+sName);
	}

	static void printProductCount() 
	{
		String sql = "select count(*) from products";
		Integer pc = template.queryForObject(sql, Integer.class);
		System.out.println("There are "+pc+" products");
	}

	static void updateShipperPhone() 
	{
		template.update("update shippers set phone = ? where shipper_id = ?","(914)878-0954",4);
		System.out.println("Phone Number Updated Successfully");
	}
	static void insertShipper() 
	 {
		template.update("insert into shippers values(?,?,?)",4,"Brazuka Transports","8617622355");
		System.out.println("New Shipper data inserted");
	 }

}
