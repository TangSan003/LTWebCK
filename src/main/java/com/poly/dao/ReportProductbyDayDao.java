package com.poly.dao;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import com.poly.entity.ReportProductbyDay;




public interface ReportProductbyDayDao extends JpaRepository<ReportProductbyDay, Integer> {
	

	@Query(value="select pro.Product_id , pro.Name ,SUM(odt.Price * odt.Quantity )as 'sum',SUM(odt.Quantity) as 'count'"
			+ "from OrderDetails odt join Orders ord\r\n"
			+ "	on odt.Order_id = ord.Order_id \r\n"
			+ "	join Products pro \r\n"
			+ "	on odt.Product_id = pro.Product_id \r\n"
			+ "	where ord.CreateDate Between ?1 and  ?2 and ord.Status =3 \r\n "
			+ "	Group by pro.Product_id  , pro.Name ; ",nativeQuery = true)
	List<ReportProductbyDay> reportProdctByDay(@DateTimeFormat(pattern="yyyy-MM-dd")Date MinDay , 
												@DateTimeFormat(pattern="yyyy-MM-dd")Date MaxDay );
	
	@Query(value="select pro.Product_id , pro.Name ,SUM(odt.Price * odt.Quantity ) as 'sum',Sum(odt.Quantity) as 'count' "
			+ "				from OrderDetails odt join Orders ord\r\n"
			+ "				on odt.Order_id = ord.Order_id \r\n"
			+ "			join Products pro \r\n"
			+ "			on odt.Product_id = pro.Product_id \r\n"
			+ "	where  ord.Status =3 \r\n "
			+ "				Group by pro.Product_id  , pro.Name  ",nativeQuery = true)
	List<ReportProductbyDay> reportProdctByDaynoMinMax();
	@Query(value="select pro.Product_id , pro.Name ,SUM(odt.Price * odt.Quantity ) as 'sum',Sum(odt.Quantity) as 'count' "
			+ "				from OrderDetails odt join Orders ord\r\n"
			+ "				on odt.Order_id = ord.Order_id \r\n"
			+ "			join Products pro \r\n"
			+ "			on odt.Product_id = pro.Product_id \r\n"
			+ "	where  ord.Status =3 \r\n "
			+ "				Group by pro.Product_id  , pro.Name "
			+ "				order by Sum(odt.Quantity) desc limit 10",nativeQuery = true)
	List<ReportProductbyDay> reportProdctTop10();
	@Query(value="select pro.Product_id , pro.Name ,SUM(odt.Price * odt.Quantity ) as 'sum',Sum(odt.Quantity) as 'count' "
			+ "				from OrderDetails odt join Orders ord\r\n"
			+ "				on odt.Order_id = ord.Order_id \r\n"
			+ "			join Products pro \r\n"
			+ "			on odt.Product_id = pro.Product_id \r\n"
			+ "	where  ord.Status =3 \r\n "
			+ "				Group by pro.Product_id  , pro.Name  limit 10",nativeQuery = true)
	List<ReportProductbyDay> reportProdctByDaynoMinMax1();
}
