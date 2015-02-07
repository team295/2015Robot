package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClawClose extends Command {

	public ClawClose() {
		requires(Robot.claw);
	}

	protected void initialize() {
		Robot.claw.open(false); // First close the claw
		Robot.claw.pin(true); // Then extend the pin
	}

	protected void execute() {}

	protected boolean isFinished() {
		return !Robot.claw.isOpen() && Robot.claw.isPinned();
	}

	protected void end() {}

	protected void interrupted() {}
}
