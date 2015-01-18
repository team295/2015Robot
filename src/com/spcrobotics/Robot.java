package com.spcrobotics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.spcrobotics.subsystems.Drivetrain;
import com.spcrobotics.subsystems.GearShifter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static GearShifter gearShifter;
	public static OI oi;
	
	Timer timer = null;
	File robotLog = null;
	FileWriter robotLogWriter = null;

	public void robotInit() {
		RobotMap.init();
		timer = new Timer();
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
		robotLog = new File("/home/lvuser/robotlogs/log_" + System.currentTimeMillis() + ".txt");
		
		try {
			timer.start();
			robotLog.createNewFile();
			robotLogWriter = new FileWriter(robotLog);
		} catch (IOException e) {e.printStackTrace();}
		
	}

	public void testPeriodic() {
		LiveWindow.run();
		
		// Write single log line
		try {
			robotLogWriter.write(System.currentTimeMillis()
					+ "\t" + timer.get()
					+ "\t" + RobotMap.DRIVETRAIN_LEFT_ENCODER.get()
					+ "\t" + RobotMap.DRIVETRAIN_RIGHT_ENCODER.get()
					+ "\n");
		} 
		catch (IOException e) {e.printStackTrace();}
		
		// Run drivetrain for 6 seconds for data collection
		if (timer.get() < 6.0) {
			drivetrain.setAll(0.1);
		} else {
			drivetrain.stop();
		}
	}
	
}
