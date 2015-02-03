package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivePIDSpeed extends DrivetrainCommand {

	public DrivePIDSpeed() {
		super();
		requires(Robot.leftSpeedDrive);
	}
	
	@Override
	protected void initialize() {
		Robot.leftSpeedDrive.enable();
		Robot.leftSpeedDrive.setSetpoint(6000);
		 // TODO: Move setpoint to Constant
	}

	@Override
	protected void execute() {
		if (Robot.DEBUG) {
			System.out.println("Running PIDDrivetrainSpeed");
		}
	}

	@Override
	protected boolean isFinished() {
		return Robot.leftSpeedDrive.onTarget();
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
