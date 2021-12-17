package GGTStore.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import GGTStore.entity.Authority;
import GGTStore.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {

	@Autowired
	AuthorityService authorityService;

	@GetMapping
	public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return authorityService.findAuthoritiesOfAdministrators();
		}
		return authorityService.findAll();
	}

	@GetMapping("/user")
	public List<Authority> findCust(@RequestParam("cust") Optional<Boolean> cust) {
		if (cust.orElse(false)) {
			return authorityService.findAuthoritiesOfCust();
		}
		return authorityService.findAll();
	}

	@GetMapping("/directors")
	public long statisticalAuthorityDirector() {
		return authorityService.authorityDirector();
	}

	@GetMapping("/staffs")
	public long statisticalAuthorityStaff() {
		return authorityService.authorityStaff();
	}

	@PostMapping
	public Authority post(@RequestBody Authority auth) {
		return authorityService.create(auth);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityService.delete(id);
	}
}
