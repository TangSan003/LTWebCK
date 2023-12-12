package com.poly.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.poly.dao.CommentDao;
import com.poly.entity.Account;
import com.poly.entity.Comment;

import com.poly.service.CommentService;


@Service
public class CommentServiceImql implements CommentService {
	
	@Autowired
	CommentDao dao;
	
	@Autowired
	HttpServletRequest request;

	@Override
	public File save(MultipartFile file, String path) throws IOException {
		if(!file.isEmpty()) {
			String path1 = "D://A_FPT POLYTECHNIC//Git//ASM_Java6//src//main//resources//static";
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

	@Override
	public Comment Create(Comment cmt) {
		String username = request.getRemoteUser();
		Account acc = new Account();
		acc.setUsername(username);
		cmt.setAccount(acc);
		return dao.save(cmt);
	}

	@Override
	public Comment Update(Comment cmt) {
		return dao.save(cmt);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Comment> findbyProduct_Id(Integer product_id) {
		return dao.findbyProductId(product_id);
	}

	
}
