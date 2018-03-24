package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeUpCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;

public class TwoCubeScaleAndSwitchAutonomous extends BaseCommandGroup {
	public TwoCubeScaleAndSwitchAutonomous(boolean isLeft) {
		addSequential(new ScaleAndGetCubeAutonomous(isLeft));
		addSequential(new DriveStraightCommand(-0.7, 15, true, true));
		addSequential(new MoveIntakeUpCommand());
		addSequential(new DriveStraightCommand(0.7, 15, true, true));
		addSequential(new SpinIntakeOutCommand(), 2);
	}
}
