package com.spcrobotics.subsystems;

import com.spcrobotics.Constant;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {

	DoubleSolenoid extenders = RobotMap.CLAW_EXTENDERS;
	DoubleSolenoid pins = RobotMap.CLAW_PINS;

	@Override
	public void initDefaultCommand() {}
	
	/**
	 * Extends the extender pistons of the claw to open the claw.
	 * 
	 * @param open true if the claw should be opened
	 */
	public void open(boolean open) {
		extenders.set(open ?
				Constant.CLAW_EXTENDERS_OUT : Constant.CLAW_EXTENDERS_IN);
	}
	
	/**
	 * Extends the pins of the claw to lock the pistons in the "closed" position.
	 * 
	 * @param pin true if the pins should be set (i.e. pistons locked)
	 */
	public void pin(boolean pin) {
		pins.set(pin ?
				Constant.CLAW_PINS_OUT : Constant.CLAW_PINS_IN);
	}
	
	public boolean isOpen() {
		return extenders.get() == Constant.CLAW_EXTENDERS_OUT;
	}
	
	public boolean isPinned() {
		return pins.get() == Constant.CLAW_PINS_OUT;
	}
	
}
