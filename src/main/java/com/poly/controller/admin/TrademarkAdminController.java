package com.poly.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.AccountDao;
import com.poly.dao.ProductDao;
import com.poly.dao.TrademarkDao;
import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.entity.Trademark;
import com.poly.service.AccountService;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;
import com.poly.service.TrademarkService;
@Controller
public class TrademarkAdminController {
	@Autowired
	TrademarkService trademarkService;
	@Autowired
	TrademarkDao tdao;
	
//	@RequestMapping("trademark/list")
//	public String home(Model model) {
//		List<Trademark> list = trademarkService.findAll();
//		model.addAttribute("items", list);
//		return "admin/trademark/list";
//	}
//	@RequestMapping("/trademark/edit")
//	public String edit(Model model , @RequestParam("trademark_id") Integer trademark_id) {
//		Trademark tra = tdao.findById(trademark_id).get();
//		model.addAttribute("tra",tra);
//		return "admin/trademark/edit";
//	}
//	@RequestMapping("/trademark/delete/{trademark_id}")
//	public String delete(@PathVariable("trademark_id") Integer trademark_id) {
//		trademarkService.delete(trademark_id);
//		return "redirect:/trademark/list";
//	}
	@RequestMapping("/admin/trademark/list")
	public String home(Model model) {
	
		return "admin/trademark/index";
	}
}
