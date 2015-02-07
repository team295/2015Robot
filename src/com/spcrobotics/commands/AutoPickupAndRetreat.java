package com.spcrobotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPickupAndRetreat extends CommandGroup {

	public AutoPickupAndRetreat() {
//		addSequential(new CloseClaw());
//		addSequential(new RaiseLift());
		addSequential(new DrivePIDDistance(-1 * 1024.0 * 25));
	}
}
