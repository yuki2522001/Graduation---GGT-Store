package GGTStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import GGTStore.service.AccountService;

@Controller
@RequestMapping("admin/accounts")
public class UserController {

	@Autowired
	AccountService accountService;

	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("accounts", accountService.findAll());

		return "/assets/admin/authority/users";
	}

	@GetMapping("delete/{username}")
	public String delete(Model model, @PathVariable("username") String username) {
		accountService.deleteById(username);

		model.addAttribute("message", "Account " + username + " is deleted !");

		return "/assets/admin/authority/users";
	}

}
