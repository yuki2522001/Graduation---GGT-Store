package GGTStore.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GGTStore.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/statistical/categories")
public class StatisticalRestCategory {

	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping
	public long statisticalCategory(){
		return categoryService.count();
	}
}
