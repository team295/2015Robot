package com.spcrobotics.subsystems;

import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class AutonomousPid extends PIDSubsystem{
	Encoder enc_left = RobotMap.DRIVETRAIN_LEFT_ENCODER;
	Encoder enc_right = RobotMap.DRIVETRAIN_RIGHT_ENCODER;
	

	public AutonomousPid(double p, double i, double d) {
		super("Autonomous" ,p, i, d);
		setAbsoluteTolerance(t);
		getPIDController().setContinuous(false);
		
		Talon[] talons = new Talon[4];
		talons[0] = RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR;
		talons[1] = RobotMap.DRIVETRAIN_LEFTBACK_MOTOR;
		talons[2] = RobotMap.DRIVETRAIN_RIGHTFRONT_MOTOR;
		talons[3] = RobotMap.DRIVETRAIN_RIGHTBACK_MOTOR;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		setLeft(output);
		setRight(output);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	public void setLeft(double speed) {
		RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR.set(speed);
		RobotMap.DRIVETRAIN_LEFTBACK_MOTOR.set(speed);
	}
	
	public void setRight(double speed) {
		RobotMap.DRIVETRAIN_RIGHTFRONT_MOTOR.set(speed);
		RobotMap.DRIVETRAIN_RIGHTBACK_MOTOR.set(speed);
	}

}
