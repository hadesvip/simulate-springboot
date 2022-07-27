package com.kevin.simulate.spring.boot.configuration;

import com.kevin.simulate.spring.boot.annotation.ConditionalOnClass;
import com.kevin.simulate.spring.boot.component.web.server.JettyWebServer;
import com.kevin.simulate.spring.boot.component.web.server.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kevin
 */
@Configuration
public class WebServiceAutoConfiguration implements AutoConfiguration {

  @Bean
  @ConditionalOnClass("org.apache.catalina.startup.Tomcat")
  public TomcatWebServer tomcatWebServer() {
    return new TomcatWebServer();
  }

  @Bean
  @ConditionalOnClass("org.eclipse.jetty.server.Server")
  public JettyWebServer jettyWebServer() {
    return new JettyWebServer();
  }
}
