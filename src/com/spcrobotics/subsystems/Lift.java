package com.spcrobotics.subsystems;

import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {

	SpeedController motor;
	Encoder encoder;
	DigitalInput topSwitch;
	DigitalInput bottomSwitch;
	
	public Lift() {
		motor = RobotMap.LIFT_MOTOR;
		encoder = RobotMap.LIFT_ENCODER;
	}

	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(null);
	}
	
	public void setSpeed(double speed) {motor.set(speed);}
	public void stopMotor() {setSpeed(0.0D);}
	
	public void resetEncoder() {encoder.reset();}
	
	public int getPosition() {return encoder.get();}
	public double getDistance() {return encoder.getDistance();}
	
}
