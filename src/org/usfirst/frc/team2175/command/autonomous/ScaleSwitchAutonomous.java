package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SCurveCommandGroup;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;

public class ScaleSwitchAutonomous extends BaseCommandGroup {
	public ScaleSwitchAutonomous(boolean isLeft) {
		double turn = (isLeft) ? 1 : -1;
		addSequential(new DriveStraightCommand(1, 160, true, false));
		addSequential(new DriveStraightCommand(1, 80, false, true));
		addSequential(new DoNothingCommandGroup(), 0.5);
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new SCurveCommandGroup(turn * 8, 16, 4, false, false));
		addSequential(new DriveStraightCommand(-.5, -3, false, false));
		// TODO Add Move Elevator Up
		addSequential(new SpinIntakeOutCommand(), .5);
		// TODO Add Move Elevator Down
		addSequential(new SCurveCommandGroup(turn * 4, -8, 4, false, false));
	}
}
