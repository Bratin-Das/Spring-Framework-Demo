package in.NoobCoders.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import in.NoobCoders.configuration.AppConfig4;
import in.NoobCoders.dao.DaOException;
import in.NoobCoders.dao.ProductDao;
import in.NoobCoders.entity.Product;

public class P06_TestingHibernateTemplateProductDao {

	public static void main(String[] args) throws DaOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		
		//htdao --> instance of the Hibernate Template 
		
		ProductDao dao = ctx.getBean("htDao",ProductDao.class);
		System.out.println("Dao is an instance of "+dao.getClass().getName());
		
		List<Product> list = dao.getAllProducts();
		System.out.println("There are "+list.size()+" Products");
		
		Double max = 200.0;
		Double min = 50.0;
		list = dao.getProductsByPriceRange(min, max);
		System.out.println("There are "+list.size()+ " products between $"+min+" and $"+max);
		
		 min = 200.0;
		 max = 50.0;
		list = dao.getProductsByPriceRange(min, max);
		System.out.println("There are "+list.size()+ " products between $"+min+" and $"+max);
		
		long count = dao.count();
		System.out.println("There are "+count+ " products");
		
		Product p = dao.getProduct(1);
		System.out.println(p.getProductName()+" ---> "+ p.getUnitPrice());
		
		p.setUnitPrice(p.getUnitPrice()+1);
		try {
			dao.updateProduct(p);
		} catch (DaOException e) {
			System.out.println("There was an error :: "+e.getMessage());
		}
		
		System.out.println("End of Testing ...");
		
		ctx.close();
	}

}
