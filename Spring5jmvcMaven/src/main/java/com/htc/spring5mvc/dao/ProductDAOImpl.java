package com.htc.spring5mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.htc.spring5mvc.beans.Product;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO{
	Logger logger =  Logger.getLogger(ProductDAOImpl.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean saveProduct(Product product) {
		logger.info("inside dao" + product);
		return saveProduct(product.getProductCode(), product.getProductDescription(), product.getUnitPrice(), product.getQoh(), product.getCategory());
	}

	@Override
	public boolean saveProduct(String productCode, String productDescription, double unitPrice, int qoh,
			String category) {
		String sql = "insert into product values(?,?,?,?,?)";
		int result = jdbcTemplate.update(sql, productCode, productDescription, unitPrice, qoh, category);
		if(result==1) 
			return true;
		return false;
	}

	@Override
	public boolean updateProduct(String productCode, double newPrice) {
		
		int result = jdbcTemplate.update("update product set unitprice = ? where productcode = ?", newPrice, productCode);
		if(result==1) 
			return true;

		return false;
	}

	//@Override
	public boolean updateProduct2(String productCode, double newPrice) {
		
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productcode", productCode);
		params.put("newPrice", new Double(newPrice));
		
		int result = namedJdbcTemplate.update("update product set unitprice = :newPrice where productcode = :productcode", params);
		if(result==1) 
			return true;

		return false;
	}

	
	@Override
	public boolean removeProduct(String productCode) {
		
		int result = jdbcTemplate.update("delete from product where productcode = ?", productCode);
		if(result==1) 
			return true;
		return false;
	}

	@Override
	public Product getProduct(String productCode) {
		Product p = null;
		try {
		p = jdbcTemplate.queryForObject("select productCode, product_desc, unitprice, qoh,category from product where productcode = ?", 
				new Object[] {productCode} , 
				new RowMapper<Product>() {
			
					@Override
						public Product mapRow(ResultSet rs, int arg1) throws SQLException {
							Product product = new Product();
							product.setProductCode(rs.getString(1));
							product.setProductDescription(rs.getString(2));
							product.setUnitPrice(rs.getDouble(3));
							product.setQoh(rs.getInt(4));
							product.setCategory(rs.getString(5));
							return product;
						}
				});
		}
		catch(Exception ex) {
			p = null;
		}
		return p;
	}

	@Override
	public List<Product> getProducts(String category) {
		List<Product> productList = jdbcTemplate.query("select productCode, product_desc, unitprice, qoh,category from product where category = ?",
							new Object[] {category}, 
							new RowMapper<Product>() {

								@Override
								public Product mapRow(ResultSet rs, int arg1) throws SQLException {
									Product product = new Product();
									product.setProductCode(rs.getString(1));
									product.setProductDescription(rs.getString(2));
									product.setUnitPrice(rs.getDouble(3));
									product.setQoh(rs.getInt(4));
									product.setCategory(rs.getString(5));
									return product;

								}
			
		});
		return productList;
	}

	@Override
	public List<Product> getProducts(double minPrice, double maxPrice) {
		List<Product> productList = jdbcTemplate.query("select productCode, product_desc, unitprice, qoh,category from product where unitprice >= ? and unitprice <=?",
				new Object[] {new Double(minPrice), new Double(maxPrice)}, 
				new RowMapper<Product>() {

					@Override
					public Product mapRow(ResultSet rs, int arg1) throws SQLException {
						Product product = new Product();
						product.setProductCode(rs.getString(1));
						product.setProductDescription(rs.getString(2));
						product.setUnitPrice(rs.getDouble(3));
						product.setQoh(rs.getInt(4));
						product.setCategory(rs.getString(5));
						return product;

					}

		});
		return productList;	
	}

	@Override
	public List<Product> getProducts2(double minPrice, double maxPrice) {
		
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		Map<String, Double> paramMap = new HashMap<String, Double>();
		paramMap.put("minPrice", new Double(minPrice));
		paramMap.put("maxPrice", maxPrice);
		
		List<Product> productList = namedJdbcTemplate.query("select productCode, product_desc, unitprice, qoh,category from product where unitprice >= :minPrice and unitprice <= :maxPrice",
				paramMap, 
				new RowMapper<Product>() {

					@Override
					public Product mapRow(ResultSet rs, int arg1) throws SQLException {
						Product product = new Product();
						product.setProductCode(rs.getString(1));
						product.setProductDescription(rs.getString(2));
						product.setUnitPrice(rs.getDouble(3));
						product.setQoh(rs.getInt(4));
						product.setCategory(rs.getString(5));
						return product;

					}

		});
		return productList;	
	}

	@Override
	public List<Product> getProducts() {
		List<Product> productList = jdbcTemplate.query("select productCode, product_desc, unitprice, qoh,category from product",
				new RowMapper<Product>() {

					@Override
					public Product mapRow(ResultSet rs, int arg1) throws SQLException {
						Product product = new Product();
						product.setProductCode(rs.getString(1));
						product.setProductDescription(rs.getString(2));
						product.setUnitPrice(rs.getDouble(3));
						product.setQoh(rs.getInt(4));
						product.setCategory(rs.getString(5));
						return product;
					}
		});
		return productList;	
	}

	@Override
	public String getProdutName(String productCode) {
		String productName = jdbcTemplate.queryForObject("select product_desc from Product where productcode = ?", new Object[] {productCode}, String.class);
		return productName;
	}

	@Override
	public int getProductCount(String category) {
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
										.withFunctionName("GETPRODUCTCOUNT");
		
		Integer count = jdbcCall.executeFunction(Integer.class, category);
		return count;
	
	}
	
	
	public void procedureCallDemo(String productCode) {
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
										.withProcedureName("GETPRODUCTDETAILS")
										.useInParameterNames("pcode")
										.declareParameters(
												new SqlParameter("pcode", Types.VARCHAR),
												new SqlOutParameter("product_desc", Types.VARCHAR),
												new SqlOutParameter("unitprice", Types.DECIMAL)
										);
										
			Map<String, Object> result  = jdbcCall.execute(productCode);
			
			System.out.println(result.get("product_desc"));
			System.out.println(result.get("unitprice"));
	}
	
}
