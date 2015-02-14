package com.spcrobotics.commands;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoPickupAndDrive extends CommandGroup {

	public AutoPickupAndDrive() {
		addSequential(new ClawFullClose());
		addSequential(new WaitCommand(1.0));

		// Anonymous command to run lift timed
		addSequential(new Command() {
			Timer timer = new Timer();
			
			@Override
			protected boolean isFinished() {return timer.get() > 1.5;}
			
			@Override
			protected void interrupted() {end();}
			
			@Override
			protected void initialize() {timer.start();}
			
			@Override
			protected void execute() {
				RobotMap.LIFT_MOTOR.set(0.4);
			}
			
			@Override
			protected void end() {
				RobotMap.LIFT_MOTOR.set(0.0);
			}
		});
		
		// Anonymous command to run drivetrain timed
		addSequential(new Command() {
			Timer timer = new Timer();
			
			@Override
			protected boolean isFinished() {return timer.get() > 8.0;}
			
			@Override
			protected void interrupted() {end();}
			
			@Override
			protected void initialize() {
				timer.start();
				
				// TESTING
				RobotMap.DRIVETRAIN_LEFT_ENCODER.reset();
				RobotMap.DRIVETRAIN_RIGHT_ENCODER.reset();
			}
			
			@Override
			protected void execute() {
				Robot.drivetrain.setLeft(0.3);
				Robot.drivetrain.setRight(-0.322);
			}
			
			@Override
			protected void end() {
				Robot.drivetrain.stop();
				
				// TESTING
				System.out.println("LRenc after auto: "
						+ RobotMap.DRIVETRAIN_LEFT_ENCODER.get() + " and "
						+ RobotMap.DRIVETRAIN_RIGHT_ENCODER.get());
			}
		});
	}
}
