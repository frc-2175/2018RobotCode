package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.KurveDriveCommand;

public class KurveDriveRightSideOfSwitch extends BaseCommandGroup {
	public KurveDriveRightSideOfSwitch() {
		double dy = 48;
		double dx = 48;
		addSequential(new KurveDriveCommand(dy, dx, 4), 10);
		addSequential(new SimpleMoveStraightCommand(), 10);
		addSequential(new KurveDriveCommand(dy, dx, 4, true), 10);
	}
}
