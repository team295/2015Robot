package com.spcrobotics.subsystems;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.spcrobotics.Robot;
import com.spcrobotics.RobotMap;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DataLogger extends Subsystem{
	PowerDistributionPanel thePDP = null;
	BuiltInAccelerometer myAcc = null;
	DatagramSocket socket = null;
	final String ArduinoAddress = "10.2.95.6";
	final int PORT = 5810;
	InetAddress address = null;
	byte[] buf = new byte[256];
	String sendString;
	Timer theTimer = null;
	final Double packetInterval = 1.0;
	Double nextTime = 0.0;
	Encoder liftEncoder = null;
	
	public static final int sampleCount = 10;
//	
//	double [] xValues;
//	double [] yValues;
//	double [] zValues;
//	
//	double xSum;
//	double ySum;
//	double zSum;
//	
//	double xAvg;
//	double yAvg;
//	double zAvg;
//	
//	double sample;
//	
//	double pitch;
//	double roll;
//	
//	int currentSample;
//	double channelCurrent;
//	boolean sendAutoMsg = true;
//	boolean sendTeleMsg = true;
//	boolean sendTestMsg = true;
//	
	public DataLogger(){
		try
		{
			thePDP = RobotMap.POWERDISTRIBUTIONPANEL;
			myAcc = RobotMap.BUILTINACCELEROMETER;
			liftEncoder = RobotMap.LIFT_ENCODER;
			socket = new DatagramSocket();
			address = InetAddress.getByName(ArduinoAddress);
			theTimer = new Timer(); //what does this mean?
			theTimer.start();
//			
//			xValues = new double[sampleCount];
//			yValues = new double[sampleCount];
//			zValues = new double[sampleCount];
//			xSum = 0.0;
//			ySum = 0.0;
//			zSum = 0.0;
//			currentSample = 0;
		}
		catch (Exception ex)
		{
			System.out.println("Exception in DataLogger.java constructor.\n" + ex.getMessage());
			ex.printStackTrace();
		}
		
		
	}
//	public void logAutonomous(){
//		if (sendAutoMsg)
//    	{
//    		sendEvent("Autonomous Started");
//    		sendAutoMsg = false;
//    	}
//    	run();
//    	currentSample++;
//    	if ((currentSample % 100) == 0)
//    	{
//    		sendEvent("Interesting Stuff Happened");
//    	}
//	}
//	
//	  public void logTeleop() 
//	    {
//	    	if (sendTeleMsg)
//	    	{
//	    		sendEvent("TeleOp Started");
//	    		sendTeleMsg = false;
//	    	}
//	    	//System.out.println("X = " + myAcc.getX() + ", Y = " + myAcc.getY() +
//	    	//		", Z = " + myAcc.getZ());
//	    	sample = myAcc.getX();
//	    	xSum = xSum - xValues[currentSample] + sample;
//	    	xValues[currentSample] = sample;
//	    	xAvg = xSum/sampleCount;
//	    	
//	    	sample = myAcc.getY();
//	    	ySum = ySum - yValues[currentSample] + sample;
//	    	yValues[currentSample] = sample;
//	    	yAvg = ySum/sampleCount;
//	    	
//	    	sample = myAcc.getZ();
//	    	zSum = zSum - zValues[currentSample] + sample;
//	    	zValues[currentSample] = sample;
//	    	zAvg = zSum/sampleCount;
//	    	
//	    	currentSample++;
//	    	currentSample = currentSample % sampleCount;
//	    	
//	    	roll = Math.atan((-1 * xAvg)/zAvg) * 100;
//	    	pitch = Math.atan(yAvg/(Math.sqrt((xAvg*xAvg) + (zAvg*zAvg)))) * 100;
//	    	
//	    	System.out.println("Pitch = " + pitch + ", Roll = " + roll);
//	    	
//	    }
	    
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void run()
	{
		if (theTimer.get() >= nextTime)
		{
			sendPacket(
			thePDP.getCurrent(0)
			+ ";" + thePDP.getCurrent(1)
			+ ";" + thePDP.getCurrent(3)
			+ ";" + thePDP.getCurrent(15)
			+ ";" + thePDP.getCurrent(2)
			+ ";" + thePDP.getVoltage()
			+ ";" + liftEncoder.getRaw());
			
			System.out.println(thePDP.getCurrent(0)
					+ ";" + thePDP.getCurrent(1)
					+ ";" + thePDP.getCurrent(3)
					+ ";" + thePDP.getCurrent(15)
					+ ";" + thePDP.getCurrent(2)
					+ ";" + thePDP.getVoltage()
					+ ";" + liftEncoder.getRaw());
			nextTime = theTimer.get() + packetInterval;
		}
	}
	
	public void sendEvent(String theString)
	{
		sendPacket("#" + theString);
	}
	public void sendPacket(String data)
	{
		// This is a good place to get data for the packet.
		// Send fake data for now.
		buf = data.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
		try 
		{
			socket.send(packet);
		} catch (IOException e) 
		{
			System.out.println("Exception while sending packet.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

}
