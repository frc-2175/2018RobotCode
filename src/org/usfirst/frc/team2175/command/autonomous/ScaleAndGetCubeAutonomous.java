package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeDownCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

public class ScaleAndGetCubeAutonomous extends BaseCommandGroup {
	public ScaleAndGetCubeAutonomous(boolean isLeft) {
		addSequential(new ScaleAutonomous(isLeft));
		addSequential(new TurnInPlaceCommand(-120 * (isLeft ? -1 : 1), 0.7, true, true, true));
		addSequential(new MoveIntakeDownCommand());
		addParallel(new DriveStraightCommand(0.7, 83, true, true), 4);
		addSequential(new SpinIntakeInCommand(), 4);
	}
}
