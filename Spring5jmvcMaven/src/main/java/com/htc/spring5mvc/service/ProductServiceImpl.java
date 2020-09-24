package com.htc.spring5mvc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htc.spring5mvc.beans.Product;
import com.htc.spring5mvc.dao.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDAO;
	
	Logger logger =  Logger.getLogger(ProductServiceImpl.class);
	
	public boolean addProduct(Product product) {
		logger.info("Inside service class");
		logger.info("From Service:" + product);
		
		return productDAO.saveProduct(product);
	}

	@Override
	public Product getProduct(String productCode) {
		logger.info("getProduct()" + productCode);
		
		return productDAO.getProduct(productCode);
	}

	@Override
	public List<Product> getProducts() {
		logger.info("getProducts()");
		return productDAO.getProducts();
	}

	@Override
	public List<Product> getProducts(String category) {
		logger.info("getProduct()" + category);
		return productDAO.getProducts(category);
	}

	@Override
	public boolean updateProduct(Product product) {
		logger.info("updateProduct" + product);
		return productDAO.updateProduct(product.getProductCode(), product.getUnitPrice());
	}

	@Override
	public boolean deleteProduct(String productCode) {
		logger.info("remove product" + productCode);
		return productDAO.removeProduct(productCode);
	}
	
}
