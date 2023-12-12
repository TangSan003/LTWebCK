package com.poly.service;

import java.util.Date;
import java.util.List;

import com.poly.entity.ReportCategory;
import com.poly.entity.ReportProductbyDay;
import com.poly.entity.ReportTrademark;

public interface ReportService {
	
	List<ReportCategory> getReportCategory() ;
	
	List<ReportProductbyDay> getReportProductbyDaynoMinMax();
	List<ReportProductbyDay> getReportProductbyDayMinMax(Date minday , Date maxday);

	List<ReportCategory> revenueByMonth();

	List<ReportTrademark> getReportTrademark();
}
