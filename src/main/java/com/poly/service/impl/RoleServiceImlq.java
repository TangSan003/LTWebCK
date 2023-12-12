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
import com.poly.dao.RoleDao;
import com.poly.entity.Role;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.RoleService;



@Service
public class RoleServiceImlq implements RoleService {
	@Autowired
	private RoleDao rdao;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return rdao.findAll();
	}

	

	
}
