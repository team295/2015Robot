package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public abstract class DrivetrainCommand extends Command {
	
	public DrivetrainCommand() {
		requires(Robot.drivetrain);
		requires(Robot.leftDrive);
		requires(Robot.rightDrive);
		requires(Robot.leftSpeedDrive);
		requires(Robot.rightSpeedDrive);
	}
	
}
