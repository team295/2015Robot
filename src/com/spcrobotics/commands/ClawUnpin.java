package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClawUnpin extends Command {

	public ClawUnpin() {
		requires(Robot.claw);
	}

	protected void initialize() {
		Robot.claw.pin(false);
	}

	protected void execute() {}

	protected boolean isFinished() {
		return !Robot.claw.isPinned();
	}

	protected void end() {}

	protected void interrupted() {}
}
