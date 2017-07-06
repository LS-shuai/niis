package com.tools;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {
	private static Logger log = Logger.getLogger(Server.class);

	public static void main(String[] args) throws Exception {
		log.info("服务开始启动......");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		log.info("服务启动结束......");
	}

}
