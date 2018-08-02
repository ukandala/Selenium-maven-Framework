package com.selenium.maven;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class TestLog {
	
	static Logger log =  LogManager.getLogger(TestLog.class.getName());
	
	@Test
	public static void logger() {
		log.info("this is for test");
		log.error("testing error");
	}

}
