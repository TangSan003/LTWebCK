package com.poly.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.Order;

@Controller
public class ContactController {

	@Autowired
	JavaMailSender javaMailSender;
	@RequestMapping("/contact")
	public String sendMail( 
			@RequestParam("subject") String subject, 
			@RequestParam("content") String content,
			@RequestParam("phone1") String phone1,
			@RequestParam("message") String message
		
			) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("morethanblue2003@gmail.com");
		msg.setText(
		"Tên khách hàng	 : " 
		+ content 
		+ "\n"
		+ "	SDT : " 
		+ phone1		
		+ "\n"
		+ "	Nội dung  : " 
		+ "\n"
		+ subject
		+ "\n"
		+ "================================");
		
		msg.setSubject(message);
		javaMailSender.send(msg);
		return "user/result";
	}
}
