package com.spcrobotics;

import com.spcrobotics.commands.PIDDriveAutonomous;
import com.spcrobotics.subsystems.Drivetrain;
import com.spcrobotics.subsystems.GearShifter;
import com.spcrobotics.subsystems.PIDDrivetrain;
import com.spcrobotics.util.EventLogger;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static PIDDrivetrain leftDrive;
	public static PIDDrivetrain rightDrive;
	public static GearShifter gearShifter;
	public static OI oi;
	
	public static EventLogger logger = null;
	private static Timer sessionTimer = null;
	private static long sessionIteration = 0;

	public void robotInit() {
		RobotMap.init();
		drivetrain = new Drivetrain();
		leftDrive = new PIDDrivetrain(
				"leftDrive",
				0.1D, 0.0D, 0.0D, 500, // TODO Move tolerance to Constants
				RobotMap.DRIVETRAIN_LEFT_ENCODER,
				RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR,
				RobotMap.DRIVETRAIN_LEFTBACK_MOTOR);
		rightDrive = new PIDDrivetrain(
				"rightDrive",
				0.1D, 0.0D, 0.0D, 500, // TODO Move tolerance to Constants
				RobotMap.DRIVETRAIN_RIGHT_ENCODER,
				RobotMap.DRIVETRAIN_RIGHTFRONT_MOTOR,
				RobotMap.DRIVETRAIN_RIGHTBACK_MOTOR);
		gearShifter = new GearShifter();
		oi = new OI();
		
		logger = EventLogger.getInstance();
		sessionTimer = new Timer();

//		autonomousCommand = new ExampleCommand();
	}

	public void enabledInit() {
		sessionTimer.start();
	}
	
	public void enabledPeriodic() {
		sessionIteration++;
	}
	
	public void disabledInit() {
		sessionTimer.reset();
		sessionIteration = 0;

		drivetrain.stop();
		leftDrive.stop();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		enabledInit();
		
//		if (autonomousCommand != null)
//			autonomousCommand.start();
		
		new PIDDriveAutonomous().start();
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
				String.valueOf(RobotMap.DRIVETRAIN_LEFT_ENCODER.get()),
				String.valueOf(RobotMap.DRIVETRAIN_RIGHT_ENCODER.get())
		);
		
		// Run drivetrain for 6 seconds for data collection
		if (sessionTimer.get() < 6.0) {
			drivetrain.setAll(0.1);
		} else {
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
