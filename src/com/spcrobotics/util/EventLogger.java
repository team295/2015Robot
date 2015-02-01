package com.spcrobotics.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.spcrobotics.Constant;
import com.spcrobotics.Robot;

/**
 * A logger for robot events.
 * 
 * To use this logger, call getInstance() once and call log() to add an entry.
 * To end the log file, call endLog().
 */
public class EventLogger {
	
	private static EventLogger singleton = null;
	
	private File outputFile = null;
	private BufferedWriter outputWriter = null;
	
	private EventLogger() {
		startLog();
	}
	
	private void startLog() {
		outputFile = null;
		outputWriter = null;
		while (outputFile == null || outputFile.exists()) {
			outputFile = new File(Constant.LOGGER_LOGDIR + "log_" + System.currentTimeMillis());
		}
		
		try {
			outputWriter = new BufferedWriter(new FileWriter(outputFile));
			System.out.println("Created new log at " + outputFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static EventLogger getInstance() {
		if (singleton == null) {
			singleton = new EventLogger();
		}
		return singleton;
	}
	
	/**
	 * Adds an entry to the log file in the following format (delimited by Constant.LOGGER_DELIMITER):<br>
	 * 	- system time (ms)<br>
	 * 	- robot session uptime (s)<br>
	 * 	- robot session iteration<br>
	 * 	- event type (user-defined String)<br>
	 * 	- message tokens (user-defined String(s))<br>
	 * 
	 * @param eventType an unique identifier for type of event
	 * @param messageTokens zero or more Strings to be printed in the entry
	 */
	public void log(String eventType, String... messageTokens) {
		try {
			outputWriter.write(getLogHeader());
			
			outputWriter.write(Constant.LOGGER_DELIMITER);
			outputWriter.write(eventType);
			
			for (String s : messageTokens) {
				outputWriter.write(Constant.LOGGER_DELIMITER);
				outputWriter.write(s);
			}
			outputWriter.newLine();
			outputWriter.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the existing log file, and prepares a new log file.
	 */
	public void endLog() {
		if (Robot.getSessionIteration() < 1) {
			outputFile.delete(); // If no iterations have passed, delete log
			System.out.println("Deleted empty log at " + outputFile.getAbsolutePath());
		} else {
			System.out.println("Completed log at " + outputFile.getAbsolutePath());
		}
		
		try {
			outputWriter.flush();
			outputWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		startLog();
	}
	
	private String getLogHeader() {
		return String.valueOf(System.currentTimeMillis())
				+ Constant.LOGGER_DELIMITER + String.valueOf(Robot.getTimerValue())
				+ Constant.LOGGER_DELIMITER + String.valueOf(Robot.getSessionIteration());
	}
	
}
