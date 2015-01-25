package com.spcrobotics.subsystems;

import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class LeftDriveTrainPID extends PIDSubsystem{
	Encoder enc_left = RobotMap.DRIVETRAIN_LEFT_ENCODER;
	public double t = 500;
	

	public LeftDriveTrainPID(double p, double i, double d) {
		super("Autonomous" ,p, i, d);
		setAbsoluteTolerance(t);
		getPIDController().setContinuous(false);
		
		Talon[] talons = new Talon[2];
		talons[0] = RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR;
		talons[1] = RobotMap.DRIVETRAIN_LEFTBACK_MOTOR;

		// TODO Auto-generated constructor stub
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return enc_left.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		setLeft(output);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	public void setLeft(double speed) {
		RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR.set(speed);
		RobotMap.DRIVETRAIN_LEFTBACK_MOTOR.set(speed);
	}
	

}
