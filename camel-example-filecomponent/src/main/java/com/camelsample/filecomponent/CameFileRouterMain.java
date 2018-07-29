package com.camelsample.filecomponent;

import java.io.InputStream;
import java.util.Properties;

import org.apache.camel.main.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CameFileRouterMain {
	private static final String DESTINATIONPATH = "destinationpath";
	private static final String SRCPATH = "srcpath";
	final static Logger logger = LogManager.getLogger(CameFileRouterMain.class);

	public static void main(String[] args) throws Exception {
		Properties prop = new Properties();
		String filename = "config.properties";

		try(InputStream input = CameFileRouterMain.class.getClassLoader().getResourceAsStream(filename)) {
			if(input==null){
				logger.error("Sorry, unable to find {}", filename);
				return;
			}

			prop.load(input);
			Main main = new Main();
			main.addRouteBuilder(new CamelFileRouter(prop.getProperty(SRCPATH), prop.getProperty(DESTINATIONPATH)));
			main.run();
		}
	}
}
