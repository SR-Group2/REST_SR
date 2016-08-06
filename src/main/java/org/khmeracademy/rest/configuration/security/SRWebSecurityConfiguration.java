package org.khmeracademy.rest.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SRWebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier(value="ajaxAuthenticationSuccessHandler")
	private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
	
	@Autowired
	@Qualifier(value="ajaxAuthenticationFailureHandler")
	private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("seyha").password("group2123456").roles("API_DEVELOPER", "ADMIN");
		auth.inMemoryAuthentication().withUser("phanit").password("group2123456").roles("API_DEVELOPER", "ADMIN");
		auth.inMemoryAuthentication().withUser("pheara").password("group2123456").roles("API_DEVELOPER", "ADMIN");
		auth.inMemoryAuthentication().withUser("kimsie").password("group2123456").roles("API_DEVELOPER", "ADMIN");
		auth.inMemoryAuthentication().withUser("chhayheng").password("group2123456").roles("API_DEVELOPER", "ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/swagger/**").permitAll(); //hasAnyRole("API_DEVELOPER");
		http
			.formLogin()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(ajaxAuthenticationSuccessHandler)
			.failureHandler(ajaxAuthenticationFailureHandler)
			.permitAll();
		http.csrf().disable();
		http.exceptionHandling().accessDeniedPage("/access-denied");
		
		
	}
}
