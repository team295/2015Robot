package com.spcrobotics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class RobotMap {
	
	public static VictorSP DRIVETRAIN_LEFTFRONT_MOTOR;
	public static VictorSP DRIVETRAIN_LEFTBACK_MOTOR;
	public static VictorSP DRIVETRAIN_RIGHTFRONT_MOTOR;
	public static VictorSP DRIVETRAIN_RIGHTBACK_MOTOR;

	public static Encoder DRIVETRAIN_LEFT_ENCODER;
	public static Encoder DRIVETRAIN_RIGHT_ENCODER;
	
	public static RobotDrive DRIVETRAIN_DRIVE;
	
	public static Compressor COMPRESSOR;
	
	public static DoubleSolenoid GEARSHIFTER_SOLENOID;
	
	public static VictorSP LIFT_MOTOR;
	public static Encoder LIFT_ENCODER;

	public static DoubleSolenoid CLAW_EXTENDERS;
	public static DoubleSolenoid CLAW_PINS;
	
	public static void init() {
		System.out.println("Initializing RobotMap"); // DEBUG
		
		DRIVETRAIN_LEFTFRONT_MOTOR =  new VictorSP(3);
		DRIVETRAIN_LEFTBACK_MOTOR =   new VictorSP(4);
		DRIVETRAIN_RIGHTFRONT_MOTOR = new VictorSP(0);
		DRIVETRAIN_RIGHTBACK_MOTOR =  new VictorSP(1);
		
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
		
		LIFT_MOTOR = new VictorSP(8);
//		LIFT_ENCODER = new Encoder(6, 7); // TODO: Find actual channels for LIFT_ENCODER
		
		CLAW_EXTENDERS = new DoubleSolenoid(2, 3);
		CLAW_PINS = new DoubleSolenoid(6, 7);
	}
	
}
