package com.cx.spring.struts2.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application Lifecycle Listener implementation class SpringServletContextListener
 *
 */
public class SpringServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SpringServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	//1.获取Spring配置文件的名称
    	ServletContext servletContext = arg0.getServletContext();
    	String config = servletContext.getInitParameter("configLocation");
        //1.创建IOC容器
    	ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
    	//2.把IOC容器放在ServletContext的一个属性中
    	servletContext.setAttribute("ApplicationContext", ctx);
    }
	
}
