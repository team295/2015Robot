package com.spcrobotics.subsystems;

import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class RightDriveTrainPID extends PIDSubsystem{
	Encoder enc_right = RobotMap.DRIVETRAIN_RIGHT_ENCODER;
	public double t = 1.0;
	

	public RightDriveTrainPID(double p, double i, double d) {
		super("Autonomous" ,p, i, d);
		setAbsoluteTolerance(t);
		getPIDController().setContinuous(false);
		
		Talon[] talons = new Talon[2];
		talons[0] = RobotMap.DRIVETRAIN_RIGHTFRONT_MOTOR;
		talons[1] = RobotMap.DRIVETRAIN_RIGHTBACK_MOTOR;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return enc_right.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		setRight(output);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	public void setRight(double speed) {
		RobotMap.DRIVETRAIN_RIGHTFRONT_MOTOR.set(speed);
		RobotMap.DRIVETRAIN_RIGHTBACK_MOTOR.set(speed);
	}

}
