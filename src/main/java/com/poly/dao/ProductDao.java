package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.entity.Category;
import com.poly.entity.Product;

import com.poly.entity.ReportCategory;
import com.poly.entity.ReportTrademark;

public interface ProductDao extends JpaRepository<Product, Integer>{
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(Integer cid);

	@Query("SELECT p FROM Product p WHERE p.trademark.id=?1")
	List<Product> findByTrademarkId(Integer tid);
	
//	giảm giá
	@Query("SELECT p FROM Product p WHERE p.Distcount > 0")
	List<Product> findByDis();
//  cũ nhất
	@Query(value = "SELECT p FROM Product p WHERE p.Lastest = true")
	List<Product> findByLatest();
//  đặt biệt
	
		@Query("SELECT p FROM Product p WHERE p.Special = true")
	List<Product> findBySpecial();

		@Query("SELECT p FROM Product p "
				+ " WHERE p.Name LIKE %:kw% OR p.category.name LIKE %:kw%")
		List<Product> findByKeywords(@Param("kw") String keywords);

		@Query("SELECT new ReportCategory(o.category.Category_id,o.category.name, sum(o.Unit_price), count(o)) "
				+ " FROM Product o "
				+ " GROUP BY o.category.Category_id ,o.category.name"
				+ " ORDER BY sum(o.Unit_price) DESC")
		List<ReportCategory> getReportCategory();
		
		@Query("SELECT p FROM Product p WHERE p.Distcount > 0")
		List<Product> findByAllDis();
		
		@Query("SELECT p FROM Product p WHERE p.Special = true")
		List<Product> findByAllSpe();
		
		@Query(value = "SELECT p FROM Product p WHERE p.Lastest = true")
		List<Product> findByAllLat();
		
		@Query("SELECT new ReportTrademark(o.trademark.Trademark_id,o.trademark.name, sum(o.Unit_price), count(o)) "
				+ " FROM Product o "
				+ " GROUP BY o.trademark.Trademark_id ,o.trademark.name"
				+ " ORDER BY sum(o.Unit_price) DESC")
		List<ReportTrademark> getReportTrademark();
		
		@Query(value="SELECT  * FROM Products p ORDER BY p.Product_date DESC limit 10",nativeQuery = true)
		List<Product> getTop10();
		
		@Query(value="SELECT * FROM Products p ORDER BY p.Unit_price DESC",nativeQuery = true)
		List<Product> getDesc();
		
		@Query(value="SELECT * FROM Products p ORDER BY p.Unit_price ASC",nativeQuery = true)
		List<Product> getAsc();
		
		@Query(value="select * from Products where Unit_price between ?1 and ?2 and Color like %?3% and Material like %?4% and Size like %?5% ",nativeQuery = true)
		List<Product> find(@Param("MinPrice") Integer unit_price, @Param("MaxPrice") Integer unit_price1 ,
				@Param("Color") String color, @Param("Material") String material, @Param("Size") String size  );
	
		@Query(value="select * from Products where Unit_price between ?1 and ?2 and Category_id like %?3% and Trademark_id like %?4% "
				+ "and Color like %?5% and Material like %?6% and Size like  %?7% ", nativeQuery = true)
		List<Product> findByAllKeyWord(
				@Param("MinPrice") Integer unit_price, @Param("MaxPrice") Integer unit_price1   ,
					@Param("Category_id") String Category_id , @Param("Trademark_id") String Trademark_id , @Param("Color") String Color , 
					@Param("Material") String Material , @Param("Size") String Size);
		
		
		  @Query(value="select * from Products where Category_id like '1'", nativeQuery
		  = true) List<Product> findByLaptop();
		 
		
		@Query(value = "select * from Products where Product_id like %:kw% or \r\n"
				+ " Name like %:kw%  " , nativeQuery = true)
		List<Product> finbyIdOrName(@Param("kw") String keywords);
		
		@Query(value="select * from Products where Unit_price between ?1 and ?2 and Category_id like %?3% and Trademark_id like %?4% "
				+ "and  Status like %?5% and Color like %?6% and Material like %?7% and Size like  %?8% ", nativeQuery = true)
		List<Product> findByAllKeyWordAdmin(@Param("MinPrice") Integer unit_price, @Param("MaxPrice") Integer unit_price1   ,
				@Param("Category_id") String Category_id , @Param("Trademark_id") String Trademark_id , 
				@Param("Status") String Status , @Param("Color") String Color , 
				@Param("Material") String Material , @Param("Size") String Size);
		
}
