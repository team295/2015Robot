package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClawPin extends Command {

	public ClawPin() {
		requires(Robot.claw);
	}

	protected void initialize() {
		Robot.claw.pin(true);
	}

	protected void execute() {}

	protected boolean isFinished() {
		return Robot.claw.isPinned();
	}

	protected void end() {}

	protected void interrupted() {}
}
