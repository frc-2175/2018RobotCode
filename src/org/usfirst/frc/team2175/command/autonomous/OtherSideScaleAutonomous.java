package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class OtherSideScaleAutonomous extends BaseCommandGroup {
	public OtherSideScaleAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		int firstTurn = 90;
		double halfWidthOfBot = DrivetrainSubsystem.WIDTH_OF_BOT / 2;
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(0.1));
		addSequential(new DriveStraightCommand(1, 209 - halfWidthOfBot, true, false));
		addSequential(new DriveCurve(halfWidthOfBot * sign, firstTurn, 0.8, false, true));
		addSequential(new WaitCommand(0.3));
		double val = (isLeft) ? 211 : 222;
		addSequential(new DriveStraightCommand(1, val, true, true));
		addSequential(new TurnInPlaceCommand(-sign * 85, 0.9, true, true, false));
		addSequential(new WaitCommand(0.3));
		addParallel(new DriveStraightCommand(0.9, 50, true, true));
		addSequential(new ElevatorAutonCommand(0.9, 65, true, true));
		addSequential(new TurnInPlaceCommand(-sign * 35, 0.8, true, true, false));
		addSequential(new WaitCommand(0.2));
		addSequential(new SpinIntakeOutCommand(0.5), .5);
	}
}