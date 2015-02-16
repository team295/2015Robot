package com.spcrobotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftGoToLowerSetpoint extends CommandGroup {

	public LiftGoToLowerSetpoint() {
		addSequential(new LiftBottom());
		addSequential(new LiftLowerSetpoint());
	}

}
