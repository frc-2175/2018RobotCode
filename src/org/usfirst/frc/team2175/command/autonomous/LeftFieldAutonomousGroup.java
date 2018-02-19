package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.LambdaConditionalCommand;
import org.usfirst.frc.team2175.control.DryverStation;

public class LeftFieldAutonomousGroup extends BaseCommandGroup {
	private final DryverStation driverStation;

	public LeftFieldAutonomousGroup() {
		driverStation = ServiceLocator.get(DryverStation.class);

		addSequential(new LambdaConditionalCommand(() -> driverStation.isSwitchLeft(),
			new LambdaConditionalCommand(() -> driverStation.isScaleLeft() /*      */,
				new LeftSwitchAndScaleAutonomous(), new LeftSwitchAutonomous()),
			new LambdaConditionalCommand(() -> driverStation.isScaleLeft(), new LeftScaleAutonomous(),
				new DriveForwardAutonomous())));
	}

}
