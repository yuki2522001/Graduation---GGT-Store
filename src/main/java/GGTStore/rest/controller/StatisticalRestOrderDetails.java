package GGTStore.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GGTStore.service.OrderDetailService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/statistical/orderDetails")
public class StatisticalRestOrderDetails {

	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping
	public long statisticalOrderDetails(){
		return orderDetailService.count();
	}
	
	
	
}
