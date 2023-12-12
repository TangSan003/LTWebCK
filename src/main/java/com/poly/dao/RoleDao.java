package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.entity.Category;
import com.poly.entity.Role;

public interface RoleDao extends JpaRepository<Role, String>{

}
