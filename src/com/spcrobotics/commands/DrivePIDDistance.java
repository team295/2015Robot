package com.spcrobotics.commands;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;
import com.spcrobotics.subsystems.DrivetrainPIDDistance;

import edu.wpi.first.wpilibj.command.Command;

public class DrivePIDDistance extends Command {

	private double distance = 240.0;
	//256 encoder count per rev, 5" diameter 
	private double setpoint = distance/5*Math.PI*256;
	private DrivetrainPIDDistance pid = null;
	
	public DrivePIDDistance(DrivetrainPIDDistance pid) {
		super();
		this.pid = pid;
	}
	
	@Override
	protected void initialize() {
		pid.setSetpoint(setpoint);
		pid.startSystem();
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
