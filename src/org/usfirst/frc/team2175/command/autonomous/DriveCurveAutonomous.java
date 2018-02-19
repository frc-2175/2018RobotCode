package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;

public class DriveCurveAutonomous extends BaseCommandGroup {
	public DriveCurveAutonomous() {
		addSequential(new DriveCurve(48, 90, .7));
	}
}
