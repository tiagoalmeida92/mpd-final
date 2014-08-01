package daoGenerator.test;


import junit.framework.Assert;
import model.Product;

import org.junit.Before;
import org.junit.Test;

import orm.JdbcExecutorSingleConnection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import daoGenerator.Builder;


public class TestDaoGenerator{
	
	private SQLServerDataSource ds;
	private JdbcExecutorSingleConnection exec;
	private ProductDao dao;

	@Before
	public void setup(){
		setDataSource();
		exec = new JdbcExecutorSingleConnection(ds, false);
		dao = Builder.make(ProductDao.class, exec);
	}
	
	 void setDataSource(){
		ds = new SQLServerDataSource();
		ds.setUser("sa");
		ds.setPassword("123456");
		ds.setDatabaseName("NORTHWND");
		ds.setServerName("TIAGO-TOSH");
	}
	
	
	@Test
	public void test_simple(){
		Iterable<Product> res = dao.getAll();
		int size = 0;
		for(Product e:res){size++;}
		Assert.assertEquals(77, size);
		
	}
	
	@Test
	public void test_product_load_by_id(){
		Product p = dao.getById(9);
		
		Assert.assertEquals("Mishi Kobe Niku", p.getProductName());
		Assert.assertEquals(97.0, p.getUnitPrice());
		Assert.assertEquals(29, p.getUnitsInStock());
	
		
	}
	
}
