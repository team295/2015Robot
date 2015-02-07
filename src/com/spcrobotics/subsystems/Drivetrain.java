package com.spcrobotics.subsystems;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;
import com.spcrobotics.commands.DriveSimpleArcade;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	SpeedController[] motors;
	RobotDrive drive;
	
	public Drivetrain() {
		motors = new SpeedController[4];
		motors[0] = RobotMap.DRIVETRAIN_LEFTFRONT_MOTOR;
		motors[1] = RobotMap.DRIVETRAIN_LEFTBACK_MOTOR;
		motors[2] = RobotMap.DRIVETRAIN_RIGHTFRONT_MOTOR;
		motors[3] = RobotMap.DRIVETRAIN_RIGHTBACK_MOTOR;
		
		drive = RobotMap.DRIVETRAIN_DRIVE;
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
		for (SpeedController sc : motors) {
			sc.set(0.0);
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
