package com.spcrobotics.commands;

import com.spcrobotics.Constant;
import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftNextSetpoint extends Command {
	
	public LiftNextSetpoint() {
		requires(Robot.lift);
		requires(Robot.pidLift);
	}

	@Override
	protected void initialize() {
		// Non-integer sentinel because setpoints can be any integer
		double nextSetpoint = 0.1;
		// Loop through setpoints from low to high
		for (int i = 0; i < Constant.LIFT_SETPOINTS.length; i++) {
			// If current encoder value is lower than the setpoint, it's the
			// next highest
			if (Robot.pidLift.getEncoderValue() < Constant.LIFT_SETPOINTS[i]) {
				nextSetpoint = Constant.LIFT_SETPOINTS[i];
				break;
			}
		}
		
		if (nextSetpoint == 0.1) { // Only possible if for-loop didn't find anything
			this.cancel();
		} else {
			Robot.pidLift.setSetpoint(nextSetpoint);
			Robot.pidLift.startSystem();
		}
	}

	@Override
	protected void execute() {}
	
	@Override
	protected boolean isFinished() {
		return this.isCanceled() || Robot.pidLift.onTarget();
	}

	@Override
	protected void end() {
		Robot.pidLift.stopSystem();
	}

	@Override
	protected void interrupted() {end();}
	
}
