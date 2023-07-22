package in.NoobCoders.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import in.NoobCoders.configuration.AppConfig4;
import in.NoobCoders.dao.DaOException;
import in.NoobCoders.dao.ProductDao;
import in.NoobCoders.entity.Product;

public class P03_TestingJdbcTemplateProductDao {

	public static void main(String[] args) throws DaOException {
		var ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		ProductDao dao = ctx.getBean("jtDao", ProductDao.class);
//-------------------------------GET PRODUCT------------------------------------------0
		Product p = new Product();
		p = dao.getProduct(1);
		System.out.println(p);
//--------------------------------UPDATE---------------------------------------------
		p.setUnitPrice(p.getUnitPrice()+1);
		dao.updateProduct(p);
		System.out.println("Price Updated");
//		dao.updateProduct(p);
//---------------------------------ADD--------------------------------------------------
//		p.setProductId(111);
//		p.setProductName("TinTin1");
//		p.setSupplierId(5022);
//		p.setCategoryId(3);
//		p.setQuantityPerUnit("100kgs");
//		p.setUnitPrice(169.0);
//		p.setUnitInStock(89);
//		p.setReorderLevel(52);
//		p.setDiscontinued(1);
//
//		dao.addProduct(p);
//		System.out.println("Product Added");
		//-------------------------------DELETE------------------------------------------
//		p.setDiscontinued(p.getDiscontinued());
//		dao.deleteProduct(77);
//		System.out.println("Product Discontinued successfully");

		//-------------------	PRODUCT BY PRICE RANGE --------------------------------------	
//		List<Product> list = dao.getProductsByPriceRange(50.0, 400.0);
//		for (Product pd : list) {
//			System.out.println("Product in range = "+pd);
//			
//		}
		
		//---------------------- DISCONTINUED PRODUCTS ---------------------------------------
//		int count = 0;
//		List<Product> list = dao.getDiscontinuedProducts();
//		for (Product product : list) 
//		{
//			System.out.println("Discontinued Products are as follows :"+product);
//			count++;
//		}
//		System.out.println("No of Discontinued products are = "+count);
		
		// ---------------------- PRODUCTS ON ORDER ----------------------------------------
//		List<Product> list = dao.getProductsOnOrder();
//		for (Product product : list) {
//			System.out.println("Product based on Order are = "+product);
//		}
		
		//------------------------ PRODUCT NOT IN STOCK ----------------------
//		List<Product> list = dao.getProductsNotInStock();
//		for (Product product : list) {
//			System.out.println("Product not in Stock are = "+product);
//		}
		
		//------------------------ PRODUCTS IN CATEGPORY ID --------------------------
		List<Product> list = dao.getProductsInCategory(2);
		System.out.println("Products in category 2 are :");
		for (Product product : list) {
			System.out.println(product);
		}
		
		ctx.close();
	}

}