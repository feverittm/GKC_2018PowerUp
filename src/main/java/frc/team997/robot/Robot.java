/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team997.robot;

import frc.team997.robot.subsystems.Collector;
import frc.team997.robot.subsystems.DriveTrain;
import frc.team997.robot.subsystems.Elevator;
import frc.team997.robot.AutoSelector;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Collector collector;
	public static DriveTrain drivetrain;
	public static Elevator elevator;
	public static OI m_oi;
	public static Logger logger;
	public static AutoSelector autoselect;
	public static String gameData;
	public static PowerDistributionPanel pdp;
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<Command>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		collector = new Collector();
		drivetrain = new DriveTrain();
		elevator = new Elevator();
		m_oi = new OI();
		pdp = new PowerDistributionPanel();
		gameData = "";
		
		logger = Logger.getInstance();

		autoselect = AutoSelector.getInstance();		
		pdp.clearStickyFaults();
		LiveWindow.disableTelemetry(pdp); // turn-off the telemetry features in Livewindow to stop the CTRE Timeouts
		
		autoselect.dbChooser();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		drivetrain.updateDashboard();
		collector.updateSmartDashboard();
		elevator.updateSmartDashboard();
		elevator.autozero();
		logger.close();
		gameData = "";
		//controlCurrent();
	}

	//noot noot
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		drivetrain.updateDashboard();
		collector.updateSmartDashboard();
		elevator.updateSmartDashboard();
		elevator.autozero();
		//controlCurrent();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		logger.openFile();
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString("gamedata", gameData);
		System.out.println("auto init game data: " + gameData);
		
		//AUTONOMOUS CHOSEN BASED ON GAMEDATA
		//this logic works. has been tested :)
		m_autonomousCommand = autoselect.AutoSM();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		drivetrain.updateDashboard();
		collector.updateSmartDashboard();
		elevator.updateSmartDashboard();
		//controlCurrent();

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		drivetrain.updateDashboard();
		collector.updateSmartDashboard();
		elevator.updateSmartDashboard();
		logger.logAll();
		//controlCurrent();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		drivetrain.updateDashboard();
		collector.updateSmartDashboard();
		elevator.updateSmartDashboard();
		//controlCurrent();

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		drivetrain.updateDashboard();
		collector.updateSmartDashboard();
		elevator.updateSmartDashboard();
		//controlCurrent();
		elevator.autozero();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	/*public void controlCurrent() {
		if (pdp.getTotalCurrent() > 180) {
			RobotMap.Values.drivetrainLeftLimit = 61;
			RobotMap.Values.drivetrainRightLimit = 61;
		}
	}*/
	
	//x = clamp(x, -1, 1);
	public static double clamp(double x, double min, double max) {
		if (x > max) {
			return max;
		} else if(x < min) {
			return min;
		} else {
			return x;
		}
	}
		public static String getGameData() {
			return gameData;
	}
		
		public static void isAuto(boolean a) {
			SmartDashboard.putBoolean("isAuto", a);
		}

}
