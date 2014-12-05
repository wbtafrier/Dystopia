package com.dreamstone.util;

import java.util.logging.Logger;
import com.dreamstone.component.EscapeFrame;

public final class EscapeLogger {
	private static Logger logger = Logger.getLogger(EscapeFrame.class.getName());
	
	public static Logger getLogger() {
		return logger;
	}
}
