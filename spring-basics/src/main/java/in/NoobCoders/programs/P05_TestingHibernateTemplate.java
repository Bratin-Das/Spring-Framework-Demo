package in.NoobCoders.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import in.NoobCoders.configuration.AppConfig4;
import in.NoobCoders.entity.Category;

public class P05_TestingHibernateTemplate {

	public static void main(String[] args)  
	{
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		//3.00.00
		HibernateTemplate ht = ctx.getBean(HibernateTemplate.class);
		Category c1 = ht.get(Category.class, 1);
		System.out.println(c1);
		
		ctx.close();
		
	}

}
