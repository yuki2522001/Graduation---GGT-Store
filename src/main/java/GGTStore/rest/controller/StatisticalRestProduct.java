package GGTStore.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GGTStore.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/statistical/products")
public class StatisticalRestProduct {

	@Autowired
	ProductService productService;
	
	
	@GetMapping
	public long statisticalProduct(){
		return productService.count();
	}
}
