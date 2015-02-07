package com.spcrobotics;

import com.spcrobotics.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	public Joystick joystickDriver;
	public Joystick joystickOperator;

	public Button buttonHighGear;
	public Button buttonLowGear;
	
	public Button buttonOpen;
	public Button buttonClose;
	public Button buttonPin;
	public Button buttonUnpin;
	
	public OI() {

		System.out.println("Initializing OI"); // DEBUG
		
		
		
		/* DRIVER CONTROLS */
		
		joystickDriver = new Joystick(0);

		buttonHighGear = new JoystickButton(joystickDriver, 1);
		buttonHighGear.whenPressed(new ShiftHighGear());
		
		buttonLowGear = new JoystickButton(joystickDriver, 2);
		buttonLowGear.whenPressed(new ShiftLowGear());
		
		
		
		/* OPERATOR CONTROLS */
		
		joystickOperator = new Joystick(1);
		
		buttonOpen = new JoystickButton(joystickOperator, 1);
		buttonOpen.whenPressed(new ClawOpen());
		
		buttonClose = new JoystickButton(joystickOperator, 2);
		buttonClose.whenPressed(new ClawClose());
		
		buttonPin = new JoystickButton(joystickOperator, 3);
		buttonPin.whenPressed(new ClawPin());

		buttonUnpin = new JoystickButton(joystickOperator, 4);
		buttonUnpin.whenPressed(new ClawUnpin());
	}
	
	public static double deadband(double input, double deadband) {
		if (Math.abs(input) < Math.abs(deadband)) return 0.0;
		return input;
	}

}
