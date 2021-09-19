package com.student.app;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//@EnableSwagger2
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement // we can automatically roll back a transaction if there is an exception 
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})

//@PropertySource("file:d://logs//application1.properties")
//@EnableAutoConfiguration//(excludeName = {"loginController"})
//@ComponentScan({"com.student.app.controller","com.student.app"})
public class StudentCbApplication extends SpringBootServletInitializer {//to enable the war file generation , to deploy in external tomcat

	public static void main(String[] args) {
		 System.out.println("..StudentCbApplicationStudentCbApplication..........  ");
		ApplicationContext ctx = SpringApplication.run(StudentCbApplication.class, args);
		String[] beanNames = ctx.getBeanDefinitionNames();
        
        Arrays.sort(beanNames);
 
        for (String beanName : beanNames) {
            System.out.println("..beanName......11....  "+beanName);
        }
        
//    	final ConfigurableApplicationContext configurableApplicationContext = SpringApplication
//    			.run(StudentCbApplication.class, args);
    }
	
	//To allow cross origin request access
//	 @SuppressWarnings("deprecation")
//	@Bean
//	    public WebMvcConfigurer corsConfigurer() {
//	        return new WebMvcConfigurerAdapter() {
//	            @Override
//	            public void addCorsMappings(CorsRegistry registry) {
//
//	                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","PUT", "DELETE"); // for /** means all mapping URL, and * for all domain
//	                //registry.addMapping("/api/**").allowedOrigins("http://www.example.com").allowedMethods("PUT", "DELETE");
//	            }
//	        };
//	    }
	
//	@Bean
//	   public Docket productApi() {
//	      return new Docket(DocumentationType.SWAGGER_2).select()
//	         .apis(RequestHandlerSelectors.basePackage("com.student.app.rest.restapi")).build();
//	   }
	
//	 @Override
//	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//	        return builder.sources(StudentCbApplication.class);
//	    }

	 
	 //Register AdminInfoListener	
//	   @Bean
//	   public ServletListenerRegistrationBean<CouchbaseBucketManager> adminInfoListener() {
//		   ServletListenerRegistrationBean<CouchbaseBucketManager> listenerRegBean = new ServletListenerRegistrationBean<>();
//		   listenerRegBean.setListener(new CouchbaseBucketManager());
//		   System.out.println("..CouchbaseBucketManagerCouchbaseBucketManager..........  ");
//		   return listenerRegBean;
//	   }  
	   
	 
//	 @Bean	
//	   public ServletRegistrationBean<HttpServlet> stateServlet() {
//		   ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
//		   servRegBean.setServlet(new RollbackCouchbase());
//		   servRegBean.addUrlMappings("/RollbackCouchbase/*");
//		   servRegBean.setLoadOnStartup(1);
//		   return servRegBean;
//	   }   
//	 
	 
//	 @Bean
//	    public MessageSource messageSource() {
//	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//	        messageSource.setBasename("classpath:message");
//	        messageSource.setDefaultEncoding("UTF-8");
//	        System.out.println("---messageSourcemessageSource--------------"+messageSource.getMessage("login.fail",null,Locale.ENGLISH));
//	        return messageSource;
//	    }
//
//	    @Bean
//	    public LocalValidatorFactoryBean validator() {
//	        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//	        bean.setValidationMessageSource(messageSource());
//	        return bean;
//	    }

//	
//	@Bean
//	   public MessageSource messageSource() {
//	      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//	      messageSource.setBasename("classpath:messages");
//	      messageSource.setDefaultEncoding("UTF-8");
//	      return messageSource;
//	   }
//	
//	@Bean
//	 public LocalValidatorFactoryBean validator(MessageSource messageSource) {
//	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//	    bean.setValidationMessageSource(messageSource);
//	    return bean;
//	 }
//	
//	@Bean
//	 public LocaleResolver localeResolver() {
//	    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//	    localeResolver.setDefaultLocale(Locale.US);
//	    return localeResolver;
//	 }
//
//	 @Bean
//	 public LocaleChangeInterceptor localeChangeInterceptor() {
//	    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//	    localeChangeInterceptor.setParamName("lang");
//	    return localeChangeInterceptor;
//	 }
//
////	 @Override
////	 public void addInterceptors(InterceptorRegistry registry) {
////	   registry.addInterceptor(localeChangeInterceptor());
////	 }
}
