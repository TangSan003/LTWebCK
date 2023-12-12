package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDao;
import com.poly.dao.AuthorityDao;
import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;



@Service
public class AuthorityServiceImql implements AuthorityService {
	@Autowired
	AuthorityDao adao;
	@Autowired
	AccountDao acdao;

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		// TODO Auto-generated method stub
		List<Account> accounts =acdao.getAdministrators();
		return adao.authritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return adao.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		adao.deleteById(id);
	}

	@Override
	public Authority create(Authority auth) {
		// TODO Auto-generated method stub
		return adao.save(auth);
	}
	
}
