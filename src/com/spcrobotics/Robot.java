package com.spcrobotics;

import com.spcrobotics.commands.*;
import com.spcrobotics.subsystems.*;
import com.spcrobotics.util.EventLogger;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final boolean DEBUG = true;
	
	public static Drivetrain drivetrain;
	public static GearShifter gearShifter;
	public static DrivetrainPIDDistance leftDistDrive;
	public static DrivetrainPIDDistance rightDistDrive;
	public static DrivetrainPIDSpeed leftSpeedDrive;
	public static DrivetrainPIDSpeed rightSpeedDrive;
	public static OI oi;
	
	public static EventLogger logger = null;
	private static Timer sessionTimer = null;
	private static long sessionIteration = 0;

	public void robotInit() {
		if (DEBUG) {
			System.out.println("Initializing robot");
		}
		
		RobotMap.init();
		
		drivetrain = new Drivetrain();
	
		leftDistDrive = new DrivetrainPIDDistance(
				"leftDriveDistance",
				0.0111D, 0.0D, 0.0D, 500, // TODO Move tolerance to Constants
				RobotMap.DRIVETRAIN_LEFT_ENCODER,
				RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR,
				RobotMap.DRIVETRAIN_LEFTBACK_MOTOR,
				true);
		rightDistDrive = new DrivetrainPIDDistance(
				"rightDriveDistance",
				0.0001D, 0.0D, 0.0D, 500, // TODO Move tolerance to Constants
				RobotMap.DRIVETRAIN_RIGHT_ENCODER,
				RobotMap.DRIVETRAIN_RIGHTFRONT_MOTOR,
				RobotMap.DRIVETRAIN_RIGHTBACK_MOTOR,
				false);
		
		leftSpeedDrive = new DrivetrainPIDSpeed(
				"leftDriveSpeed",
				0.0111D, 0.0D, 0.0D, 500, // TODO Move tolerance to Constants
				RobotMap.DRIVETRAIN_LEFT_ENCODER,
				RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR,
				RobotMap.DRIVETRAIN_LEFTBACK_MOTOR,
				true);
		rightSpeedDrive = new DrivetrainPIDSpeed(
				"rightDriveSpeed",
				0.008391775D, 0.0D, 0.0D, 500, // TODO Move tolerance to Constants
				RobotMap.DRIVETRAIN_RIGHT_ENCODER,
				RobotMap.DRIVETRAIN_RIGHTFRONT_MOTOR,
				RobotMap.DRIVETRAIN_RIGHTBACK_MOTOR,
				false);
		
		gearShifter = new GearShifter();
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
		leftDistDrive.stopSystem();
		rightDistDrive.stopSystem();
		leftSpeedDrive.stopSystem();
		rightSpeedDrive.stopSystem();
		
		logger.endLog();
		
		sessionTimer.reset();
		sessionIteration = 0;
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		enabledInit();
		
		leftDistDrive.startSystem();
		rightDistDrive.startSystem();
		
		new AutoPickupAndRetreat().start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		enabledPeriodic();
		
		logger.log("encoderLR_counts",
				String.valueOf(RobotMap.DRIVETRAIN_LEFT_ENCODER.get()),
				String.valueOf(RobotMap.DRIVETRAIN_RIGHT_ENCODER.get())
		);
	}

	public void teleopInit() {
		enabledInit();
		
		leftSpeedDrive.startSystem();
		rightSpeedDrive.startSystem();
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
		
		logger.log("encoderLR_counts",
				String.valueOf(RobotMap.DRIVETRAIN_LEFT_ENCODER.getRate()),
				String.valueOf(RobotMap.DRIVETRAIN_RIGHT_ENCODER.getRate())
		);
		
		// Run drivetrain for 6 seconds for data collection
		if (sessionTimer.get() < 6.0) {
			drivetrain.setAll(0.3);
		} else {
			//driveSpeedCmd.cancel();
			drivetrain.stop();
		}
	}
	
	public static double getTimerValue() {
		return sessionTimer.get();
	}
	
	public static long getSessionIteration() {
		return sessionIteration;
	}
	
}
