package com.spcrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.spcrobotics.subsystems.Claw;
import com.spcrobotics.subsystems.Drivetrain;
import com.spcrobotics.subsystems.GearShifter;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static GearShifter gearShifter;
	public static Claw claw;
	public static OI oi;
	
	Timer timer;
	
	Command autonomousCommand;

	public void robotInit() {
		RobotMap.init();
		
		drivetrain = new Drivetrain();
		gearShifter = new GearShifter();
		claw = new Claw();
		timer = new Timer();
		
		oi = new OI();
//		autonomousCommand = new ExampleCommand();
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
	
	public void testInit() {
		timer.start();
	}

	public void testPeriodic() {
		LiveWindow.run();
		
		if (timer.get() < 4.0) {
			RobotMap.CLAW_EXTENDERS.set(Constant.CLAW_EXTENDERS_OUT);
		} else {
			RobotMap.CLAW_EXTENDERS.set(Constant.CLAW_EXTENDERS_IN);
		}
		
//		if (timer.get() < 4.0) {
//			RobotMap.CLAW_PINS.set(Constant.CLAW_PINS_OUT);
//		} else {
//			RobotMap.CLAW_PINS.set(Constant.CLAW_PINS_IN);
//		}
	}
}
