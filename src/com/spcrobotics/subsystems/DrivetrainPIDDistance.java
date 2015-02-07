package com.spcrobotics.subsystems;

import com.spcrobotics.Robot;
import com.spcrobotics.commands.DrivePIDDistance;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DrivetrainPIDDistance extends PIDSubsystem {

	private Encoder encoder;
	private SpeedController motorFront;
	private SpeedController motorBack;
	private boolean reverseOutput;
	
	public DrivetrainPIDDistance(
			String name,
			double p, double i, double d, double t,
			Encoder enc,
			SpeedController scFront, SpeedController scBack,
			boolean reverseOutput) {
		super(name, p, i, d);
		setAbsoluteTolerance(t);
		
		getPIDController().setContinuous(false);
//		setOutputRange(-1.0D, 1.0D);
		setOutputRange(-0.5D, 0.5D); // DEBUG
		
		encoder = enc;
		motorFront = scFront;
		motorBack = scBack;
		this.reverseOutput = reverseOutput;
	}
	
	@Override
	protected void initDefaultCommand() {}

	@Override
	protected double returnPIDInput() {
		return encoder.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (reverseOutput) output *= -1;
		motorFront.set(output);
		motorBack.set(output);
		
		Robot.logger.log(this.getName() + "PID_ios",
				String.valueOf(encoder.get()),
				String.valueOf(output),
				String.valueOf(this.getSetpoint()));
	}
	
	public void startSystem() {
		this.enable();
	}
	
	public void stopSystem() {
		this.disable();
		motorFront.set(0.0D);
		motorBack.set(0.0D);
	}

}
