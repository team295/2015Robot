package com.spcrobotics.commands;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DrivePIDDistance extends DrivetrainCommand {

	public DrivePIDDistance() {
		super();
	}
	
	@Override
	protected void initialize() {
		Robot.leftDrive.enable();
		Robot.leftDrive.setSetpoint(24 * 1024.0D); // TODO: Move setpoint to Constant
	}

	@Override
	protected void execute() {
		if (Robot.DEBUG) {
			System.out.println("Running PIDDriveAutonomous");
			//Robot.logger.log("PIDDriveAutonomous", "execute()");
			System.out.println("Speed:" + RobotMap.DRIVETRAIN_LEFT_ENCODER.getRate());
		}
	}

	@Override
	protected boolean isFinished() {
		return Robot.leftDrive.onTarget();
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
