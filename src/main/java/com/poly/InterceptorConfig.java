package com.poly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poly.interceptor.Globallnterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	@Autowired
	Globallnterceptor globalInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/rest/**","/assets/**");
		// cái này là sẽ không hiển thị globalInterceptor lên các ip bên dưới
	}
	
	
	
}
