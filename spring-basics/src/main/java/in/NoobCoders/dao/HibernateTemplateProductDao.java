package in.NoobCoders.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import in.NoobCoders.entity.Product;

@SuppressWarnings("unchecked")
@Repository("htDao")
public class HibernateTemplateProductDao implements ProductDao 
{
	@Autowired(required = false) //in practice we wont use at all
	private HibernateTemplate template;

	@Override
	public void addProduct(Product product) throws DaOException {
		template.persist(product);
	}

	@Override
	public void updateProduct(Product product) throws DaOException {
		template.merge(product);
	}

	@Override
	public Product getProduct(Integer productId) throws DaOException {
		return template.get(Product.class, productId);
	}

	@Override
	public void deleteProduct(Integer productId) throws DaOException {
		Product p = getProduct(productId);
		p.setDiscontinued(1);
		template.merge(p);
	}

	@Override
	public List<Product> getAllProducts() throws DaOException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		return (List<Product>) template.findByCriteria(dc);
	}

	@Override
	public List<Product> getProductsByPriceRange(Double min, Double max) throws DaOException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.between("unitPrice", min, max));
		return (List<Product>) template.findByCriteria(dc);
	}

	@Override
	public List<Product> getProductsInCategory(Integer categoryId) throws DaOException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("categoryId", categoryId)); //eq--equal
		return (List<Product>) template.findByCriteria(dc);
	}

	@Override
	public List<Product> getProductsNotInStock() throws DaOException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("unitInStock", 0));
		return (List<Product>) template.findByCriteria(dc);

	}

	@Override
	public List<Product> getProductsOnOrder() throws DaOException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.gt("unitsOnOrder", 0));       //gt--greater
		return (List<Product>) template.findByCriteria(dc);

	}

	@Override
	public List<Product> getDiscontinuedProducts() throws DaOException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("discontinued", 1));
		return (List<Product>) template.findByCriteria(dc);

	}

	@Override
	public long count() throws DaOException 
	{
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.setProjection(Projections.rowCount());
		
		
		
		return (long) template.findByCriteria(dc).get(0);
	}
	
	

}
