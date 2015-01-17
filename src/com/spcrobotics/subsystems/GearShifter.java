package com.spcrobotics.subsystems;

import com.spcrobotics.Constant;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearShifter extends Subsystem {
	
	DoubleSolenoid solenoid = RobotMap.GEARSHIFTER_SOLENOID;
	
	@Override
	public void initDefaultCommand() {}
	
	public void shiftHighGear() {
		solenoid.set(Constant.GEARSHIFTER_HIGHGEAR_VALUE);
	}
	
	public void shiftLowGear() {
		solenoid.set(Constant.GEARSHIFTER_LOWGEAR_VALUE);
	}
	
	public boolean isHighGear() {
		return solenoid.get().value == Constant.GEARSHIFTER_HIGHGEAR_VALUE.value;
	}

	public boolean isLowGear() {
		return solenoid.get().value == Constant.GEARSHIFTER_LOWGEAR_VALUE.value;
	}
	
}
