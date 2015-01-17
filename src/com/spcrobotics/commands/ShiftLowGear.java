package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftLowGear extends Command {

	public ShiftLowGear() {
		requires(Robot.gearShifter);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.gearShifter.shiftLowGear();
	}

	@Override
	protected boolean isFinished() {
		return Robot.gearShifter.isLowGear();
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {
		end();
	}

}
