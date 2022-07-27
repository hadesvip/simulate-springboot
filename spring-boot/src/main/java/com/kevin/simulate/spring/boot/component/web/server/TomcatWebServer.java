package com.kevin.simulate.spring.boot.component.web.server;

import java.util.logging.Logger;
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
import org.springframework.web.servlet.DispatcherServlet;

/**
 * tomcat web容器
 *
 * @author kevin
 */
public class TomcatWebServer implements WebServer {

  @Override
  public void start(WebApplicationContext webApplicationContext) {
    Logger.getGlobal().info("==Tomcat Web Server==");

    Tomcat tomcat = new Tomcat();
    Server server = tomcat.getServer();
    Service service = server.findService("Tomcat");

    Connector connector = new Connector();
    connector.setPort(8081);

    Engine engine = new StandardEngine();
    engine.setDefaultHost("localhost");

    Host host = new StandardHost();
    host.setName("localhost");

    String contextPath = "";
    Context context = new StandardContext();
    context.setPath(contextPath);
    context.addLifecycleListener(new Tomcat.FixContextListener());

    host.addChild(context);
    engine.addChild(host);

    service.setContainer(engine);
    service.addConnector(connector);

    Tomcat.addServlet(context, "dispatcher", new DispatcherServlet(webApplicationContext));
    context.addServletMappingDecoded("/*", "dispatcher");

    try {
      tomcat.start();
    } catch (LifecycleException e) {
      e.printStackTrace();
    }
  }
}
