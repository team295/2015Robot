package com.spcrobotics.commands;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DrivePIDDistance extends Command {

	private final double setpoint;
	
	public DrivePIDDistance(double distanceSetpoint) {
		super();
		this.setpoint = distanceSetpoint;
	}
	
	@Override
	protected void initialize() {
		Robot.leftDistDrive.setSetpoint(setpoint);
		Robot.leftDistDrive.enable();
	}

	@Override
	protected void execute() {
		if (Robot.DEBUG) {
			System.out.println("Running PIDDriveAutonomous");
			System.out.println("Speed: " + RobotMap.DRIVETRAIN_LEFT_ENCODER.getRate());
		}
	}

	@Override
	protected boolean isFinished() {
		return Robot.leftDistDrive.onTarget();
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
