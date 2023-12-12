package com.poly;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;

import com.poly.entity.Account;
import com.poly.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder pe;
	@Autowired
	HttpServletRequest request;
	@Override
	protected void configure( AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(Username -> {
			try {
				// Kiểm tra tài khoản trong data
				Account user = accountService.findById(Username);
				// Kiểm tra pass 
				String password = pe.encode(user.getPassword());
				// Kiểm tra quyền 
				String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getRole_id())
						.collect(Collectors.toList()).toArray(new String[0]);
				// Kiểm tra trạng thái tài khoản trong data
				if(user.getActive() == true) {
					return User.withUsername(Username).password(password).roles(roles).build();
					}
					else {
					request.setAttribute("message", "Tài khoản chưa kích hoạt");
					return null;
					}
				
			} catch (Exception e) {
				throw new UsernameNotFoundException(Username + "not found!");
			}
		});

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// phân quyền hệ thống
		http.authorizeRequests()
		.antMatchers("/order/**").authenticated()
		.antMatchers("/favolist/**","/thanhtoanonline/**").authenticated()
		.antMatchers("/contact-us/**").authenticated()
		 .antMatchers("/admin/home/index").hasAnyRole("STAF")
		 .antMatchers("/admin/product/**").hasAnyRole("STAF")
		 .antMatchers("/admin/order/**").hasAnyRole("STAF")
		 .antMatchers("/admin/category/**").hasAnyRole("STAF")
		 .antMatchers("/admin/trademark/**", "/admin/map/**", "/admin/post/**").hasAnyRole("STAF")
		 .antMatchers("/admin/char/**").hasRole("STAF")
		 .antMatchers("/admin/authority/**").hasRole("STAF")
		 .antMatchers("/admin/account/**").hasRole("STAF")
		.anyRequest().permitAll();
		// đăng nhập
		http.formLogin().loginPage("/security/login/form")
		.loginProcessingUrl("/security/login")
		.defaultSuccessUrl("/home/index", false)
		.failureUrl("/security/login/erorr");
		http.rememberMe().tokenValiditySeconds(86400);
		http.exceptionHandling().accessDeniedPage("/security/unauthoried");
		//đăng xuất
		http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");
	
		
		// đăng nhập bằng mạng xã hội gg fb
		http.oauth2Login()
		.loginPage("/security/login/form")
		//đăng nhập fb gg thành công trả về true 
		.defaultSuccessUrl("/security2/login/success",true)
		//nếu sai thì trả về false và chuyển sang trang login/error
		.failureUrl("/security/login/error")
		.authorizationEndpoint()
		//đường dẫn liên của gg và fb trang login 
			.baseUri("/security2/authrization");
	}


	

	/* Mã hoá mật khẩu */
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
	}

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String fullname = oauth2.getPrincipal().getAttribute("name");
		
		String name = oauth2.getName();
		//lấy email làm username
		//String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		UserDetails user = User.withUsername(name).password(pe.encode(password)).roles("GUEST").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		//thay đổi thông tin đăng nhập 
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

}
