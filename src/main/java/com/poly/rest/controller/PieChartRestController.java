package com.poly.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.ReportTrademark;
import com.poly.dao.ReportDao;
import com.poly.entity.Report;
import com.poly.entity.ReportCategory;
import com.poly.entity.ReportProductbyDay;
import com.poly.service.ReportService;


@RestController
public class PieChartRestController {
	
	
	
	@Autowired
	ReportService reportdao;
	@Autowired
	ReportDao rdeDao;
	
	@GetMapping("/getdata")
    public ResponseEntity<?> getPieChart() {
		List<Report>  report =  rdeDao.inventoryByCategory();
       
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
	@GetMapping("/getdata1")
    public ResponseEntity<?> getPieChart1() {
		List<Report>  report =  rdeDao.inventoryByTrarkmark();
       
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
	
	
	
	
	@GetMapping("/getDatareport")
	public ResponseEntity<?> getDataChartReport(Model model,@RequestParam(value= "minday" , required = false)@DateTimeFormat(pattern="yyyy-MM-dd") Date minday,
    		@RequestParam(value = "maxday", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") Date maxday){
		try {
			if (minday == null && maxday ==null) {
				List<ReportProductbyDay>  report =  reportdao.getReportProductbyDaynoMinMax();
		        return new ResponseEntity<>(report, HttpStatus.OK);
			}
			else {
				List<ReportProductbyDay>  report =  reportdao.getReportProductbyDayMinMax(minday , maxday);
				 return new ResponseEntity<>(report, HttpStatus.OK);
			}
		} catch (Exception e) {
			model.addAttribute("message", "Không được để trống Minday hoặc Maxday ----> trả về dữ liệu bản đầu ");
			List<ReportProductbyDay>  report =  reportdao.getReportProductbyDaynoMinMax();
	        return new ResponseEntity<>(report, HttpStatus.OK);
		}
	}
}
