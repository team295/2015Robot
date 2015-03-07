package com.spcrobotics.commands;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoPickupAndDrive extends CommandGroup {

	// SpeedController speed at which lift should raised/lowered in autonomous
	final double LIFT_MOTOR_UP_SPEED = 0.4;
	
	// Time for which lift should be raised and lowered
	final double LIFT_TIME = 1.5;
	
	// SpeedController speeds for timed drivetrain (these are for forward)
	final double DRIVE_LEFT_FORWARD_SPEED = -0.4;
	final double DRIVE_RIGHT_FORWARD_SPEED = 0.428;
	
	public AutoPickupAndDrive() {
		addSequential(new ClawFullClose());
//		addSequential(new LiftGoToLowerSetpoint());
		addSequential(new WaitCommand(1.0));

		// Raise lift
		addSequential(new SimpleTimedCommand(LIFT_TIME) {
			@Override
			protected void execute() {RobotMap.LIFT_MOTOR.set(LIFT_MOTOR_UP_SPEED);}
			
			@Override
			protected void end() {RobotMap.LIFT_MOTOR.set(0.0);}
		});
		
		// Move backwards
		addSequential(new SimpleTimedCommand(4.5) {
			@Override
			protected void execute() {
				Robot.drivetrain.setLeft(-1 * DRIVE_LEFT_FORWARD_SPEED);
				Robot.drivetrain.setRight(-1 * DRIVE_RIGHT_FORWARD_SPEED);
			}
			
			@Override
			protected void end() {Robot.drivetrain.stop();}
		});

		// Turn right in place
		addSequential(new SimpleTimedCommand(1.5) {
			@Override
			protected void execute() {
				Robot.drivetrain.setLeft(DRIVE_LEFT_FORWARD_SPEED);
				Robot.drivetrain.setRight(-1 * DRIVE_RIGHT_FORWARD_SPEED);
			}
			
			@Override
			protected void end() {Robot.drivetrain.stop();}
		});
		
//		// Lower lift
//		addSequential(new SimpleTimedCommand(LIFT_TIME) {
//			// Only continue going down if we don't hit bottom
//			@Override
//			protected void execute() {
//				if (Robot.lift.isAtBottom()) {end();}
//				else {RobotMap.LIFT_MOTOR.set(-1 * LIFT_MOTOR_UP_SPEED);}
//			}
//			
//			@Override
//			protected void end() {RobotMap.LIFT_MOTOR.set(0.0);}
//		});
//		
//		addSequential(new ClawFullOpen());
	}
}
