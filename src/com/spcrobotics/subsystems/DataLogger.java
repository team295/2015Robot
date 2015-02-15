package com.spcrobotics.subsystems;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DataLogger extends Subsystem {
	
	public static final int sampleCount = 10;
	final String ArduinoAddress = "10.2.95.6";
	final int PORT = 5810;
	final Double packetInterval = 1.0;
	
	PowerDistributionPanel thePDP = null;
	BuiltInAccelerometer myAcc = null;
	DatagramSocket socket = null;
	InetAddress address = null;
	Timer theTimer = null;
	Encoder liftEncoder = null;
	Thread loggerThread = null;

	byte[] buf = new byte[256];
	String sendString;
	Double nextTime = 0.0;
	Boolean logging = false;

	public DataLogger() {
		try {
			thePDP = RobotMap.POWERDISTRIBUTIONPANEL;
			myAcc = RobotMap.BUILTINACCELEROMETER;
			liftEncoder = RobotMap.LIFT_ENCODER;
			socket = new DatagramSocket();
			address = InetAddress.getByName(ArduinoAddress);
			theTimer = new Timer();
			theTimer.start();
		} catch (Exception ex) {
			System.out.println("Exception in DataLogger.java constructor.\n" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	protected void initDefaultCommand() {}

	public void execute() {
		if (theTimer.get() >= nextTime) {
			sendPacket(thePDP.getCurrent(2) + ";" + thePDP.getCurrent(3) + ";"
					+ thePDP.getCurrent(15) + ";" + thePDP.getCurrent(0) + ";"
					+ thePDP.getCurrent(1) + ";" + thePDP.getVoltage() + ";" + liftEncoder.getRaw());

			nextTime = theTimer.get() + packetInterval;
		}
	}

	public void sendEvent(String theString) {
		sendPacket("#" + theString);
	}

	public void sendPacket(String data) {
		// This is a good place to get data for the packet.
		// Send fake data for now.
		buf = data.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.out.println("Exception while sending packet.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void startLogger() {
		if (logging == false) {
			logging = true;
			Runnable r = new Runnable() {
				@Override
				public void run() {
					while (logging) {
						execute();
					}
				}
			};
			loggerThread = new Thread(r);
			loggerThread.start();
		}
	}

	public void stopLogger() {
		if (logging == true) {
			logging = false;
		}
	}

}
