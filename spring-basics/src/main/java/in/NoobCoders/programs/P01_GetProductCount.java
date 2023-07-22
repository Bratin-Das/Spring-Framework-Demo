package in.NoobCoders.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import in.NoobCoders.configuration.AppConfig3;
import in.NoobCoders.dao.DaOException;
import in.NoobCoders.dao.ProductDao;

public class P01_GetProductCount {

	public static void main(String[] args) throws DaOException
	{
		//our dependency
		ProductDao dao;
		
		// a variable representing spring container and creating obj of it
		var ctx = new AnnotationConfigApplicationContext(AppConfig3.class);
		
		System.out.println("-----------");
		dao = ctx.getBean("jdbcDao", ProductDao.class);
		ProductDao dao2 = ctx.getBean("jdbcDao", ProductDao.class);
		System.out.println("dao2 == dao is " + (dao2 == dao));
		
		System.out.println("dao is an instanceof "+dao.getClass().getName());
		System.out.println("There are "+dao.count()+" products.");
		System.out.println("There are "+dao.count()+" products.");
		System.out.println("There are "+dao.count()+" products.");
		System.out.println("There are "+dao.count()+" products.");
		System.out.println("There are "+dao.count()+" products.");
		System.out.println("There are "+dao.count()+" products.");
		System.out.println("There are "+dao.count()+" products.");
		
		
		ctx.close();
	}

}
