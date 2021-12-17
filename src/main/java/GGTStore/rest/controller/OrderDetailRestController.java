package GGTStore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GGTStore.entity.OrderDetail;
import GGTStore.service.OrderDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/list/orders")
public class OrderDetailRestController {

	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping()
	public List<OrderDetail> getAll() {
		return orderDetailService.findAll();
	}
	
	@GetMapping("/success")
	public long statisticalOrderDetailSuccess(){
		return orderDetailService.countOrderDetailSuccess();
	}
	
	@GetMapping("/fail")
	public long statisticalOrderDetailFail(){
		return orderDetailService.countOrderDetailFail();
	}
	
	@GetMapping("/onair")
	public long statisticalOrderDetailOnAir(){
		return orderDetailService.countOrderDetailOnAir();
	}
	
	@GetMapping("/cancel")
	public long statisticalOrderDetailCancel(){
		return orderDetailService.countOrderDetailCancel();
	}
	
	@GetMapping("/totalRevenue")
	public long statisticaltotalRevenue(){
		return orderDetailService.TotalRevenue();
	}
	
	@GetMapping("/totalQuantity")
	public long statisticaltotalQuantity(){
		return orderDetailService.TotalQuantity();
	}
	
	@PutMapping("{id}")
	public OrderDetail update(@RequestBody OrderDetail orderDetail, @PathVariable("id") Long id) {
		return orderDetailService.update(orderDetail);
	}
	
	@DeleteMapping("{id}")
	public void update(@PathVariable("id") Long id) {
		orderDetailService.delete(id);
	}
}
