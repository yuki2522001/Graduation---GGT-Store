package GGTStore.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GGTStore.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/statistical/orders")
public class StatisticalRestOrder {

	@Autowired
	OrderService orderService;
	
	
	@GetMapping
	public long statisticalOrder(){
		return orderService.count();
	}
}
