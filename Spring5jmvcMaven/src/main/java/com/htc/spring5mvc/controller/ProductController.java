package com.htc.spring5mvc.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.htc.spring5mvc.beans.Product;
import com.htc.spring5mvc.service.ProductService;
import com.htc.spring5mvc.validator.ProductFormValidator;

@Controller
public class ProductController {
	Logger logger =  Logger.getLogger(ProductController.class);

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductFormValidator validator;
	
	@InitBinder   //Execute for each request.
	public void dataBinding(WebDataBinder binder) {
		//binder.registerCustomEditor(java.util.Date.class, "expiryDate", new CustomDateEditor(dateFormat, false));
	//	binder.setValidator(validator);
		//binder.addValidators(validators);
	}
	
	@RequestMapping(value="/productForm", method=RequestMethod.GET)
	public ModelAndView productForm() {
		logger.info("showing product form");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("productForm");
		mv.addObject("product", new Product());
		return mv;
	}
	
	@RequestMapping(value = "/addProduct", method=RequestMethod.POST)    
	public String addProduct(@ModelAttribute(name="product") @Valid Product product, BindingResult result, RedirectAttributes redirectAttrs, Model model) {

		if(result.hasErrors()) {
			model.addAttribute("product", product);
			return "productForm";
		}
		else {
			logger.info("addProduct() method");
			logger.info(product.toString());
		
			boolean insertStatus = productService.addProduct(product);
			logger.info(insertStatus);
			if(insertStatus) {
				redirectAttrs.addFlashAttribute("msg", "Product Added Successfully");
				redirectAttrs.addFlashAttribute("productName", product.getProductDescription());
				return "redirect:/addProductSuccess";
			}
			else
				return "redirect:/addProductFailure";
		}
	}

	@GetMapping(value="/addProductSuccess")
	 public String showSuccess() {
		 return "addProductSuccess";
	 }

	@GetMapping(value="/list")
	public ModelAndView listProducts() {
		
		List<Product> productList = productService.getProducts();
		Collections.sort(productList);		
		ModelAndView mv = new ModelAndView("listproduct","productList", productList);
		return mv;
	}
	
	@GetMapping(value="/editProductForm")
	public ModelAndView editProductForm(@RequestParam(name="productCode") String productCode) {
		logger.info("Param:" + productCode);
		Product product = productService.getProduct(productCode);
		ModelAndView mv = new ModelAndView("editProductForm", "product", product);
		return mv;
	}
	
	@PostMapping(value="/updateProduct")
	public String updateProduct(@ModelAttribute(name="product") @Valid Product product, BindingResult result, RedirectAttributes redirectAttrs, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("product-", product);
			return "editProductForm";
		}
		else {
			logger.info("updateproduct:" + product.toString());
		
			boolean updateStatus = productService.updateProduct(product);
			logger.info(updateStatus);
			if(updateStatus) {
				redirectAttrs.addFlashAttribute("msg", "Product Updated Successfully");
				redirectAttrs.addFlashAttribute("productName", product.getProductDescription());
				return "redirect:/addProductSuccess";
			}
			else
				return "redirect:/addProductFailure";
		}
	}
	
	@GetMapping(value="/deleteProduct/{productCode}")
	public String deleteProduct(@PathVariable(name="productCode") String productCode) {
		logger.info("deleteProduct-" + productCode);
		boolean result = productService.deleteProduct(productCode);
		if(result) {
			return "redirect:/list";
		}
		else {
			return "deleteFail";
		}
	}


	@GetMapping(value="/searchProductForm")
	public String searchForm() {
		return "searchProductForm";
	}
	
	@GetMapping(value="/getProductAsJSON/{productCode}",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Product getProductFormJSON(@PathVariable(name="productCode") String productCode, HttpServletResponse response) {
		Product product = productService.getProduct(productCode);
		response.setHeader("Cache-control", "no-cache");
		return product;
	}
	
	@PutMapping(value="/updateProductAsJSON")
	@ResponseBody
	public String updateProductJSON(@RequestBody Product product) {
		System.out.println(product);
		boolean updateStatus = productService.updateProduct(product);
		if(updateStatus) {
			return "Product Updated Successfully";
		}else {
			return "Product Updation Failed";
		}
	}

	@DeleteMapping(value="/deleteProductUsingAJAX/{productCode}")
	@ResponseBody
	public String deleteProductUsingAJAX(@PathVariable(name="productCode") String productCode, HttpServletResponse response) {
		logger.info("deleteProduct-" + productCode);
		boolean result = productService.deleteProduct(productCode);
		response.setHeader("Cache-control", "no-cache");
		if(result) {
			return "Product Deleted successfully";
		}
		else {
			return "Error while deleting product";
		}
	}

	/*
	@ExceptionHandler
	public ModelAndView handleException(Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		modelAndView.addObject("errorMessage", ex.getMessage());
		return modelAndView;
	}	
	 */
}
