package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;

public class KurveDriveRightSideOfSwitch extends BaseCommandGroup {
	public KurveDriveRightSideOfSwitch() {
		double dy = 5000;
		double dx = 5000;
		addSequential(new KurveDriveCommand(dy, dx, 4), 10);
		addSequential(new SimpleMoveStraightCommand(), 10);
		addSequential(new KurveDriveCommand(dy, dx, 4, true), 10);
	}
}
