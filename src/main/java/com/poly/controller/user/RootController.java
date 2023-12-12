package com.poly.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.ProductDao;
import com.poly.entity.ReportCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.ui.Model;	
@Controller
@RequestMapping("/report")
public class RootController {
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	
	@Autowired
	ProductDao DAO;
	
    @GetMapping
    public String getPieChart(Model model) {
//    	List<ReportCategory>  report =  DAO.getReportCategory();
//        Map<String, Double > graphData = new LinkedHashMap<>();
//
//        for(int i=0 ; i<report.size();i++) {
//        	graphData.put(report.get(i).getCategory_name(),report.get(i).getSum());
//        }
//        model.addAttribute("chartData", graphData);
        return "user/char/index3";
    }
  
   
}
