package com.ssafy.switon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ssafy.switon.Interceptors.AuthInterceptor;
import com.ssafy.switon.Interceptors.CORSInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	AuthInterceptor authInterceptor;
	@Autowired
	CORSInterceptor corsInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(corsInterceptor)
		.addPathPatterns("/**");
		
		registry.addInterceptor(authInterceptor)
		.addPathPatterns("/accounts/info")
		.addPathPatterns("/study/create")
		.addPathPatterns("/study/**/delete")
		.addPathPatterns("/**/myqna")
		.addPathPatterns("/**/myrepository");
	}
}
