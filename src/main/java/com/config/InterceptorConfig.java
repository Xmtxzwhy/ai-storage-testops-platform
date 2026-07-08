package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.interceptor.AuthorizationInterceptor;

import java.io.File;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{

	@Bean
	public AuthorizationInterceptor getAuthorizationInterceptor() {
		return new AuthorizationInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getAuthorizationInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/static/**", "/zhixingrenwu/runTestCase","/upload/**","/mysqldump","/springbootarkc6v1u/**");
		super.addInterceptors(registry);
	}

	/**
	 * springboot 2.0配置WebMvcConfigurationSupport之后，会导致默认配置被覆盖，要访问静态资源需要重写addResourceHandlers方法
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String projectRoot = System.getProperty("user.dir");
		String uploadDir = projectRoot + "/upload/";
		String externalPath = "E:/Mywork/TestManagement/springbootarkc6v1u(run success)/";
		registry.addResourceHandler("/springbootarkc6v1u/**")
				.addResourceLocations("file:" + externalPath);

		// 静态资源路径映射
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/resources/")
				.addResourceLocations("classpath:/static/")
				.addResourceLocations("classpath:/admin/")
				.addResourceLocations("classpath:/front/")
				.addResourceLocations("classpath:/front-pc/")
				.addResourceLocations("classpath:/public/");

		super.addResourceHandlers(registry);
		// 在 addResourceHandlers 方法开头添加日志
		System.out.println("Project root: " + System.getProperty("user.dir"));
		System.out.println("Upload dir: " + uploadDir);
		System.out.println("External Path: " + externalPath);
	}

}
