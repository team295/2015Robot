package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PIDDriveAutonomous extends DrivetrainCommand {

	public PIDDriveAutonomous() {
		super();
//		requires(Robot.leftDrive);
//		
//		// This is required to prevent the default command from fighting for
//		// control over the motors.
//		requires(Robot.drivetrain);
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
			Robot.logger.log("PIDDriveAutonomous", "execute()");
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
