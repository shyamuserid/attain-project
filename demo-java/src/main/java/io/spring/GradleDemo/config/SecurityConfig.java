package io.spring.GradleDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic()
		.and().authorizeRequests()
		.antMatchers("/index.html", 
					"/",
					"/home",
					"/login").permitAll()
		.anyRequest().authenticated()
		.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/*.js",
									"/*.js.map",
									"/*.css",
									"/index.html",
									"/favicon.ico");
	}
	
}
