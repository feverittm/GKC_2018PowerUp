/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team997.robot;

import frc.team997.robot.commands.Auto2CubeLeftLeft;
import frc.team997.robot.commands.Auto2CubeLeftStart;
import frc.team997.robot.commands.Auto2CubeRightRight;
import frc.team997.robot.commands.Auto2CubeRightStart;
import frc.team997.robot.commands.AutoCenterLeftSwitch;
import frc.team997.robot.commands.AutoCenterRightSwitch;
import frc.team997.robot.commands.AutoCenterSwitchDelivery;
import frc.team997.robot.commands.AutoDoNothing;
import frc.team997.robot.commands.AutoLeftLeftScale;
import frc.team997.robot.commands.AutoLeftLeftSwitch;
import frc.team997.robot.commands.AutoLeftScale;
import frc.team997.robot.commands.AutoRightRightScale;
import frc.team997.robot.commands.AutoRightRightSwitch;
import frc.team997.robot.commands.AutoRightScale;
import frc.team997.robot.commands.CrossLine;
import frc.team997.robot.commands.LeftScaleOrSwitch;
import frc.team997.robot.commands.RightScaleOrSwitch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector {
	private static AutoSelector instance;
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<Command>();
	public static String gameData;

	public static AutoSelector getInstance() {
		if (instance == null) {
			instance = new AutoSelector();
		}
		return instance;
	}

	public SendableChooser<Command> dbChooser() {
		// get the autonomous selection from the dashboard.
		// this should be called in INIT
		m_chooser.addDefault("Do nothing", new AutoDoNothing());

		m_chooser.addObject("Cross line", new CrossLine());
		// m_chooser.addObject("Same side switch", new SwitchSameSideDelivery());
		// m_chooser.addObject("Turn 90 degrees", new PDriveToAngle(90));
		// m_chooser.addObject("Drive forward 5 ft", new PDriveToDistance(0.5,
		// RobotMap.Values.ticksPerFoot * ((60 - RobotMap.Values.robotLength) / 12)));
		// m_chooser.addObject("Conditionals Test 2/24/28", new AutoTest());

		// 1 CUBE DELIVERY
		m_chooser.addObject("Center Switch", new AutoCenterSwitchDelivery());

		m_chooser.addObject("Left Scale or cross line", new AutoLeftScale());
		m_chooser.addObject("Right Scale or cross line", new AutoRightScale());

		m_chooser.addObject("Left Switch or cross line", new AutoLeftLeftSwitch());
		m_chooser.addObject("Right Switch or cross line", new AutoRightRightSwitch());

		m_chooser.addObject("Left Scale or left switch or cross line", new LeftScaleOrSwitch());
		m_chooser.addObject("Right Scale or right switch or cross line", new RightScaleOrSwitch());

		// 2 CUBE DELIVERY
		m_chooser.addObject("2 Cube Left Scale/Switch Left Start", new Auto2CubeLeftStart());
		m_chooser.addObject("2 Cube Right Scale/Switch Right Start", new Auto2CubeRightStart());

		SmartDashboard.putData("Auto mode", m_chooser);
		return m_chooser;
	}

	public Command AutoSM() {
		Command m_chosenCommand;
		String m_cName;

		// Autonomous selector. Use information from the field and the choices from the
		// dashboard
		// In order to work with the field information this needs to be called in
		// AutoInit

		gameData = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString("gamedata", gameData);
		System.out.println("auto init game data: " + gameData);

		m_chosenCommand = m_chooser.getSelected();
		m_cName = m_chosenCommand.getName();

		// 1 CUBE AUTO

		// AUTO CENTER SWITCH DELIVERY
		if (m_cName.equals("AutoCenterSwitchDelivery")) {
			if (gameData.charAt(0) == 'L') {
				m_autonomousCommand = new AutoCenterLeftSwitch();
				System.out.println("Autocommand center switch left");
			} else {
				m_autonomousCommand = new AutoCenterRightSwitch();
				System.out.println("Autocommand center switch right");
			}

		} else if (m_cName.equals("LeftScaleOrSwitch")) {
			if (gameData.charAt(1) == 'L') {
				m_autonomousCommand = new AutoLeftLeftScale();
				System.out.println("Autocommand scale left left");
			} else if (gameData.charAt(0) == 'L') {
				m_autonomousCommand = new AutoLeftLeftSwitch();
				System.out.println("Autocommand switch left left");
			} else {
				m_autonomousCommand = new CrossLine();
				System.out.println("Autocommand cross line");
			}

		} else if (m_cName.equals("RightScaleOrSwitch")) {
			if (gameData.charAt(1) == 'R') {
				m_autonomousCommand = new AutoRightRightScale();
				System.out.println("Autocommand scale right right");
			} else if (gameData.charAt(0) == 'R') {
				m_autonomousCommand = new AutoRightRightSwitch();
				System.out.println("Autocommand switch right right");
			} else {
				m_autonomousCommand = new CrossLine();
				System.out.println("Autocommand cross line");
			}

		} else if (m_cName.equals("AutoLeftScale")) {
			if (gameData.charAt(1) == 'L') {
				m_autonomousCommand = new AutoLeftLeftScale();
				System.out.println("Autocommand scale left left");
			} else {
				m_autonomousCommand = new CrossLine();
				System.out.println("Autocommand cross line");
			}

		} else if (m_cName.equals("AutoRightScale")) {
			if (gameData.charAt(1) == 'R') {
				m_autonomousCommand = new AutoRightRightScale();
				System.out.println("Autocommand scale right right");
			} else {
				m_autonomousCommand = new CrossLine();
				System.out.println("Autocommand cross line");
			}
		}

		// 2 CUBE AUTO

		// AUTO LEFT SCALE/SWITCH DELIVERY
		else if (m_cName.equals("Auto2CubeLeftStart")) {
			if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {
				m_autonomousCommand = new Auto2CubeLeftLeft();
				System.out.println("Autocommand 2 cube left left scale and switch");
			} else if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
				m_autonomousCommand = new AutoLeftLeftSwitch();
				System.out.println("Autocommand 1 cube left left switch (2 CUBE NOT SUPPORTED)");
			} else if (gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {
				m_autonomousCommand = new AutoLeftLeftScale();
				System.out.println("Autocommand 1 cube left left scale (2 CUBE NOT SUPPORTED)");
			} else if (gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {
				m_autonomousCommand = new CrossLine();
				System.out.println("Autocommand crossline (2 CUBE NOT SUPPORTED");
			}
		}
		// AUTO RIGHT SCALE/SWITCH DELIVERY
		else if (m_cName.equals("Auto2CubeRightStart")) {
			if (gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {
				m_autonomousCommand = new Auto2CubeRightRight();
				System.out.println("Autocommand 2 cube right right scale and switch");
			} else if (gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {
				m_autonomousCommand = new AutoRightRightSwitch();
				System.out.println("Autocommand 1 cube right right switch (2 CUBE NOT SUPPORTED)");
			} else if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
				m_autonomousCommand = new AutoRightRightScale();
				System.out.println("2 CUBE NOT SUPPORTED");
			} else if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {
				m_autonomousCommand = new CrossLine();
				System.out.println("Autocommand crossline (2 CUBE NOT SUPPORTED)");
			}
		} else if (m_cName.equals("AutoRightRightSwitch")) {
			if (gameData.charAt(0) == 'R') {
				m_autonomousCommand = new AutoRightRightSwitch();
				System.out.println("Autocommand right switch right");
			} else {
				m_autonomousCommand = new CrossLine();
				System.out.println("");
			}

		} else if (m_cName.equals("AutoLeftLeftSwitch")) {
			if (gameData.charAt(0) == 'L') {
				m_autonomousCommand = new AutoLeftLeftSwitch();
				System.out.println("Autocommand left switch left");
			} else {
				m_autonomousCommand = new CrossLine();
				System.out.println("");
			}

		} else {
			m_autonomousCommand = m_chooser.getSelected();
			System.out.println("Name is: " + (m_chooser.getSelected()).getName());
			System.out.println("autocommand is " + m_autonomousCommand.getName());
		}

		return m_autonomousCommand;
	}
}