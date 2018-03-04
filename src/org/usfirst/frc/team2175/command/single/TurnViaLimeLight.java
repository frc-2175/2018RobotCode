package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.subsystem.LimeLight;

public class TurnViaLimeLight extends BaseCommandGroup {

	public TurnViaLimeLight() {
		double midVal = 7;
		addSequential(new TurnInPlaceCommand(midVal - LimeLight.getTx(), .6, false, false));
	}

}
