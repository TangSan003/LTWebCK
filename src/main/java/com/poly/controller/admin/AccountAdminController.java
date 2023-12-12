package com.poly.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.dao.AccountDao;
import com.poly.dao.CommentDao;
import com.poly.dao.OrderDao;
import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.entity.Comment;
import com.poly.entity.Order;
import com.poly.entity.Product;
import com.poly.entity.Report;
import com.poly.entity.Trademark;
import com.poly.service.AccountService;

@Controller
public class AccountAdminController {
	@Autowired
	AccountService accountService;
	@Autowired
	AccountDao aDao;
	@Autowired
	OrderDao odao;
	@Autowired
	CommentDao commentDao;
	
	// hiển thị danh sách các tài khoản
	@GetMapping("admin/account/list")
	public String index(Model model,HttpServletRequest request
			,RedirectAttributes redirect) {
		request.getSession().setAttribute("accountlist", null);
		if(model.asMap().get("success") != null)
			redirect.addFlashAttribute("success",model.asMap().get("success").toString());
		return "redirect:/admin/account/list/page/1";
	}
	@GetMapping("/admin/account/list/page/{pageNumber}")
	public String showProductPage(HttpServletRequest request,
			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("accountlist");
		int pagesize = 9;
		List<Account> list =(List<Account>) accountService.findAll();
		model.addAttribute("sizepro", list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("accountlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/admin/account/list/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("items", pages);

		return "/admin/account/list";
	}
	
	
//	@RequestMapping("/account/list")
//	public String index(Model model) {
//		List<Account> List = aDao.findAll();
//		model.addAttribute("items", List);
//		return "/admin/account/list";
//	}
	@GetMapping("/admin/account/findIdorName")
	public String search(@RequestParam("keyword") String keyword, Model model) {
		if (keyword.equals("")) {
			return "redirect:/admin/account/list";
		}
		model.addAttribute("items", aDao.finbyIdOrName(keyword));
		return "list";
	}
	@RequestMapping("/admin/account/findIdorName/{pageNumber}")
	public String findIdorName(Model model ,  @RequestParam("keyword") String keyword, HttpServletRequest request,
			@PathVariable int pageNumber) {
		if (keyword.equals("")) {
			return "redirect:/admin/account/list";
		}
		List<Account> list = aDao.finbyIdOrName(keyword);
		if (list == null) {
			return "redirect:/admin/account/list";
		}
		model.addAttribute("sizepro", list.size());
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("accountlist");
		int pagesize = 9;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("accountlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/admin/account/list/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("items", pages);
		return "/admin/account/list";
	}
	@RequestMapping("admin/account/edit")
	public String edit(Model model, @RequestParam("username") String username) {
		try {
			Account acc = aDao.findById(username).get();
			
			model.addAttribute("acc", acc);
			List<Order> order = odao.findByUsername(username);
			model.addAttribute("order",order);
			model.addAttribute("listOrder",order.size());
			model.addAttribute("message", "Hiện chi tiết khách hàng thành công");
			return "admin/account/edit";
		} catch (Exception e) {
			model.addAttribute("message", "Hiện chi tiết khách hàng thất bại");
			return "admin/account/edit";
		}
	}
	@RequestMapping("admin/account/update")
	public String update(Account acc, Model model, @RequestParam("photo_img") MultipartFile file) throws IOException {
		if (aDao.existsById(acc.getUsername())) {
			File saveFile = accountService.save(file, "assets/images");
			
			String filename = file.getOriginalFilename();
		
			if (filename == "") {
				accountService.update(acc);
				model.addAttribute("message", "Cập nhập thành công");
				return "redirect:/admin/account/edit?username=" + acc.getUsername();
			} else {
				acc.setPhoto(filename);
				accountService.update(acc);
				model.addAttribute("message", "Cập nhập thành công");
				return "redirect:/admin/account/edit?username=" + acc.getUsername();
			}
		} else {
			model.addAttribute("message", "Cập nhập thất bại");
			return "redirect:/admin/account/edit?username=" + acc.getUsername();
		}
	}
	@RequestMapping("/admin/account/delete/form/{username}")
	public String deleteform(Model model, @PathVariable("username") String username) {
		try {
			aDao.deleteById(username);
			model.addAttribute("message", "Xoá thành công");
			return "redirect:/admin/account/list";
		} catch (Exception e) {
			model.addAttribute("message", "Xoá thất bại");
			return "redirect:/admin/account/list";
		}

	}
	@RequestMapping("/admin/account/delete/{username}")
	public String delete(Model model,@PathVariable("username") String username) {
		try {
			aDao.deleteById(username);
			
		model.addAttribute("message", "Xoá thành công");
		return "redirect:/admin/account/list";
		}
		catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", "Xóa thất bại ");
			return "redirect:/admin/account/list";
		}
		
	}
	
	@RequestMapping("/admin/account/create")
	public String create(Account acc, Model model, @RequestParam("photo_img") MultipartFile file) throws IOException {
		File saveFile = accountService.save(file, "assets/images");
	
		String filename1 = file.getOriginalFilename();
	
		if (filename1 == "") {
	
			aDao.save(acc);
			model.addAttribute("message", "Thêm mới thành công");
			return "redirect:/admin/account/detail";
		} else {
			acc.setPhoto(filename1);
	
	
			aDao.save(acc);
			model.addAttribute("message", "Thêm mới thành công");
			return "redirect:/admin/account/detail";
		}
	}
	@RequestMapping("/admin/account/detail")
	public String detail(Model model) {
		Account acc = new Account();
		acc.setUsername("");
		acc.setFullname("");
		acc.setPhoto("");
		acc.setEmail("");
		acc.setPassword("");
		acc.setPhone("");
		acc.setActive(true);
	
		
		model.addAttribute("acc", acc);
		return "admin/account/detail";
	}
}
