package GGTStore.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import GGTStore.service.OrderDetailService;

@Controller
public class OrderDetailController {

	@Autowired
	OrderDetailService orderDetailService;
	
	@RequestMapping()
	public String showOrderDetail(Model model) {
		model.addAttribute("orderDetail", orderDetailService.findAll());
		
		return "/assets/admin/product/_order-detail";
	}
}
