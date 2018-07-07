package cn.yarne.com.base.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */
public class ServerListener implements ServletContextListener {
	protected final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("=================================");
		logger.info("项目[{"+arg0.getServletContext().getServletContextName()+"}]已经销毁!!!");
		logger.info("=================================");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("=================================");
		logger.info("项目[{"+arg0.getServletContext().getServletContextName()+"}]启动完成!!!");
		logger.info("=================================");
	}

}
