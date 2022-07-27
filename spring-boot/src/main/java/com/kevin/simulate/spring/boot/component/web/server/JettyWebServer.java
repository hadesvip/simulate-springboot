package com.kevin.simulate.spring.boot.component.web.server;

import java.util.logging.Logger;
import org.springframework.web.context.WebApplicationContext;

/**
 * jetty web容器
 *
 * @author kevin
 */
public class JettyWebServer implements WebServer {

  @Override
  public void start(WebApplicationContext webApplicationContext) {
    Logger.getGlobal().info("==Jetty Web Server==");
  }
}
