package com.poly.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.AccountDao;
import com.poly.entity.Account;
import com.poly.service.AccountService;





@Controller
public class SignupController {
	
	@Autowired
	AccountService accservice;
	
	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	JavaMailSender sender;
	
	@Autowired
	AccountDao dao;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping("/home/signup")
	public String Signup() {
		return "user/acc/signup";
	}
	
	@RequestMapping("/signupnew")
	public String signup2(Account  acc , @RequestParam("confim-Password") String confim , Model model) {
		if(!dao.existsById(acc.getUsername())) {
			if(confim.equals(acc.getPassword())) {
			
				acc.setPhoto("avata.jpg");
			acc.setActive(false);
			accservice.create(acc);
			String urlActive = request.getRequestURL().toString().replace("signupnew","signup/active_account?username="+ acc.getUsername());
			SimpleMailMessage message = new SimpleMailMessage();
			String activeurl = "Nhấn vào link:  "+urlActive+" để kích hoạt tài khoản ";
			message.setTo(acc.getEmail());
			message.setSubject("Kích hoạt tài khoản BlackBear");
			message.setText(activeurl);
			// Send Message!
			this.emailSender.send(message);
			model.addAttribute("message","Vui lòng kiểm tra email để kích hoạt tài khoản");
			return "user/security/login";
			}
			model.addAttribute("message","Đăng kí không thành công");
			return "user/acc/signup";
		}
		else {
			model.addAttribute("message","Tài khoản đã có người sử dụng");
			return "user/acc/signup";
			
		}
		
	}
	
	@RequestMapping("/signup/active_account")
	public String activeAccount(Model model , @RequestParam("username") String username)
	{
		Account acc = dao.getById(username);
		acc.setActive(true);
		dao.save(acc);
		model.addAttribute("message", "Tài khoản kích hoạt thành công vui lòng đăng nhập lại");
		return "user/security/login" ;
	}
}
