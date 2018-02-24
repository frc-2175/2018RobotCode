package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SCurveCommandGroup;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutSlowCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CenterSwitchAutonomous extends CommandGroup {
	private final double WIDTH_OF_SWITCH = 153.5;
	private final double X = (WIDTH_OF_SWITCH / 2 - 26.125 / 2) - 5;

	public CenterSwitchAutonomous(boolean isLeft) {
		double sign = (isLeft) ? -1 : 1;
		addSequential(new SCurveCommandGroup(sign * X, 139 - 39.25, 30), 6);
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(0.2));
		addSequential(new SpinIntakeOutSlowCommand(), 2);
	}
}
