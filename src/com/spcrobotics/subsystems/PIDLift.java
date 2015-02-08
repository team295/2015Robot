package com.spcrobotics.subsystems;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PIDLift extends PIDSubsystem {

	private SpeedController motor = RobotMap.LIFT_MOTOR;
	private Encoder encoder = RobotMap.LIFT_ENCODER;
	
	public PIDLift() {
		super("lift", 0.0001, 0.0, 0.0); // TODO: Move constants to Constant
		setAbsoluteTolerance(100); // TODO: Move tolerance to Constant
		
		getPIDController().setContinuous(false);
		setOutputRange(-1.0, 1.0);
	}

	@Override
	protected void initDefaultCommand() {}

	@Override
	protected double returnPIDInput() {
		return encoder.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		if ((output > 0 && Robot.lift.isAtTop()) ||
			(output < 0 && Robot.lift.isAtBottom())) {
			motor.set(0.0);
		} else {
			motor.set(output);
		}
	}
	
	public int getEncoderValue() {return encoder.get();}
	
	public void startSystem() {
		this.enable();
	}
	
	public void stopSystem() {
		this.disable();
		motor.set(0.0);
	}

}
