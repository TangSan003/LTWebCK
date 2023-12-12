package com.poly.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poly.dao.AccountDao;
import com.poly.dao.AuthorityDao;
import com.poly.dao.ProductDao;
import com.poly.entity.Product;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.ProductService;



@Service
public class ProductServiceImlq implements ProductService {
	@Autowired
	private ProductDao pdao;
	@Autowired
	HttpServletRequest request;
	@Override
	public List<Product> findAll() {
		
		return pdao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		
		return pdao.findById(id).get();
	}

	@Override
	public List<Product> findByCategoryId(Integer cid) {
		// TODO Auto-generated method stub
		return pdao.findByCategoryId(cid);
	}

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return pdao.save(product);
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return pdao.save(product);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		 pdao.deleteById(id);;
	}

	@Override
	public List<Product> findByNameContaining(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByKeywords(String keywords) {
		// TODO Auto-generated method stub
		return pdao.findByKeywords(keywords);
	}

	@Override
	public List<Product> findByTrademarkId(Integer integer) {
		// TODO Auto-generated method stub
		return pdao.findByTrademarkId(integer);
	}

	@Override
	public List<Product> findByLaptop() {
		// TODO Auto-generated method stub
		return pdao.findByLaptop();
	}
	@Override
	public File save(MultipartFile file, String path) throws IOException {
		if(!file.isEmpty()) {
			String path1 = "C:\\HomeDesktop\\ki 7\\Web\\CK\\Web_Ban_My_Pham_AngularJS_Thymeleaf\\src\\main\\resources\\static\\";
			File directory = new File(path1 + path);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			String fileName = file.getOriginalFilename();
			File f = new File(directory , fileName);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(f));
			byte[] data = file.getBytes();
			bufferedOutputStream.write(data);
			bufferedOutputStream.close();
			
			File dir = new File(request.getServletContext().getRealPath(path));
			if(!dir.exists()) {
				dir.mkdirs();
			}
			try {
				File savedFile = new File(dir, file.getOriginalFilename());
				file.transferTo(savedFile);
				return savedFile;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}


	

	

	
}
