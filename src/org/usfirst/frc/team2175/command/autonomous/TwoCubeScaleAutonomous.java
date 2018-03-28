package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

public class TwoCubeScaleAutonomous extends BaseCommandGroup {
	public TwoCubeScaleAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		addSequential(new ScaleAndGetCubeAutonomous(isLeft));
		addParallel(new SpinIntakeInCommand(), 1);
		addSequential(new DriveStraightCommand(-0.85, 60, true, true));
		addParallel(new TurnInPlaceCommand(-80 * sign, 0.85, true, true, true));
		addSequential(new ElevatorAutonCommand(1, 60, true, true));
		addSequential(new SpinIntakeOutCommand(), .5);
		addSequential(new ElevatorAutonCommand(-1, 0, false, true));
	}
}
