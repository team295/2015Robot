package com.spcrobotics.commands;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
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
	final double TURN1_TIME      = 0.5;
	final double MOVE1_TIME      = 3.0;
	final double TURN2_TIME      = 0.9;
	final double MOVE2_TIME      = 1.5;
	
	public AutoWith254() {
		// Close claw (grip game piece)
		addSequential(new ClawFullClose());
		addSequential(new WaitCommand(CLAW_CLOSE_TIME));

		// Raise lift
		addSequential(new SimpleTimedCommand(LIFT_TIME) {
			protected void execute() {RobotMap.LIFT_MOTOR.set(LIFT_MOTOR_UP_SPEED);}
			protected void end()     {RobotMap.LIFT_MOTOR.set(0.0);}
		});

		addSequential(timedTurnRight(TURN1_TIME));
		addSequential(timedMoveBack(MOVE1_TIME));
		addSequential(timedTurnRight(TURN2_TIME));
		addSequential(timedMoveBack(MOVE2_TIME));
	}
	
	// Move backwards
	public Command timedTurnRight(double time) {
		return new SimpleTimedCommand(time) {
			protected void execute() {
				Robot.drivetrain.setLeft(DRIVE_LEFT_FORWARD_SPEED * DRIVE_TURN_MULT);
				Robot.drivetrain.setRight(DRIVE_RIGHT_FORWARD_SPEED * DRIVE_TURN_MULT * -1);
			}
			
			protected void end() {Robot.drivetrain.stop();}
		};
	}
	
		// Move backwards
	public Command timedMoveBack(double time) {
		return new SimpleTimedCommand(time) {
			protected void execute() {
				Robot.drivetrain.setLeft(-1 * DRIVE_LEFT_FORWARD_SPEED);
				Robot.drivetrain.setRight(-1 * DRIVE_RIGHT_FORWARD_SPEED);
			}
			
			protected void end() {Robot.drivetrain.stop();}
		};
	}
	
}
