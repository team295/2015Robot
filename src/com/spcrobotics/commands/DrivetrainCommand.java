package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public abstract class DrivetrainCommand extends Command {
	
	public DrivetrainCommand() {
		requires(Robot.drivetrain);
	}
	
	public static abstract class LeftDrivetrainCommand extends DrivetrainCommand {
		public LeftDrivetrainCommand() {
			super();
			requires(Robot.leftDistDrive);
			requires(Robot.leftSpeedDrive);
		}
	}
	
	public static abstract class RightDrivetrainCommand extends DrivetrainCommand {
		public RightDrivetrainCommand() {
			super();
			requires(Robot.rightDistDrive);
			requires(Robot.rightSpeedDrive);
		}
	}
	
}
