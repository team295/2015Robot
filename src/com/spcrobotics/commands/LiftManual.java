package com.spcrobotics.commands;

import com.spcrobotics.Constant;
import com.spcrobotics.OI;
import com.spcrobotics.Robot;

public class LiftManual extends LiftCommand {

	public LiftManual() {
		super();
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void executeLift() {
		double rawInput = Robot.oi.joystickOperator.getY() * -1;
		double adjInput = OI.deadband(rawInput, Constant.LIFT_INPUT_DEADBAND);
		
		// Prevent going too far up or too far down
		if ((adjInput > 0 && Robot.lift.isAtTop()) ||
			(adjInput < 0 && Robot.lift.isAtBottom())) {
			Robot.lift.setSpeed(0.0);
		} else {
			Robot.lift.setSpeed(adjInput);
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
