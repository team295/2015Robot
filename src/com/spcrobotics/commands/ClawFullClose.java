package com.spcrobotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClawFullClose extends CommandGroup {

	public ClawFullClose() {
		addSequential(new ClawClose());
		addSequential(new ClawUnpin());
	}
}
