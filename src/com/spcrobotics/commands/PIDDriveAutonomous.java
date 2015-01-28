package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PIDDriveAutonomous extends Command {

	public PIDDriveAutonomous() {
		requires(Robot.leftDrive);
	}
	
	@Override
	protected void initialize() {
		Robot.leftDrive.enable();
		Robot.leftDrive.setSetpoint(5000.0D); // TODO: Move setpoint to Constant
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return Robot.leftDrive.onTarget();
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
