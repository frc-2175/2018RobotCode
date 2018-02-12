package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;

public class DriveCurveAutonomous extends BaseCommandGroup {
	public DriveCurveAutonomous() {
		addSequential(new DriveCurve(48, 90, .7));
	}
}
