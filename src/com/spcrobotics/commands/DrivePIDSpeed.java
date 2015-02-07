package com.spcrobotics.commands;

import com.spcrobotics.Robot;
import com.spcrobotics.subsystems.DrivetrainPIDSpeed;

import edu.wpi.first.wpilibj.command.Command;

public class DrivePIDSpeed extends Command {

	private DrivetrainPIDSpeed pid = null;
	private double desiredSpeed = 0.0D;
	
	public DrivePIDSpeed(DrivetrainPIDSpeed pid) {
		super();
		this.pid = pid;
	}
	
	@Override
	protected void initialize() {
		pid.setSetpoint(desiredSpeed);
		pid.startSystem();
	}

	@Override
	protected void execute() {
		if (Robot.DEBUG) {
			System.out.println("Running PIDDrivetrainSpeed");
		}
		
		pid.setSetpoint(transferInput());
	}

	@Override
	protected boolean isFinished() {
		return false; // Command should always run
	}

	@Override
	protected void end() {
		pid.stopSystem();
	}

	@Override
	protected void interrupted() {end();}
	
	public void setDesiredSpeed(double desiredSpeed) {
		this.desiredSpeed = desiredSpeed;
	}
	
	private double transferInput() {
		double adjusted = desiredSpeed;
		
		return adjusted;
	}

}
