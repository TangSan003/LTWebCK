package com.poly.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.AccountDao;
import com.poly.entity.Account;
import com.poly.service.AccountService;



@Controller
public class ForgotControler {
	
	@Autowired
	AccountService accservice;

	@Autowired
	AccountDao dao;

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	JavaMailSender sender;
	
	@RequestMapping("/home/forgot")
	public String Login() {
		return "user/acc/forgot";
	}
	
	@PostMapping("/forgotPW")
	public String confirmmk(Model model, @RequestParam("name") String name, @RequestParam("email") String email) {

		String username = name.trim();
		Account acc = accservice.findById(username);
		try {
			if (email.equals(acc.getEmail())) {
				SimpleMailMessage message = new SimpleMailMessage();
				
				message.setTo(email);
				message.setSubject("Kiểm tra email");
				message.setText("Mật khẩu của bạn là: " + acc.getPassword());

				// Send Message!
				this.emailSender.send(message);

				model.addAttribute("message", "Gửi email thành công");
				return "user/acc/forgot";
			} else {
				model.addAttribute("message", "Email không khớp với email đã đăng kí ");
				return "user/acc/forgot";
			}
		} catch (Exception e) {
			return e.getMessage();
		}

	}
}
