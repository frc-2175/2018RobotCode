package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;

public class CrossBaselineTimeBasedAutonomous extends BaseCommandGroup {

	public CrossBaselineTimeBasedAutonomous() {
		addSequential(new BlendedDriveForAutonCommand(0.7), 5);
	}

}
