package com.kevin.simulate.spring.boot;

import com.kevin.simulate.spring.boot.component.web.server.WebServer;
import java.util.Map;
import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author kevin
 */
public class SpringApplication {

  public static void run(Class<?> clazz) {
    //启动spring容器
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    applicationContext.register(clazz);
    applicationContext.refresh();
    //启动tomcat
//    startTomcat(applicationContext);
    WebServer webServer = getWebServer(applicationContext);
    webServer.start(applicationContext);
  }


  public static WebServer getWebServer(WebApplicationContext webApplicationContext) {
    Map<String, WebServer> webServerMap = webApplicationContext.getBeansOfType(WebServer.class);
    if (webServerMap.isEmpty()) {
      throw new IllegalStateException();
    }
    if (webServerMap.size() > 1) {
      throw new IllegalStateException();
    }
    //返回唯一的一个
    return webServerMap.values().stream().findFirst().get();
  }


}
