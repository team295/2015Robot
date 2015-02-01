package com.spcrobotics.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.spcrobotics.Constant;
import com.spcrobotics.Robot;

/**
 * A logger for robot events.
 * To use this logger, call getInstance() once and call log() to add an entry.
 * To end the log file, call endLog().
 * <p>
 * Logs are saved as log_[datetime]_[lognumber], where datetime is the timestamp
 * during the Logger's initialization, and lognumber is incremented after every
 * log created.
 */
public class EventLogger {
	
	private static EventLogger singleton = null;
	
	private final String logPrefix;
	private int logNumber = 0;
	
	private File outputFile = null;
	private BufferedWriter outputWriter = null;
	
	private EventLogger() {
		logPrefix =
				Constant.LOGGER_LOGDIR
				+ "log_"
				+ new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) // Current time
				+ "_";
		
		startLog();
	}
	
	/*
	 * Starts a new log file.
	 */
	private void startLog() {
		outputFile = null;
		outputWriter = null;

		outputFile = new File(logPrefix + String.format("%02d", logNumber));
		
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
	 * 	- message tokens (user-defined String(s))
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

		logNumber++;
		startLog();
	}
	
	private String getLogHeader() {
		return String.valueOf(System.currentTimeMillis())
				+ Constant.LOGGER_DELIMITER + String.valueOf(Robot.getTimerValue())
				+ Constant.LOGGER_DELIMITER + String.valueOf(Robot.getSessionIteration());
	}
	
}
