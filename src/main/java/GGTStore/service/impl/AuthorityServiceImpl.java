package GGTStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GGTStore.dao.AccountDAO;
import GGTStore.dao.AuthorityDAO;
import GGTStore.entity.Account;
import GGTStore.entity.Authority;
import GGTStore.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	AuthorityDAO dao;
	
	@Autowired
	AccountDAO acdao;

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = acdao.getAdminstrators();
		return dao.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		return dao.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		return dao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Authority> findAuthoritiesOfCust() {
		List<Account> accounts = acdao.getUsers();
		return dao.authoritiesOf(accounts);
	}

	@Override
	public long authorityDirector() {
		return dao.authorityDirector();
	}

	@Override
	public long authorityStaff() {
		return dao.authorityStaff();
	}
	
	
}
