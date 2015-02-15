package com.spcrobotics.commands;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoPickupAndDrive extends CommandGroup {

	public AutoPickupAndDrive() {
		addSequential(new ClawFullClose());
		addSequential(new WaitCommand(1.0));

		// Lower lift
		addSequential(new SimpleTimedCommand(1.5) {
			@Override
			protected void execute() {RobotMap.LIFT_MOTOR.set(0.4);}
			
			@Override
			protected void end() {RobotMap.LIFT_MOTOR.set(0.0);}
		});
		
		// Move backwards
		addSequential(new SimpleTimedCommand(8.0) {
			@Override
			protected void execute() {
				Robot.drivetrain.setLeft(0.3);
				Robot.drivetrain.setRight(-0.322);
			}
			
			@Override
			protected void end() {Robot.drivetrain.stop();}
		});
	}
}
