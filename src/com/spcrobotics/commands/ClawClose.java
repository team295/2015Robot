package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClawClose extends Command {

	public ClawClose() {
		requires(Robot.claw);
	}

	protected void initialize() {
		Robot.claw.open(false);
	}

	protected void execute() {}

	protected boolean isFinished() {
		return !Robot.claw.isOpen();
	}

	protected void end() {}

	protected void interrupted() {}
}
