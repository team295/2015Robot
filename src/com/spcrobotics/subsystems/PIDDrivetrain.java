package com.spcrobotics.subsystems;

import com.spcrobotics.Robot;
import com.spcrobotics.commands.PIDDriveAutonomous;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PIDDrivetrain extends PIDSubsystem {

	private Encoder encoder;
	private SpeedController motorFront;
	private SpeedController motorBack;
	private boolean reverseOutput;
	
	public PIDDrivetrain(
			String name,
			double p, double i, double d, double t,
			Encoder enc,
			SpeedController scFront, SpeedController scBack,
			boolean reverseOutput) {
		super(name, p, i, d);
		setAbsoluteTolerance(t);
		
		getPIDController().setContinuous(false);
//		setOutputRange(-1.0D, 1.0D);
		setOutputRange(-0.2D, 0.2D); // DEBUG
		
		encoder = enc;
		motorFront = scFront;
		motorBack = scBack;
		this.reverseOutput = reverseOutput;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PIDDriveAutonomous());
	}

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
	
	public void stop() {
		this.disable();
		motorFront.set(0.0D);
		motorBack.set(0.0D);
	}

}
