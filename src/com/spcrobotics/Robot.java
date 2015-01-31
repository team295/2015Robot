package com.spcrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.spcrobotics.subsystems.Drivetrain;
import com.spcrobotics.subsystems.GearShifter;
import com.spcrobotics.subsystems.Piston;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static GearShifter gearShifter;
	public Piston piston;
	public static OI oi;
	
	Timer timer;
	

	
	Command autonomousCommand;

	public void robotInit() {
		RobotMap.init();
		
		drivetrain = new Drivetrain();
		gearShifter = new GearShifter();
		piston = new Piston(1,2,3,4);
		timer = new Timer();
		
		oi = new OI();
//		autonomousCommand = new ExampleCommand();
	}

	public void disabledInit() {
		drivetrain.stop();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		if (autonomousCommand != null)
			autonomousCommand.start();
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

	public void testPeriodic() {
		LiveWindow.run();
		timer.start();
		piston.PistonInit();
		if (timer.get() <0) {
			piston.PistonOn();			
		}
		else
		{
			piston.Pistonoff();
		}
		piston.PistonOn();
	}
}
