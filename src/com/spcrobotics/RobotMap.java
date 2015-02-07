package com.spcrobotics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class RobotMap {
	
	public static Talon DRIVETRAIN_LEFTFRONT_MOTOR;
	public static Talon DRIVETRAIN_LEFTBACK_MOTOR;
	public static Talon DRIVETRAIN_RIGHTFRONT_MOTOR;
	public static Talon DRIVETRAIN_RIGHTBACK_MOTOR;

	public static Encoder DRIVETRAIN_LEFT_ENCODER;
	public static Encoder DRIVETRAIN_RIGHT_ENCODER;
	
	public static RobotDrive DRIVETRAIN_DRIVE;
	
	public static Compressor COMPRESSOR;
	
	public static DoubleSolenoid GEARSHIFTER_SOLENOID;
	
	public static DoubleSolenoid CLAW_EXTENDERS;
	public static DoubleSolenoid CLAW_PINS;
	
	public static void init() {
		System.out.println("Initializing RobotMap"); // DEBUG
		
		DRIVETRAIN_LEFTFRONT_MOTOR =  new Talon(0);
		DRIVETRAIN_LEFTBACK_MOTOR =   new Talon(1);
		DRIVETRAIN_RIGHTFRONT_MOTOR = new Talon(2);
		DRIVETRAIN_RIGHTBACK_MOTOR =  new Talon(3);
		
		DRIVETRAIN_LEFT_ENCODER =  new Encoder(0, 1);
		DRIVETRAIN_RIGHT_ENCODER = new Encoder(2, 3);
		
		DRIVETRAIN_DRIVE = new RobotDrive(
				DRIVETRAIN_LEFTFRONT_MOTOR,
				DRIVETRAIN_LEFTBACK_MOTOR,
				DRIVETRAIN_RIGHTFRONT_MOTOR,
				DRIVETRAIN_RIGHTBACK_MOTOR
		);
		
		COMPRESSOR = new Compressor();
		COMPRESSOR.setClosedLoopControl(true);
		
		GEARSHIFTER_SOLENOID = new DoubleSolenoid(1, 0);
		GEARSHIFTER_SOLENOID.set(Constant.GEARSHIFTER_LOWGEAR_VALUE);
		
		CLAW_EXTENDERS = new DoubleSolenoid(2, 3);
		CLAW_PINS = new DoubleSolenoid(6, 7);
	}
	
}
