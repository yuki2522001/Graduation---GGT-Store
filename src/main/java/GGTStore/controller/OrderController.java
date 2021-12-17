package GGTStore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import GGTStore.service.OrderDetailService;
import GGTStore.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailSerice;

	@RequestMapping("/order/checkout")
	public String checkout() {
		return "order/checkout";
	}
	
	@RequestMapping("/order/checkout/success")
	public String checkoutSuccess() {
		return "order/status";
	}
	
	@RequestMapping("/order/template/checkout")
	public String template() {
		return "order/template-checkout";
	}
	
	@RequestMapping("/admin/list/orders")
	public String orderList(Model model) {
		model.addAttribute("orderList", orderService.findAll());
		
		return "order/orderList";
	}

	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		model.addAttribute("orderss", orderService.findByUsername(username));
		return "order/list";
	}
	
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		return "order/detail";
	}
	
	@RequestMapping("/delete/order/detail/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		orderDetailSerice.deleteById(id);
		return "redirect:/order/list";
	}
}
