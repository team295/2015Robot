package com.spcrobotics.commands;

import com.spcrobotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftHighGear extends Command {

	public ShiftHighGear() {
		requires(Robot.gearShifter);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.gearShifter.shiftHighGear();
	}

	@Override
	protected boolean isFinished() {
		return Robot.gearShifter.isHighGear();
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {
		end();
	}

}
