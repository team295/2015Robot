package com.spcrobotics.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PIDDrivetrain extends PIDSubsystem {

	private Encoder encoder;
	private SpeedController motorFront;
	private SpeedController motorBack;
	
	public PIDDrivetrain(
			String name,
			double p, double i, double d, double t,
			Encoder enc,
			SpeedController scFront, SpeedController scBack) {
		super(name, p, i, d);
		setAbsoluteTolerance(t);
		
		getPIDController().setContinuous(false);
		setOutputRange(-1.0D, 1.0D);
		
		encoder = enc;
		motorFront = scFront;
		motorBack = scBack;
	}

	@Override
	protected double returnPIDInput() {
		return encoder.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		motorFront.set(output);
		motorBack.set(output);
	}
	
	public void stop() {
		this.disable();
		motorFront.set(0.0D);
		motorBack.set(0.0D);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
}
