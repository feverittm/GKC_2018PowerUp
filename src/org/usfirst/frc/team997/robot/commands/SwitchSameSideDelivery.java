package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchSameSideDelivery extends CommandGroup {

    public SwitchSameSideDelivery() {
        	
        	addSequential(new PDriveToDistance(14 * PDriveToDistance.ticksPerFoot));
        	if (Robot.getGameData().charAt(0) == 'L') {
        		addSequential(new PDriveToAngle(90)); //Turn right to face switch.
        	} else {
        		addSequential(new PDriveToAngle(-90)); //Turn left to face switch.
        	}
        	//TODO How far does the robot have to travel to get close enough to the switch?

    }
}