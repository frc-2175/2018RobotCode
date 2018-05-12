package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveOppositeSideAutonomous extends BaseCommandGroup {
	public DriveOppositeSideAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		int firstTurn = 90;
		double halfWidthOfBot = DrivetrainSubsystem.WIDTH_OF_BOT / 2;
		addSequential(new WaitCommand(0.1));
		addSequential(new DriveStraightCommand(1, 206 - halfWidthOfBot, true, false));
		addSequential(new DriveCurve(halfWidthOfBot * sign, firstTurn, 1, false, true));
		addSequential(new WaitCommand(0.3));
		double val = (isLeft) ? 204 : 218;
		addSequential(new DriveStraightCommand(1, val, true, true));
		addSequential(new TurnInPlaceCommand(-sign * 85, 0.9, true, true, false));
	}
}
