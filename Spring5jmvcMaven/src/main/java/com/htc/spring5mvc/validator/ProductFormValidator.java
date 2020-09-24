package com.htc.spring5mvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.htc.spring5mvc.beans.Product;

@Component
public class ProductFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return Product.class.equals(cls);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Product prod = (Product) target;
		
		ValidationUtils.rejectIfEmpty(errors, "productCode", "productForm.productCode.empty","");
		ValidationUtils.rejectIfEmpty(errors, "productDescription", "productForm.productDescription.empty","");
		ValidationUtils.rejectIfEmpty(errors, "unitPrice", "productForm.unitPrice.empty","");
		ValidationUtils.rejectIfEmpty(errors, "qoh", "productForm.qoh.empty","");
		
		String price = "" + prod.getUnitPrice();
		System.out.println(price);
		try {
			double unitPrice = Double.parseDouble(price);
			
			System.out.println(unitPrice);
			if(unitPrice<0) {
				errors.rejectValue("unitPrice", "productForm.unitPrice.negative");
			}
		}
		catch(Exception ex) {
			errors.reject("unitPrice", "productForm.unitPrice.invalid");
		}
		
		String qty = "" + prod.getQoh();
		try {
			int qoh = Integer.parseInt(qty);
			System.out.println(qoh);
			if(qoh<0) {
				errors.rejectValue("qoh", "productForm.qoh.negative");
			}
		}
		catch(Exception ex) {
			errors.reject("qoh", "productForm.qoh.invalid");
		}
	
	}
}
