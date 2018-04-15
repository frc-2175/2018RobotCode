package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInDriverCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;

public class TwoCubeScaleAndSwitchAutonomous extends BaseCommandGroup {
	public TwoCubeScaleAndSwitchAutonomous(boolean isLeft) {
		addSequential(new ScaleAndGetCubeAutonomous(isLeft));
		addParallel(new DriveStraightCommand(-0.7, 18, false, false));
		addSequential(new SpinIntakeInDriverCommand(), 1);
		addSequential(new DriveStraightCommand(0.7, 18, false, false));
		addSequential(new SpinIntakeOutCommand(), 0.5);
	}
}