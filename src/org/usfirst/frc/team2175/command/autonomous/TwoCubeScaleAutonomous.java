package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;

public class TwoCubeScaleAutonomous extends BaseCommandGroup {
	public TwoCubeScaleAutonomous(boolean isLeft) {
		addSequential(new ScaleAndGetCubeAutonomous(isLeft));
		addSequential(new DriveStraightCommand(-0.7, 83, true, true));
	}
}
