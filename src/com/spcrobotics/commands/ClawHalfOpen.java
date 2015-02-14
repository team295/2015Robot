package com.spcrobotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ClawHalfOpen extends CommandGroup {

	public ClawHalfOpen() {
		addSequential(new ClawClose());
		addSequential(new WaitCommand(1.0));
		addSequential(new ClawPin());
		addSequential(new ClawOpen());
	}
}
