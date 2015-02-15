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
	
	public static DrivePIDSpeed leftTeleopDriveCommand = null;
	public static DrivePIDSpeed rightTeleopDriveCommand = null;
	
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
				0.00099D, 0.000D, 0.000D, 50, // TODO Move tolerance to Constants
				RobotMap.DRIVETRAIN_LEFT_ENCODER,
				RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR,
				RobotMap.DRIVETRAIN_LEFTBACK_MOTOR,
				true);
		rightDistDrive = new DrivetrainPIDDistance(
				"rightDriveDistance",
				0.0012D, 0.0D, 0.0D, 50, // TODO Move tolerance to Constants
				RobotMap.DRIVETRAIN_RIGHT_ENCODER,
				RobotMap.DRIVETRAIN_RIGHTFRONT_MOTOR,
				RobotMap.DRIVETRAIN_RIGHTBACK_MOTOR,
				false);
		
		leftSpeedDrive = new DrivetrainPIDSpeed(
				"leftDriveSpeed",
				0.00011D, 0.000035D,0.000004D, 100, // TODO Move tolerance to Constants
				RobotMap.DRIVETRAIN_LEFT_ENCODER,
				RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR,
				RobotMap.DRIVETRAIN_LEFTBACK_MOTOR,
				true);
		rightSpeedDrive = new DrivetrainPIDSpeed(
				"rightDriveSpeed",
				0.000011D, 0.00002D, 0.000002D, 100, // TODO Move tolerance to Constants
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
		// Turns off motors
		drivetrain.stop();

		// Disables PID and turns off motors
		leftDistDrive.stopSystem();
		rightDistDrive.stopSystem();
		
		// Disables PID
		if (leftTeleopDriveCommand != null) {
			leftTeleopDriveCommand.cancel();
			leftTeleopDriveCommand = null;
		}
		if (rightTeleopDriveCommand != null) {
			rightTeleopDriveCommand.cancel();
			rightTeleopDriveCommand = null;
		}
		
		// Ends current log and begins a new one; resets run statistics
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
		System.out.println("Start left");
//		rightDistDrive.startSystem();
		
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		enabledPeriodic();

		
	}

	public void teleopInit() {
		enabledInit();
		
		leftTeleopDriveCommand = new DrivePIDSpeed(leftSpeedDrive);
		rightTeleopDriveCommand = new DrivePIDSpeed(rightSpeedDrive);
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		enabledPeriodic();
		
		SmartDashboard.putNumber("left_encoder_count", RobotMap.DRIVETRAIN_LEFT_ENCODER.get());
		SmartDashboard.putNumber("right_encoder_count", RobotMap.DRIVETRAIN_RIGHT_ENCODER.get());
	}
	
	public void testInit() {
		enabledInit();
//		leftSpeedDrive.startSystem();
//		rightSpeedDrive.startSystem();
		
		leftDistDrive.startSystem();
		rightDistDrive.startSystem();
		
	
	}

	public void testPeriodic() {
		Scheduler.getInstance().run();
		LiveWindow.run();
		enabledPeriodic();
		
//		logger.log("encoderLR_counts",
//				String.valueOf(RobotMap.DRIVETRAIN_LEFT_ENCODER.getRate()),
//				String.valueOf(RobotMap.DRIVETRAIN_RIGHT_ENCODER.getRate())
//		);
		System.out.println(
				"Session time = " + sessionTimer.get() 
				+"LeftSetpoint = " 
				+ leftDistDrive.getSetpoint()
				+"rightSetpoint = " 
				+ rightDistDrive.getSetpoint()
				+ " Right Count = " 
				+ RobotMap.DRIVETRAIN_RIGHT_ENCODER.get()
//				+ " Right Rate = " 
//				+ RobotMap.DRIVETRAIN_RIGHT_ENCODER.getRate()
				+ " Left Count = " 
				+ RobotMap.DRIVETRAIN_LEFT_ENCODER.get()
//				+ " Left Rate = " 
//				+ RobotMap.DRIVETRAIN_LEFT_ENCODER.getRate());
				+ " Right Desired Value ="
				+ (RobotMap.DRIVETRAIN_RIGHT_ENCODER.get() + RobotMap.DRIVETRAIN_LEFT_ENCODER.get()));
		// Run drivetrain for 6 seconds for data collection
//		if (sessionTimer.get() < 6.0) {
//	
//		} else {
//			//driveSpeedCmd.cancel();
//			leftTeleopDriveCommand.setDesiredSpeed(0);
//			leftTeleopDriveCommand.cancel();
//		}
	}
	
	public static double getTimerValue() {
		return sessionTimer.get();
	}
	
	public static long getSessionIteration() {
		return sessionIteration;
	}
	
}
