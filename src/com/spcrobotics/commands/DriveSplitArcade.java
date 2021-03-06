package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSplitArcade extends Command {

	public DriveSplitArcade() {
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		// Scale rotation output to 80%
		Robot.drivetrain.splitArcadeDrive(1.0, 0.8);
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
