package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Simple one-stick arcade drive, using default stick on drive joystick
 */
public class DriveSimpleArcade extends Command {

	public DriveSimpleArcade() {
		super();
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		if (Robot.DEBUG) {
			System.out.println("Running DriveSimpleArcade");
			Robot.logger.log("DriveSimpleArcade", "execute()");
		}
		
		Robot.drivetrain.arcadeDrive();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
