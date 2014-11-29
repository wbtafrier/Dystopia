package com.escape.util;

import java.util.logging.Logger;

import com.escape.launch.Escape;

public class LoggingUtil {
	private static Logger logger = Logger.getLogger(Escape.class.getName());
	
	public static Logger getLogger() {
		return logger;
	}
}
