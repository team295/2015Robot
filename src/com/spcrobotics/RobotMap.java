package com.spcrobotics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class RobotMap {
	
	// Drivetrain speed controllers
	public static VictorSP DRIVETRAIN_LEFTFRONT_MOTOR;
	public static VictorSP DRIVETRAIN_LEFTBACK_MOTOR;
	public static VictorSP DRIVETRAIN_RIGHTFRONT_MOTOR;
	public static VictorSP DRIVETRAIN_RIGHTBACK_MOTOR;
	
	// Drivetrain encoders
	public static Encoder DRIVETRAIN_LEFT_ENCODER;
	public static Encoder DRIVETRAIN_RIGHT_ENCODER;
	
	// Basic drivetrain controller (used for non-PID drive)
	public static RobotDrive DRIVETRAIN_DRIVE;
	
	// Compressor for all pneumatics
	public static Compressor COMPRESSOR;
	
	// Gear shifter (drivetrain) solenoids
	public static DoubleSolenoid GEARSHIFTER_SOLENOID;
	
	// Lift speed controller and encoder
	public static VictorSP LIFT_MOTOR;
	public static Encoder LIFT_ENCODER;
	
	public static DigitalInput LIFT_TOP_SWITCH; 
	public static DigitalInput LIFT_BOTTOM_SWITCH; 

	// Claw solenoids (two for "extenders" that move left and right, and two for
	// "pins" that move up and down to lock the extenders in place)
	public static DoubleSolenoid CLAW_EXTENDERS;
	public static DoubleSolenoid CLAW_PINS;
	
	/**
	 * Initializes all components for the robot, and provides a central location
	 * for all channel/port-related constants.
	 */
	public static void init() {
		System.out.println("Initializing RobotMap"); // DEBUG
	
		DRIVETRAIN_LEFTFRONT_MOTOR = new VictorSP(3);
		DRIVETRAIN_LEFTBACK_MOTOR = new VictorSP(4);
		DRIVETRAIN_RIGHTFRONT_MOTOR = new VictorSP(0);
		DRIVETRAIN_RIGHTBACK_MOTOR = new VictorSP(1);
		
		DRIVETRAIN_LEFT_ENCODER = new Encoder(0, 1);
		DRIVETRAIN_RIGHT_ENCODER = new Encoder(2, 3);
		
		DRIVETRAIN_DRIVE = new RobotDrive(DRIVETRAIN_LEFTFRONT_MOTOR,
				DRIVETRAIN_LEFTBACK_MOTOR, DRIVETRAIN_RIGHTFRONT_MOTOR,
				DRIVETRAIN_RIGHTBACK_MOTOR);
		
		COMPRESSOR = new Compressor();
		// Compressor will automatically run if pressure is insufficient
		COMPRESSOR.setClosedLoopControl(true);
		
		GEARSHIFTER_SOLENOID = new DoubleSolenoid(1, 0);
		// Start in low gear so that we have a known default upon startup
		GEARSHIFTER_SOLENOID.set(Constant.GEARSHIFTER_LOWGEAR_VALUE);
		
		LIFT_MOTOR = new VictorSP(2);
		LIFT_ENCODER = new Encoder(4, 5);
		LIFT_TOP_SWITCH = new DigitalInput(6);
		LIFT_BOTTOM_SWITCH = new DigitalInput(7);
		
		CLAW_EXTENDERS = new DoubleSolenoid(2, 3);
		CLAW_PINS = new DoubleSolenoid(6, 7);
	}
	
}
