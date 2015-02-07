package com.spcrobotics;

import com.spcrobotics.commands.ShiftHighGear;
import com.spcrobotics.commands.ShiftLowGear;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	public Joystick joystickDriver;
	public Joystick joystickOperator;

	public Button buttonHighGear;
	public Button buttonLowGear;
	
	public OI() {

		System.out.println("Initializing OI"); // DEBUG
		
		joystickDriver = new Joystick(0);
		joystickOperator = new Joystick(1);

		buttonHighGear = new JoystickButton(joystickDriver, 1);
		buttonHighGear.whenPressed(new ShiftHighGear());
		
		buttonLowGear = new JoystickButton(joystickDriver, 2);
		buttonLowGear.whenPressed(new ShiftLowGear());
		
	}
	
	public static double deadband(double input, double deadband) {
		if (Math.abs(input) < Math.abs(deadband)) return 0.0;
		return input;
	}

}
