package mywebprj.test.com.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//log4j需要引入的包
//commons-logging.jar
//log4j-1.2.11.jar

public class Log4jTest {
	public static void main(String[] args) {
		Log logger = LogFactory.getLog(Log4jTest.class);
		logger.debug("debug");
		logger.error("error");
		logger.info("info");
		logger.fatal("fatal");
		logger.warn("warn");
		
	}

}
