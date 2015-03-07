package com.spcrobotics.commands;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoWith254 extends CommandGroup {

	// SpeedController speed at which lift should raised/lowered in autonomous
	final double LIFT_MOTOR_UP_SPEED = 0.5;
	
	// SpeedController speeds for timed drivetrain (these are for forward)
	final double DRIVE_LEFT_FORWARD_SPEED = -0.8;
	final double DRIVE_RIGHT_FORWARD_SPEED = 0.856;
	
	final double DRIVE_TURN_MULT = 0.75;

	final double CLAW_CLOSE_TIME = 0.4;
	final double LIFT_TIME       = 0.6;
	final double MOVE_TIME       = 3.0;
	final double TURN_TIME       = 0.5;
	
	public AutoWith254() {
		// Close claw (grip game piece)
		addSequential(new ClawFullClose());
		addSequential(new WaitCommand(CLAW_CLOSE_TIME));

		// Raise lift
		addSequential(new SimpleTimedCommand(LIFT_TIME) {
			protected void execute() {RobotMap.LIFT_MOTOR.set(LIFT_MOTOR_UP_SPEED);}
			protected void end()     {RobotMap.LIFT_MOTOR.set(0.0);}
		});
		
		// Move backwards
		addSequential(new SimpleTimedCommand(MOVE_TIME) {
			protected void execute() {
				Robot.drivetrain.setLeft(-1 * DRIVE_LEFT_FORWARD_SPEED);
				Robot.drivetrain.setRight(-1 * DRIVE_RIGHT_FORWARD_SPEED);
			}
			
			protected void end() {Robot.drivetrain.stop();}
		});

		// Turn right in place
		addSequential(new SimpleTimedCommand(TURN_TIME) {
			protected void execute() {
				Robot.drivetrain.setLeft(DRIVE_LEFT_FORWARD_SPEED * DRIVE_TURN_MULT);
				Robot.drivetrain.setRight(DRIVE_RIGHT_FORWARD_SPEED * DRIVE_TURN_MULT * -1);
			}
			
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
