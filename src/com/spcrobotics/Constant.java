package com.spcrobotics;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Constant {
	
	public static final String LOGGER_LOGDIR = "/home/lvuser/_logs/";
	public static final String LOGGER_DELIMITER = "\t";
	
	public static final DoubleSolenoid.Value GEARSHIFTER_LOWGEAR_VALUE
			= DoubleSolenoid.Value.kForward;
	public static final DoubleSolenoid.Value GEARSHIFTER_HIGHGEAR_VALUE
			= DoubleSolenoid.Value.kReverse;
	
}
