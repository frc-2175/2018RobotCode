package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

public class ScaleAutonomous extends BaseCommandGroup {
	public ScaleAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new DriveStraightCommand(1, 268, true, true));
		addSequential(new TurnInPlaceCommand(true, 45 * sign, 0.85, true, true, true));
		addSequential(new ElevatorAutonCommand(1, 60, true, true), 3);
		addSequential(new SpinIntakeOutCommand(1), .5);
	}
}
