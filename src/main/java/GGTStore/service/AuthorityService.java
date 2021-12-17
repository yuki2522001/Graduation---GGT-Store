package GGTStore.service;

import java.util.List;

import GGTStore.entity.Authority;

public interface AuthorityService {
	
	public List<Authority> findAll();
	
	public Authority create(Authority auth);
	
	public void delete(Integer id);
	
	public List<Authority> findAuthoritiesOfAdministrators();
	
	public List<Authority> findAuthoritiesOfCust();
	
	long authorityDirector();
	
	long authorityStaff();
}
