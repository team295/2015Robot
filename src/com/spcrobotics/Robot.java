package com.spcrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import com.spcrobotics.subsystems.Drivetrain;
import com.spcrobotics.subsystems.GearShifter;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static GearShifter gearShifter;
	public static OI oi;

	Command autonomousCommand;

	public void robotInit() {
		RobotMap.init();
		
		drivetrain = new Drivetrain();
		gearShifter = new GearShifter();
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
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
