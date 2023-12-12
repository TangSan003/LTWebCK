package com.poly.controller.user;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.FavoriteDao;
import com.poly.entity.Account;
import com.poly.entity.Favorite;
import com.poly.entity.Product;
import com.poly.service.FavoriteService;

@Controller
public class FavoriteController {
	@Autowired
	FavoriteService fadao;
	
	@Autowired
	FavoriteDao dao;
	
		
	@RequestMapping("/favolist/user")
	public String favolist(Model model , HttpServletRequest request) {
		String username = request.getRemoteUser();
		List<Favorite> list = dao.getListUserFavorite(username);
		model.addAttribute("listfavo", list);
		 return "user/favo/list";
	}
	
	@RequestMapping("/favolist/remove/{product_id}")
	public String removefavo(Model model,@PathVariable("product_id") Integer product_id ,HttpServletRequest request ){
		String username = request.getRemoteUser();
		dao.deleteFavaritesAdmin(product_id, username);
		 return "redirect:/favolist/user";
	}
	
	@RequestMapping("/add/favo/{product_id}")
	public String addFavo(Model model ,@PathVariable("product_id") Integer product_id ,HttpServletRequest request ) {
		String username = request.getRemoteUser();
		Favorite addlist = new Favorite();
		Account acc = new Account();
		acc.setUsername(username);
		Product pro = new Product();
		pro.setProduct_id(product_id);
	
			addlist.setFavorite_date(new Date());
			addlist.setAccount(acc);;
			addlist.setProduct(pro);
			dao.save(addlist);
			model.addAttribute("statusfavo", "Đã thích");
			return "redirect:/product/detail/"+product_id;
		
	}
	
	@RequestMapping("/delete/favo/{product_id}")
	public String deletefavo(Model model,@PathVariable("product_id") Integer product_id ,HttpServletRequest request ){
		String username = request.getRemoteUser();
		dao.deleteFavaritesAdmin(product_id, username);
		model.addAttribute("statusfavo", "Chưa thích");
		return "redirect:/product/detail/"+product_id;
	}
}
