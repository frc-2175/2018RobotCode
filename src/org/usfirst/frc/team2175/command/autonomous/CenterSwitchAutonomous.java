package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SCurveCommandGroup;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterSwitchAutonomous extends CommandGroup {
	public CenterSwitchAutonomous(boolean isLeft) {
		double sign = (isLeft) ? -1 : 1;
		addSequential(new SCurveCommandGroup(sign * (40), 130 - 39.25, 18));
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new SpinIntakeOutCommand(), 2);
	}
}
