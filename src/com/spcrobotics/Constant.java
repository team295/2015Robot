package com.spcrobotics;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Constant {
	
	public static final String LOGGER_LOGDIR = "/home/lvuser/_logs/";
	public static final String LOGGER_DELIMITER = "\t";
	
	public static final DoubleSolenoid.Value GEARSHIFTER_LOWGEAR_VALUE
			= DoubleSolenoid.Value.kForward;
	public static final DoubleSolenoid.Value GEARSHIFTER_HIGHGEAR_VALUE
			= DoubleSolenoid.Value.kReverse;
	
	public static final DoubleSolenoid.Value CLAW_EXTENDERS_OUT
			= DoubleSolenoid.Value.kForward;
	public static final DoubleSolenoid.Value CLAW_EXTENDERS_IN
			= DoubleSolenoid.Value.kReverse;
	public static final DoubleSolenoid.Value CLAW_PINS_OUT
			= DoubleSolenoid.Value.kForward;
	public static final DoubleSolenoid.Value CLAW_PINS_IN
			= DoubleSolenoid.Value.kReverse;
	
	// This list must be sorted from low to high
	public static final int[] LIFT_SETPOINTS = {
		2000,
		5000,
		10000,
		15000,
		20000
	};
	
}
