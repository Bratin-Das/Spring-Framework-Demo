package in.NoobCoders.programs;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import in.NoobCoders.entity.Category;

public class P04_HibernateExample 
{
	  public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("hibernate.connection.driver_class","org.h2.Driver");
		props.setProperty("hibernate.connection.url", "jdbc:h2:tcp://localhost/~/spring-training");
		props.setProperty("hibernate.connection.user", "root");
		props.setProperty("hibernate.connection.password", "root");
		//Takes care of generating of revelent sql cmds for each operation we do
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		
		
		Configuration cfg = new Configuration();
		cfg.setProperties(props);
		cfg.addAnnotatedClass(Category.class);
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();     // Equivalent to DB connection
		
		Category c1 = session.get(Category.class, 1);
		System.out.println(c1);
		System.out.println("------------------------------------------------");
		
		Query<Category> qry = session.createQuery("from Category", Category.class);
		List<Category> list = qry.getResultList();
		
		for (Category c : list) {
			System.out.println(c);
		}
		
		System.out.println("-----------------------------------------------");
		
		Category c2 = new Category();		
		c2.setCategoryId(33);
		c2.setCategoryName("Stationaries");
		c2.setDescription("Pen , Pencil , Clips , etc.");
		
		Transaction tx = session.beginTransaction();
		try {
			session.persist(c2);
			tx.commit();
			System.out.println("New Category Added ");
		}catch(Exception ex) {
			tx.rollback();
			System.out.println("There was an error in adding new Category! Category Already exits");
		}
		
		
		session.close();
		factory.close();
	}

}
		