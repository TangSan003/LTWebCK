package com.poly.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.AccountDao;
import com.poly.entity.Account;
import com.poly.service.AccountService;

@Controller
public class ChangeControler {
	
	@Autowired
	AccountService accservice;
	
	@Autowired
	AccountDao dao;
	
	@RequestMapping("/home/change")
	public String Login() {
		return "user/acc/change";
	}
	
	@RequestMapping("/changePW")
	public String signup2(Account  acc ,
			@RequestParam("username") String username,
			@RequestParam("pswnew") String pswnew,
			@RequestParam("confim") String confim ,
			@RequestParam("confim-pswnew") String confimpswnew , Model model) {
		acc = accservice.findById(username);
		if(confim.equals(acc.getPassword()) && confimpswnew.equals(pswnew)) {
			acc.setPassword(pswnew);
			accservice.update(acc);
			model.addAttribute("message","Đổi mật khẩu thành công");
			return "user/acc/change";
		}
		else {
			model.addAttribute("message","Xác nhận mật khẩu không đúng");
			return "user/acc/change";
		}
		
	}
}
