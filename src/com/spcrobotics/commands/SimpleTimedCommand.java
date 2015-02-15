package com.spcrobotics.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A simple timed command that will run {@code execute()} for a specified amount of time. Subclasses
 * must implement {@code execute()} and {@code end()}. The other methods cannot be overridden, as it
 * would be better to simply subclass Command.
 */
public abstract class SimpleTimedCommand extends Command {

	final double runTime;
	Timer timer = new Timer();

	/**
	 * @param time the time (in seconds) for which the {@code execute()} method should be called
	 *        periodically.
	 */
	public SimpleTimedCommand(double time) {
		this.runTime = time;
	}

	@Override
	protected final void initialize() {
		timer.start();
	}

	@Override
	protected final boolean isFinished() {
		return timer.get() > runTime;
	}

	@Override
	protected final void interrupted() {
		end();
	}
}
