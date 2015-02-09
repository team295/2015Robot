package com.spcrobotics.commands;

import com.spcrobotics.OI;
import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftManual extends Command {

	public LiftManual() {
		requires(Robot.lift);
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		double rawInput = Robot.oi.joystickOperator.getY() * -1;
		double adjInput = OI.deadband(rawInput, 0.1);
		
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