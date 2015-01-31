package com.spcrobotics.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class Piston {

	Solenoid[] piston;
	Compressor compressor;
	
	public Piston(int channelA, int channelB, int channelC, int channelD){
		
		piston = new Solenoid[4];
		piston[0] = new Solenoid(channelA);
		piston[1] = new Solenoid(channelB);
		piston[2] = new Solenoid(channelC);
		piston[3] = new Solenoid(channelD);
		
		compressor = new Compressor();
	}
	
	public void PistonInit(){
		compressor.start();
	}
	public void PistonOn(){
		for(Solenoid s: piston)
		{
			s.set(true);
		}
	}	
	public void Pistonoff(){
		for(Solenoid s: piston)
		{
			s.set(false);
		}
	}
	public void leftPistonOn(){
		piston[0].set(true);
		piston[1].set(true);
	}
	public void leftPistonOff(){
		piston[0].set(false);
		piston[1].set(false);
	}
	public void rightPistonOn(){
		piston[2].set(true);
		piston[3].set(true);
	}
	public void rightPistonOff(){
		piston[2].set(false);
		piston[3].set(false);
	}
	public void PistonDisable(){
		compressor.stop();
	}
	
	
	
	
}
