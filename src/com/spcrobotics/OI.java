package com.spcrobotics;

import com.spcrobotics.commands.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	// Joysticks
	public Joystick joystickDriver;
	public Joystick joystickOperator;

	// Gear shifter control buttons
	public Button buttonHighGear;
	public Button buttonLowGear;
	
	// Claw control buttons
	public Button buttonOpen;
	public Button buttonClose;
	public Button buttonPin;
	public Button buttonUnpin;
	public Button buttonLiftNext;
	public Button buttonLiftPrevious;
	
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
		
		buttonLiftNext = new POVButton(joystickOperator, 315, 0, 45);
		buttonLiftNext.whenPressed(new LiftNextSetpoint());
		
		buttonLiftPrevious = new POVButton(joystickOperator, 135, 180, 225);
		buttonLiftPrevious.whenPressed(new LiftPreviousSetpoint());

	}
	
	/**
	 * Returns 0.0 if {@code input} is between {@code deadband} and {@code -deadband}, otherwise
	 * returns {@code input}. This is useful for eliminating, for example, near-zero input values
	 * that should not be fed to speed controllers.
	 * 
	 * @param input the raw input to which the deadband will be applied
	 * @param deadband the deadband boundary
	 * @return the value of the input with deadband applied
	 */
	public static double deadband(double input, double deadband) {
		if (Math.abs(input) < Math.abs(deadband)) return 0.0;
		return input;
	}
	
	static class POVButton extends Button {
		GenericHID joystick;
		int[] values;
		
		public POVButton(GenericHID joystick, int... values) {
			this.joystick = joystick;
			this.values = values;
		}
		
		@Override
		public boolean get() {
			for (int i : values) {
				if (joystick.getPOV() == i) return true;
			}
			return false;
		}
	}

}
