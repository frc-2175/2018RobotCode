package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeUpCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInCommand;

public class SideScaleOppositeSwitch extends BaseCommandGroup {
	public SideScaleOppositeSwitch(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		addSequential(new ScaleAndGetCubeAutonomous(isLeft));
		addParallel(new MoveIntakeUpCommand(), 1);
		addSequential(new SpinIntakeInCommand(), 1);
		addSequential(new DriveStraightCommand(-0.9, 6, false, false));
		// addSequential(new TurnInPlaceCommand(53 * -sign, 0.9, true, true));
	}
}
