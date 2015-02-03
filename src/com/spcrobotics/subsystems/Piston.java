package com.spcrobotics.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class Piston {
	
	DoubleSolenoid leftPiston;
	DoubleSolenoid rightPiston;

	Compressor compressor;
	
	public Piston(int channelA, int channelB, int channelC, int channelD){
		

		leftPiston = new DoubleSolenoid(channelA, channelB);
		rightPiston = new DoubleSolenoid(channelC, channelD);
		
		compressor = new Compressor();
	}
	
	public void PistonInit(){
		compressor.start();
	}
	public void PistonOn(){
		leftPiston.set(DoubleSolenoid.Value.kForward);
		rightPiston.set(DoubleSolenoid.Value.kForward);
	}	
	public void Pistonoff(){
		leftPiston.set(DoubleSolenoid.Value.kOff);
		rightPiston.set(DoubleSolenoid.Value.kOff);
	}
	public void leftPistonOn(){
		leftPiston.set(DoubleSolenoid.Value.kForward);
	}
	public void leftPistonOff(){
		leftPiston.set(DoubleSolenoid.Value.kOff);
	}
	public void rightPistonOn(){
		rightPiston.set(DoubleSolenoid.Value.kForward);
	}
	public void rightPistonOff(){
		rightPiston.set(DoubleSolenoid.Value.kOff);
	}
	public void PistonDisable(){
		compressor.stop();
	}
	
	
	
	
}
