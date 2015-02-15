package com.spcrobotics;

import com.spcrobotics.commands.AutoPickupAndDrive;
import com.spcrobotics.subsystems.Claw;
import com.spcrobotics.subsystems.DataLogger;
import com.spcrobotics.subsystems.Drivetrain;
import com.spcrobotics.subsystems.GearShifter;
import com.spcrobotics.subsystems.Lift;
import com.spcrobotics.subsystems.PIDLift;
import com.spcrobotics.util.EventLogger;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {
	
	// Subsystems and operator interface declaration
	public static Drivetrain drivetrain;
	public static GearShifter gearShifter;
	public static Lift lift;
	public static PIDLift pidLift;
	public static Claw claw;
	public static OI oi;
	public static DataLogger dataLogger;
	
	// Logger and logger utilities
	public static EventLogger logger = null;
	private static Timer sessionTimer = null;
	private static long sessionIteration = 0;
	
	// State vars
	private static boolean compressorWasOn = false;
	
	public void robotInit() {
		// Initialize all robot components
		RobotMap.init();

		// Initialize all subsystems and operator interface
		drivetrain = new Drivetrain();
		gearShifter = new GearShifter();
		lift = new Lift();
		pidLift = new PIDLift();
		claw = new Claw();
		oi = new OI();
		dataLogger = new DataLogger();
		
		// Start logger implicitly and initialize timer (not started yet)
		logger = EventLogger.getInstance();
		sessionTimer = new Timer();
	}
	
	/**
	 * Initialization code for enabled modes (anything but disabled: autonomous,
	 * teleop, and test) should go here.
	 *
	 * Users should override this method for initialization code which will be
	 * called each time the robot enters any enabled mode.
	 */
	public void enabledInit() {
		sessionTimer.start();
		dataLogger.startLogger();
	}
	
	/**
	 * Periodic code for enabled modes (anything but disabled: autonomous,
	 * teleop, and test) should go here.
	 *
	 * Users should override this method for code which will be called
	 * periodically at a regular rate while the robot is in any enabled mode.
	 */
	public void enabledPeriodic() {
		sessionIteration++;
		
		// Periodic logging
		logCompressorInfo();
	}

	@Override
	public void disabledInit() {
		drivetrain.stop();
		// End the current log and prepare a new one for the next enable
		logger.endLog();
		dataLogger.stopLogger();
		// Reset session stats for logger
		sessionTimer.reset();
		sessionIteration = 0;

	}
	
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousInit() {
		enabledInit();
		logToAll("startAutonomous");

		new AutoPickupAndDrive().start();
}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		enabledPeriodic();
	}

	@Override
	public void teleopInit() {
		enabledInit();
		logToAll("startTeleop");
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		enabledPeriodic();
	}
	
	@Override
	public void testInit() {
		enabledInit();
	}
	
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		enabledPeriodic();
		logToAll("startTest");
		
		System.out.println("liftpos: " + lift.getPosition());
	}
	
	/**
	 * @return session timer's current value (time since enabling) in seconds
	 */
	public static double getTimerValue() {
		return sessionTimer.get();
	}
	
	/**
	 * @return current session's iteration (number of times periodic method has
	 *         been called)
	 */
	public static long getSessionIteration() {
		return sessionIteration;
	}
	
	/**
	 * Logs information to all logging agents. Currently, it logs to the EventLogger and the
	 * DataLogger.
	 * 
	 * @param message the primary message to be logged
	 * @param tokens additional string(s) that will be logged in the EventLogger
	 */
	public static void logToAll(String message, String... tokens) {
		logger.log(message, tokens);
		dataLogger.sendEvent(message);
	}
	
	public static void logCompressorInfo() {
		// Check for changes in compressor state, and log appropriately
		boolean compressorOn = RobotMap.COMPRESSOR.enabled();
		if (compressorOn && !compressorWasOn) {
			logToAll("compressorOn");
		} else if (!compressorOn && compressorWasOn) {
			logToAll("compressorOff");
		}
		compressorWasOn = compressorOn;
		
		// Log compressor current
		logger.log("compressorCurrent",
				String.valueOf(RobotMap.COMPRESSOR.getCompressorCurrent()));
	}
	
}
