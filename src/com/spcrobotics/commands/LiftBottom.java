package com.spcrobotics.commands;

import com.spcrobotics.Robot;

public class LiftBottom extends LiftCommand {

	public LiftBottom() {
		super();
	}

	protected void initialize() {}

	protected void executeLift() {
		Robot.lift.setSpeed(-0.7);
	}

	protected boolean isFinished() {
		return Robot.lift.isAtBottom() || Math.abs(Robot.lift.getLiftInput()) > 0;
	}

	protected void end() {
		// If the Command ends (without being interrupted), stop the motor
		Robot.lift.stopMotor();
	}

	// If interrupted, let the interrupting Command take care of motor control
	protected void interrupted() {}

}
