package com.poly.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductDao;
import com.poly.dao.ReportProductbyDayDao;
import com.poly.entity.ReportCategory;
import com.poly.entity.ReportProductbyDay;
import com.poly.entity.ReportTrademark;
import com.poly.service.ProductService;
import com.poly.service.ReportService;

@Service
public class ReportServiceImql implements ReportService {
	
	@Autowired
	ReportService reportdao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ReportProductbyDayDao reportproductbydaydao;
	
	@Override
	public List<ReportCategory> getReportCategory() {
		return productDao.getReportCategory();
	}

	@Override
	public List<ReportProductbyDay> getReportProductbyDaynoMinMax() {
		// TODO Auto-generated method stub
		return reportproductbydaydao.reportProdctByDaynoMinMax();
	}

	@Override
	public List<ReportProductbyDay> getReportProductbyDayMinMax(Date minday,Date Max) {
		// TODO Auto-generated method stub
		return reportproductbydaydao.reportProdctByDay( minday,  Max);
	}

	@Override
	public List<ReportCategory> revenueByMonth() {
		// TODO Auto-generated method stub
		return reportdao.revenueByMonth();
	}

	@Override
	public List<ReportTrademark> getReportTrademark() {
		// TODO Auto-generated method stub
		return productDao.getReportTrademark();
	}

}
