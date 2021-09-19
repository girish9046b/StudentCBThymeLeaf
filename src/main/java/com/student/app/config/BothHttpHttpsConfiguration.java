//package com.student.app.config;
//import java.util.Locale;
//
//import org.apache.catalina.connector.Connector;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
////Use this class if you want to run application both on http and https
//public class BothHttpHttpsConfiguration {
//  @Bean
//  public ServletWebServerFactory servletContainer(@Value("${server.http.port}") int httpPort) {
//      Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//      connector.setPort(httpPort);
//
//      TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//      tomcat.addAdditionalTomcatConnectors(connector);
//      System.out.println("----------------BothHttpHttpsConfiguration :");
//      return tomcat;
//  }
//}
