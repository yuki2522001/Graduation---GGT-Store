package GGTStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping({ "/", "/home/index" })
	public String home() {
		return "redirect:/product/list";
	}

	@RequestMapping("/home/reduce")
	public String reduce() {
		return "layout/_reduce";
	}

	@RequestMapping("/home/contact")
	public String contact() {
		return "layout/_contact";
	}

	@RequestMapping({ "/admin", "/admin/home/index" })
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
	
	@RequestMapping({ "/adminstrator", "/adminstrator/home/index" })
	public String adminstrator() {
		return "redirect:/assets/adminstrator/index.html";
	}
}
