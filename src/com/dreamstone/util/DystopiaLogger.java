package com.dreamstone.util;

import java.util.logging.Logger;

import com.dreamstone.graphics.DystopiaFrame;

public final class DystopiaLogger {
	private static Logger logger = Logger.getLogger(DystopiaFrame.class.getName());
	
	public static Logger getLogger() {
		return logger;
	}
}
