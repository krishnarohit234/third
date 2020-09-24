package com.htc.spring5mvc.service;

import java.util.List;

import com.htc.spring5mvc.beans.Product;

public interface ProductService {

	public boolean addProduct(Product product) ;
	public Product getProduct(String productCode);
	public List<Product> getProducts();
	public List<Product> getProducts(String category);
	
	public boolean updateProduct(Product product) ;
	public boolean deleteProduct(String productCode);
}
