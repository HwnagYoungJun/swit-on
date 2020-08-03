package com.ssafy.switon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ssafy.switon.Interceptors.AuthInterceptor;
import com.ssafy.switon.Interceptors.CORSInterceptor;
<<<<<<< HEAD
=======
import com.ssafy.switon.Interceptors.GetRequestInterceptor;
import com.ssafy.switon.Interceptors.OtherRequestInterceptor;
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

<<<<<<< HEAD
	@Autowired
	AuthInterceptor authInterceptor;
	@Autowired
	CORSInterceptor corsInterceptor;
=======
//	@Autowired
//	AuthInterceptor authInterceptor;
	@Autowired
	CORSInterceptor corsInterceptor;
	@Autowired
	GetRequestInterceptor getInterceptor;
	@Autowired
	OtherRequestInterceptor otherInterceptor;
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(corsInterceptor)
		.addPathPatterns("/**");
		
<<<<<<< HEAD
		registry.addInterceptor(authInterceptor)
		.addPathPatterns("/accounts/info")
		.addPathPatterns("/study/create")
		.addPathPatterns("/study/**/delete")
		.addPathPatterns("/**/myqna")
		.addPathPatterns("/**/myrepository");
=======
		registry.addInterceptor(otherInterceptor)
		.addPathPatterns("/study")
		.addPathPatterns("/study/**");
		
		registry.addInterceptor(getInterceptor)
		.addPathPatterns("/**/myqna")
		.addPathPatterns("/**/myrepository");
		
//		registry.addInterceptor(authInterceptor)
//		.addPathPatterns("/accounts/info")
//		.addPathPatterns("/study/create")
//		.addPathPatterns("/study/**/delete")
//		.addPathPatterns("/**/myqna")
//		.addPathPatterns("/**/myrepository");
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf
	}
}
