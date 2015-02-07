package com.spcrobotics.commands;

import com.spcrobotics.OI;
import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualLiftControl extends Command {

	public ManualLiftControl() {
		requires(Robot.lift);
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.lift.setSpeed(
				OI.deadband(Robot.oi.joystickOperator.getY(), 0.1));
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
