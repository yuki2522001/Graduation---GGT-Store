package GGTStore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GGTStore.entity.Account;
import GGTStore.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts/users")
public class UserRestController {

	@Autowired
	AccountService accountService;
	
	@GetMapping
	public List<Account> getAll(){
		return accountService.findAll();
	}
	
	@DeleteMapping("{username}")
	public void update(@PathVariable("username") String username) {
		accountService.delete(username);
	}
}
