package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/*
 * Simple one-stick arcade drive, using default stick on drive joystick
 */
public class DriveSimpleArcade extends Command {

	public DriveSimpleArcade() {
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.drivetrain.fakeTankDrive(Robot.oi.joystickDriver.getY(Hand.kLeft), Robot.oi.joystickOperator.getX(Hand.kRight));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
