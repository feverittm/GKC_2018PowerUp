/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team997.robot;

import edu.wpi.first.wpilibj.SerialPort;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Ports {
		public static int
		//CAN
		leftTalonPort = 3,
		rightTalonPort = 8,
		elevatorTalonPort = 2,
		
		//ANALOGINPUT
		leftCollectorSensorInput = 0,
		rightCollectorSensorInput = 1,
		
		//PWM
		leftVictorPort = 4,
		leftVictorPort2 = 5,
		rightVictorPort = 6,
		rightVictorPort2 = 7,
		elevatorVictorPort = 1,
		leftCollectorPort = 2,
		rightCollectorPort = 3,
		
		//PNEUMATICS
		elevatorSolenoidPort = 0,
		elevatorSolenoidPort2 = 1,
		
		//DIO
		leftEncoderPort1 = 0,
		leftEncoderPort2 = 1,
		rightEncoderPort1 = 2,
		rightEncoderPort2 = 3,
		
		//GAMEPADS
		GamePad1 = 0, 
		GamePad2 = 1;
		
		//USB
		public static final SerialPort.Port AHRS = SerialPort.Port.kUSB;
		
	}
	
	public static class PDPPorts {
		public static int
		
		rightDriveTrain = 0,
		rightDriveTrain2 = 1,
		intake = 6,
		intake2 = 7,
		elevatorTalon = 10,
		elevatorVictor = 11,
		leftDriveTrain = 12,
		leftDriveTrain2 = 13,
		leftDriveTrainTalon = 14,
		rightDriveTrainTalon = 15;
	}
	
	public static class Values {
		
		public static double
		inchesPerTick = (3.954*Math.PI)/4096,	//inches per encoder tick
		ticksPerFoot = ((49152/(3.97*Math.PI)))*0.9, //3940, //encoder ticks per foot	
		
		robotLength = 33.25, //in inches
		robotWidth = 37.25,
		robotWheelBase = 30,
		
		autoSwitchDistance = 14, //in feet (supposedly)
		autoScaleDistance = 28, //in feet
		
		//From back of wall to front of switch. Measure these at each comp!
		autoLeftSwitchTotal = 139/12, //11.79167, 
		autoRightSwitchTotal = 139/12, //11.77083,
		autoCenterSwitchTotal = 139/12, //12,
		
		autoAngle1,
		autoAngle2,
		turnAngle,
		
		//ir sensor value - the reading when close enough
		//Measure this at comp
		autoIRthreshold = 0.8,	//0.8 for plywood, 0.4 for clear plexiglass
		
		elevatorTopHeight = 36853, //<--CB top height
							//22336, <--PB top height
		elevatorHighMidHeight = 16000,
		elevatorLowMidHeight = 6135,
		elevatorSwitchHeight = 14333,
								//14083 <--PB switch height,
		elevatorBottomHeight = 0,
		elevatorSafeDriveHeight = 2750, //good for PB and CB
		//TODO: Set values
		
		slowcollectspeed = 0.25,
		fastcollectspeed = 0.75,
		collectspeed = 0.25,
		

		driveDistanceP = 0.00025,
		driveDistanceI = 0.0,
		driveDistanceD = 0.0,
		elevatorPidP = 0.75,
		elevatorPidI = 0.001,
		elevatorPidD = 0, //0.1,
		
		driveAngleP = 0.015, //0.035, then 0.0138, then 0.0244 (important one), then 0.00694, then 0.0138
		driveAngleI = 0.002595, //0.000138 (important one) then 0.000038 then 0.000138 then 0.0001037 
		//then 0.000138 then 0.000514
		driveAngleD = 0,
		
		//voltage limits in amps
		drivetrainLeftLimit = 81, //81
		drivetrainRightLimit = 81, //81
		collectorLeftLimit = 12,
		collectorRightLimit = 12,
		elevatorLimit = 55;
		
	}

	public static class Buttons {
		public static int
		
		//ELEVATOR CONTROLS
		elevatorManualUp = 6, //Right Bumper, GamePad1
		elevatorManualDown = 5, //Left Bumper, GamePad1
		//elevatorArrayUp = 4, //Square, GamePad2
		elevatorArrayDown = 1, //Triangle, GamePad2
		
		topPosition = 4, //Y, GamePad1
		highMidPosition = 2, //B, GamePad1
		switchPosition = 8, //Start, GamePad1
		lowMidPosition = 3, //X, Gamepad1
		bottomPosition = 1, //A, GamePad1
		
		//COLLECTOR CONTROLS
		collectButton = 4, //Right Trigger, Gamepad2
		//smartCollectButton = 7, //Left Trigger, Gamepad2 TODO: needs testing
		uncollectButton = 1, //Left Trigger, Gamepad2
		flopButton = 7; //Back, Gamepad1
	}
}
