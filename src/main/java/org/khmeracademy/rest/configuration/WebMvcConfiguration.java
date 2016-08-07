package org.khmeracademy.rest.configuration;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mangofactory.swagger.plugin.EnableSwagger;

@Configuration
@MapperScan("org.khmeracademy.rest.repositories")
@EnableSwagger
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{
	
	@Autowired 
	private DataSource dataSource;
	
	
	@Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

 
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory;
    }

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/401").setViewName("/error/401");
		registry.addViewController("/swagger").setViewName("/swagger");
		registry.addViewController("/access-denied").setViewName("/error/access-denied");
		
		
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET","POST","DELETE","PUT","OPTIONS","PATCH")
				.allowedOrigins("*");
	}
	
}
