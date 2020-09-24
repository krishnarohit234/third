package com.htc.spring5mvc.dao;

import java.util.List;

import com.htc.spring5mvc.beans.Product;

public interface ProductDAO {

	public boolean saveProduct(Product product);
	public boolean saveProduct(String productCode, String productDescription, double unitPrice, int qoh, String category);
	public boolean updateProduct(String productCode, double newPrice);
	public boolean updateProduct2(String productCode, double newPrice);
	
	public boolean removeProduct(String productCode);
	
	public Product getProduct(String productCode);
	
	public List<Product> getProducts(String category);
	public List<Product> getProducts(double minPrice, double maxPrice);
	public List<Product> getProducts2(double minPrice, double maxPrice);
	public List<Product> getProducts();
	
	public String getProdutName(String productCode);
	
	public int getProductCount(String category);
	
}
