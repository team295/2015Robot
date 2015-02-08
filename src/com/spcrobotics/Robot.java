package com.spcrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.spcrobotics.subsystems.*;
import com.spcrobotics.util.EventLogger;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static GearShifter gearShifter;
	public static Lift lift;
	public static PIDLift pidLift;
	public static Claw claw;
	public static OI oi;
	
	public static EventLogger logger = null;
	private static Timer sessionTimer = null;
	private static long sessionIteration = 0;
	
	public void robotInit() {
		RobotMap.init();
		
		drivetrain = new Drivetrain();
		gearShifter = new GearShifter();
		lift = new Lift();
		pidLift = new PIDLift();
		claw = new Claw();
		oi = new OI();
		
		logger = EventLogger.getInstance();
		sessionTimer = new Timer();

	}

	public void enabledInit() {
		sessionTimer.start();
	}
	
	public void enabledPeriodic() {
		sessionIteration++;
	}
	
	public void disabledInit() {
		drivetrain.stop();
		
		logger.endLog();
		
		sessionTimer.reset();
		sessionIteration = 0;
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		enabledInit();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		enabledPeriodic();
	}

	public void teleopInit() {
		enabledInit();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		enabledPeriodic();
		
		SmartDashboard.putNumber("left_encoder_count", RobotMap.DRIVETRAIN_LEFT_ENCODER.get());
		SmartDashboard.putNumber("right_encoder_count", RobotMap.DRIVETRAIN_RIGHT_ENCODER.get());
	}
	
	public void testInit() {
		enabledInit();
	}

	public void testPeriodic() {
		LiveWindow.run();
		enabledPeriodic();
	}
	
	public static double getTimerValue() {
		return sessionTimer.get();
	}
	
	public static long getSessionIteration() {
		return sessionIteration;
	}
	
}
