package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;

public class TestCurveCommandGroup extends BaseCommandGroup {
	public TestCurveCommandGroup() {
		addSequential(new DriveCurve(36, 90, 1, true, false));
		addSequential(new DriveStraightCommand(0.7, 24.0, false, false));
		addSequential(new DriveCurve(-36, 90, 1, false, true));
	}
}
