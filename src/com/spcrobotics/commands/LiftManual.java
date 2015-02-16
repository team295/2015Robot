package com.spcrobotics.commands;

import com.spcrobotics.Robot;

public class LiftManual extends LiftCommand {

	public LiftManual() {
		super();
	}

	@Override
	protected void initialize() {}

	@Override
	protected void executeLift() {
		double input = Robot.lift.getLiftInput();

		// Prevent going too far up or too far down
		if ((Robot.lift.isAtTop() && input > 0) ||
			(Robot.lift.isAtBottom() && input < 0 )) {
			Robot.lift.setSpeed(0.0);
		} else {
			Robot.lift.setSpeed(input);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.lift.stopMotor();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
