package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public abstract class LiftCommand extends Command {

	public LiftCommand() {
		requires(Robot.lift);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.lift.zeroIfBottom();
		executeLift();
	}

	/**
	 * The executeLift method is called repeatedly until this LiftCommand either finishes or is
	 * canceled.
	 */
	protected abstract void executeLift();

}
