package com.spcrobotics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.spcrobotics.subsystems.Drivetrain;
import com.spcrobotics.subsystems.GearShifter;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static GearShifter gearShifter;
	public static OI oi;

	File robotLog = null;
	FileWriter robotLogWriter = null;

	public void robotInit() {
		RobotMap.init();
		
		drivetrain = new Drivetrain();
		gearShifter = new GearShifter();
		oi = new OI();
//		autonomousCommand = new ExampleCommand();
	}

	public void disabledInit() {
		drivetrain.stop();
		
		if (robotLogWriter != null) {
			try {
				robotLogWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			robotLogWriter = null;
		}
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
//		if (autonomousCommand != null)
//			autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("left_encoder_count", RobotMap.DRIVETRAIN_LEFT_ENCODER.get());
		SmartDashboard.putNumber("right_encoder_count", RobotMap.DRIVETRAIN_RIGHT_ENCODER.get());
	}
	
	public void testInit() {
		robotLog = new File("/home/lvuser/robotlogs/log_" + System.currentTimeMillis());
		try {
			robotLogWriter = new FileWriter(robotLog);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testPeriodic() {
		LiveWindow.run();
		
		try {
			robotLogWriter.write(System.currentTimeMillis()
					+ "\t"
					+ RobotMap.DRIVETRAIN_LEFT_ENCODER.get()
					+ "\t"
					+ RobotMap.DRIVETRAIN_RIGHT_ENCODER.get());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
