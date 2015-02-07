package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClawOpen extends Command {

	public ClawOpen() {
		requires(Robot.claw);
	}

	protected void initialize() {
		Robot.claw.pin(false); // First remove the pin
		Robot.claw.open(true); // The open the claw
	}

	protected void execute() {}

	protected boolean isFinished() {
		return !Robot.claw.isPinned() && Robot.claw.isOpen();
	}

	protected void end() {}

	protected void interrupted() {}
}
