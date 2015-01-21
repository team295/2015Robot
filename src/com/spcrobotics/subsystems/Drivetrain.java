package com.spcrobotics.subsystems;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;
import com.spcrobotics.commands.DriveSimpleArcade;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	Talon[] talons;
	RobotDrive drive;
	
	public Drivetrain() {
		talons = new Talon[4];
		talons[0] = RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR;
		talons[1] = RobotMap.DRIVETRAIN_LEFTBACK_MOTOR;
		talons[2] = RobotMap.DRIVETRAIN_RIGHTFRONT_MOTOR;
		talons[3] = RobotMap.DRIVETRAIN_RIGHTBACK_MOTOR;
		
		drive = RobotMap.DRIVETRAIN_DRIVE;
		System.out.println("Is drive null? " + (drive == null));
	};

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveSimpleArcade());
	}
	
	public void tankDrive(Joystick leftJoystick, Joystick rightJoystick) {
		drive.tankDrive(leftJoystick, rightJoystick);
	}
	
	public void arcadeDrive() {
		drive.arcadeDrive(Robot.oi.joystickDriver);
	}
	
	public void arcadeDrive(Joystick movJoystick, AxisType movAxis,
	                        Joystick rotJoystick, AxisType rotAxis) {
		double mov = movJoystick.getAxis(movAxis);
		double rot = rotJoystick.getAxis(rotAxis);
		drive.arcadeDrive(mov, rot);
	}
	
	public void stop() {
		setAll(0.0);
	}
	
	public void setAll(double speed) {
		for (Talon t : talons) {
			t.set(speed);
		}
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
