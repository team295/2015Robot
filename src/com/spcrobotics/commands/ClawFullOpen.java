package com.spcrobotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ClawFullOpen extends CommandGroup {

	public ClawFullOpen() {
		addSequential(new ClawClose());
		addSequential(new WaitCommand(0.5));
		addSequential(new ClawUnpin());
		addSequential(new ClawOpen());
	}
}
