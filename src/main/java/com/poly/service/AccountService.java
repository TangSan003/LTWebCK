package com.poly.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.poly.entity.Account;



public interface AccountService {
	 Account findById(String username);

	 List<Account> getAdminnostrators();

	 List<Account> findAll();
	 
	 Account create(Account account);
	 
	 Account update(Account account);
	 
	 void delete(String username);
	 
	 public File save(MultipartFile file, String path)  throws IOException;
}
