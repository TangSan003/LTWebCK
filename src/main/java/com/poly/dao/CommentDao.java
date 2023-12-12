package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Comment;



public interface CommentDao extends JpaRepository<Comment, Integer>{
	@Query("select c from Comment c where c.product.Product_id=?1 order by c.Comment_id desc")
	List<Comment> findbyProductId(Integer product_id);
}
