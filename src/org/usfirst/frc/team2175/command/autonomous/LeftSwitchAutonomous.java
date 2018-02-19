package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;

public class LeftSwitchAutonomous extends BaseCommandGroup {
	public LeftSwitchAutonomous() {
		addSequential(new DriveStraightCommand(0.7, 144, true, true));
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new SpinIntakeOutCommand(), 2);
	}
}
