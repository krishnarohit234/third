package com.htc.spring5mvc.beans;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product implements Comparable<Product>{

	@NotEmpty(message="{productForm.productCode.empty}" )
	private String productCode;
	
	@NotEmpty(message="Product Description is mandatory")
	private String productDescription;
	
	@Min(value=0L, message="Cannot be negative price")
	private double unitPrice;
	
	@DecimalMin(value="0.0", message="Cannot be negative price")
	private int qoh;
	
	//@Email
	//@Pattern
	//@Past
	//@PastOrPresent
	//@Future
	//@FutureOrPresent
	//@Positive
	//@Negative
	
	@NotNull
	@Size(min=4, max=4, message="Only 4 characters allowed.")
	private String category;
	
	public Product() {}

	public Product(String productCode, String productDescription, double unitPrice, int qoh, String category) {
		super();
		this.productCode = productCode;
		this.productDescription = productDescription;
		this.unitPrice = unitPrice;
		this.qoh = qoh;
		this.category = category;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQoh() {
		return qoh;
	}

	public void setQoh(int qoh) {
		this.qoh = qoh;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productDescription=" + productDescription + ", unitPrice="
				+ unitPrice + ", qoh=" + qoh + ", category=" + category + "]";
	}

	@Override
	public int compareTo(Product p) {
		return p.getProductCode().compareTo(this.getProductCode());
	}
	
}
