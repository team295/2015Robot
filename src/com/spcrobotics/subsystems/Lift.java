package com.spcrobotics.subsystems;

import com.spcrobotics.Constant;
import com.spcrobotics.OI;
import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;
import com.spcrobotics.commands.LiftManual;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {

	SpeedController motor = RobotMap.LIFT_MOTOR;
	Encoder encoder = RobotMap.LIFT_ENCODER;
	DigitalInput topSwitch = RobotMap.LIFT_TOP_SWITCH;
	DigitalInput bottomSwitch = RobotMap.LIFT_BOTTOM_SWITCH;

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LiftManual());
	}
	
	public void setSpeed(double speed) {motor.set(speed);}
	public void stopMotor() {setSpeed(0.0D);}
	
	public void resetEncoder() {encoder.reset();}
	
	public int getPosition() {return encoder.get();}
	public double getDistance() {return encoder.getDistance();}
	
	public boolean isAtTop() {return topSwitch.get() == false;}
	public boolean isAtBottom() {return bottomSwitch.get() == false;}
	
	/**
	 * @return input for lift control, with deadband applied, with positive values indicating that
	 *         the lift should rise
	 */
	public double getLiftInput() {
		double rawInput = Robot.oi.joystickOperator.getY() * -1;
		return OI.deadband(rawInput, Constant.LIFT_INPUT_DEADBAND);
	}
	
	/**
	 * Resets the encoder count to zero if the lift is at the bottom.
	 */
	public void zeroIfBottom() {
		if (isAtBottom()) {resetEncoder();}
	}
	
}
