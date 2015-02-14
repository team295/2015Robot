package com.spcrobotics;

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
	}
	
	// Called during all non-disabled periodic methods
	/**
	 * Periodic code for enabled modes (anything but disabled: autonomous,
	 * teleop, and test) should go here.
	 *
	 * Users should override this method for code which will be called
	 * periodically at a regular rate while the robot is in any enabled mode.
	 */
	public void enabledPeriodic() {
		sessionIteration++;
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
		
		dataLogger.startLogger();
		dataLogger.sendEvent("Autonomous Started");
		
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		enabledPeriodic();
		
		if (sessionTimer.get() > 7.5) {
			drivetrain.stop();
		} else {
			drivetrain.setLeft(-0.3);
			drivetrain.setRight(0.3);
		}
	}
	@Override
	public void teleopInit() {
		enabledInit();
		dataLogger.startLogger();
		dataLogger.sendEvent("Teleop Started");
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
	
}
