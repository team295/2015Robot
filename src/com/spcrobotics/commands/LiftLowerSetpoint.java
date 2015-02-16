package com.spcrobotics.commands;

import com.spcrobotics.Robot;

public class LiftLowerSetpoint extends LiftCommand {

	static final int BOTTOM_SETPOINT = -23;
	static final int TOLERANCE = 5;
	private double MOVE_SPEED;
	
	public LiftLowerSetpoint() {
		super();
	}

	protected void initialize() {
		if (Robot.lift.getPosition() < BOTTOM_SETPOINT) { // Above setpoint
			MOVE_SPEED = -0.7;
		} else if (Robot.lift.getPosition() > BOTTOM_SETPOINT) { // Below setpoint
			MOVE_SPEED = 0.4;
		} else { // At setpoint
			this.cancel();
		}
	}

	protected void executeLift() {
		Robot.lift.setSpeed(MOVE_SPEED);
	}

	protected boolean isFinished() {
		return Math.abs(Robot.lift.getPosition() - BOTTOM_SETPOINT) < TOLERANCE
				|| Math.abs(Robot.lift.getLiftInput()) > 0;
	}

	protected void end() {
		// If the Command ends (without being interrupted), stop the motor
		Robot.lift.stopMotor();
	}
	// If interrupted, let the interrupting Command take care of motor control
	protected void interrupted() {}

}
