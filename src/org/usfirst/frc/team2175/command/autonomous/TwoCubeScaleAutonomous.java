package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

public class TwoCubeScaleAutonomous extends BaseCommandGroup {
	public TwoCubeScaleAutonomous(boolean isLeft) {
		addSequential(new ScaleAndGetCubeAutonomous(isLeft));
		addSequential(new DriveStraightCommand(-0.7, 83, true, true));
		addSequential(new TurnInPlaceCommand(120 * (isLeft ? 1 : -1), 0.7, true, true, true));
		addSequential(new ElevatorAutonCommand(0.8, 50, true, true));
		addSequential(new DriveCurve((isLeft ? 1 : -1) * 20, 35, -.7, false, false));
		addSequential(new SpinIntakeOutCommand(), 2);
	}
}
