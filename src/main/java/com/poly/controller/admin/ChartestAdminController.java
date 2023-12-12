package com.poly.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.poly.dao.AccountDao;
import com.poly.dao.OrderDao;
import com.poly.dao.ProductDao;
import com.poly.dao.ReportDao;
import com.poly.dao.ReportProductbyDayDao;
import com.poly.entity.ReportProductbyDay;
import com.poly.service.AccountService;
import com.poly.service.ReportService;

@Controller
public class ChartestAdminController {
	@Autowired
	ProductDao pdao;
	@Autowired
	AccountService accservice;
	@Autowired
	ReportDao rDao;
	@Autowired
	OrderDao odao;
	@Autowired
	AccountDao adao;
	@Autowired
	ReportService reportdao;
	@Autowired
	ReportProductbyDayDao pReportProductbyDayDao;

	@RequestMapping("/admin/char/product1")
	public String showhome() {
		return "admin/char/chartest2";
	}

	@RequestMapping("/admin/testchar")
	@ResponseBody
	public String home(Model model,
			@RequestParam(value = "minday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date minday,
			@RequestParam(value = "maxday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxday) {
//		List<ReportProductbyDay> dataList = pReportProductbyDayDao.reportProdctByDaynoMinMax();
//		JsonArray jsonName = new JsonArray();
//		JsonArray jsonSum = new JsonArray();
//		JsonArray jsonCount = new JsonArray();
//		JsonObject json =new JsonObject();
//		dataList.forEach(data->{
//			jsonName.add(data.getName());
//			jsonSum.add(data.getSum());
//			jsonCount.add(data.getCount());
//			
//		
//		});
//		json.add("name", jsonName);
//		json.add("sum", jsonSum);
//		json.add("count", jsonCount);
		JsonArray jsonName = new JsonArray();
		JsonArray jsonSum = new JsonArray();
		JsonArray jsonCount = new JsonArray();
		JsonObject json = new JsonObject();
        try {
		if (minday == (null) && maxday == (null)) {
			List<ReportProductbyDay> dataList = pReportProductbyDayDao.reportProdctByDaynoMinMax();
			dataList.forEach(data -> {
				jsonName.add(data.getName());
				jsonSum.add(data.getSum());
				jsonCount.add(data.getCount());
			});
		} else {
			List<ReportProductbyDay> dataList = pReportProductbyDayDao.reportProdctByDay(minday, maxday);
			dataList.forEach(data -> {
				jsonName.add(data.getName());
				jsonSum.add(data.getSum());
				jsonCount.add(data.getCount());

			});
		}
		json.add("name", jsonName);
		json.add("sum", jsonSum);
		json.add("count", jsonCount);
		model.addAttribute("message", "Thống kê thành công");
		
		return json.toString() ;
        } catch (Exception e) {
			model.addAttribute("message", "Không được để trống Minday hoặc Maxday");
			return json.toString();
		} 
        
	}

}
