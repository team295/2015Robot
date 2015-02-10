package com.spcrobotics.subsystems;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;
import com.spcrobotics.commands.DrivePIDDistance;
import com.spcrobotics.commands.DrivePIDSpeed;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DrivetrainPIDSpeed extends PIDSubsystem {

	private Encoder encoder;
	private SpeedController motorFront;
	private SpeedController motorBack;
	private boolean reverseOutput;
	
	public DrivetrainPIDSpeed(
			String name,
			double p, double i, double d, double t,
			Encoder enc,
			SpeedController scFront, SpeedController scBack,
			boolean reverseOutput) {
		super(name, p, i, d);
		setAbsoluteTolerance(t);
		
//		this.disable();
		getPIDController().setContinuous(false);
		setOutputRange(-1.0D, 1.0D); // DEBUG
		
		encoder = enc;
		motorFront = scFront;
		motorBack = scBack;
		this.reverseOutput = reverseOutput;
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

	@Override
	protected double returnPIDInput() {
		return encoder.getRate();
	}

	@Override
	protected void usePIDOutput(double output) {
//		if (reverseOutput) output *= -1;
		motorFront.set(output);
		motorBack.set(output);
		
		Robot.logger.log(this.getName() + "PID_ios",
				String.valueOf(RobotMap.DRIVETRAIN_LEFT_ENCODER.getRate()),
				String.valueOf(output),
				String.valueOf(this.getSetpoint()));
	}
	
	public void startSystem() {
		this.enable();
		this.setSetpoint(6000);
	}
	
	public void stopSystem() {
		this.disable();
		motorFront.set(0.0D);
		motorBack.set(0.0D);
	}

}
