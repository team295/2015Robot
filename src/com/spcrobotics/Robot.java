package com.spcrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.spcrobotics.subsystems.*;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static GearShifter gearShifter;
	public static Lift lift;
	public static Claw claw;
	public static OI oi;
	public static DataLogger dataLogger;
	
	Timer timer;
	
	Command autonomousCommand;

	public void robotInit() {
		RobotMap.init();
		
		dataLogger = new DataLogger();
		drivetrain = new Drivetrain();
		gearShifter = new GearShifter();
		lift = new Lift();
		claw = new Claw();
		oi = new OI();
		
		timer = new Timer();
	}

	public void disabledInit() {
		drivetrain.stop();
		
		timer.stop();
		timer.reset();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
				dataLogger.run();
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
		
		if (autonomousCommand != null)
			autonomousCommand.start();
		dataLogger.sendEvent("#Autonomous Started");
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		dataLogger.sendEvent("#Teleop Started");
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("left_encoder_count", RobotMap.DRIVETRAIN_LEFT_ENCODER.get());
		SmartDashboard.putNumber("right_encoder_count", RobotMap.DRIVETRAIN_RIGHT_ENCODER.get());
	}
	
	public void testInit() {
		timer.start();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
