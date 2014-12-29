package com.dreamstone.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.dreamstone.graphics.DystopiaFrame;

public final class DystopiaLogger {
	private static Logger logger = Logger.getLogger(DystopiaFrame.class.getName());
	
	public static void logInfo(Object obj) {
		logger.log(Level.INFO, obj.toString());
	}
	
	public static void logSevere(Object obj) {
		logger.log(Level.SEVERE, obj.toString());
	}
	
	public static void logWarning(Object obj) {
		logger.log(Level.WARNING, obj.toString());
	}
	
	public static void logDebug(Object obj) {
		logger.log(Level.INFO, "[DEBUG] " + obj.toString());
	}
}
