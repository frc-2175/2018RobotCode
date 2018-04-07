package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class TwoCubeScaleAndSwitchAutonomous extends BaseCommandGroup {
	public TwoCubeScaleAndSwitchAutonomous(boolean isLeft) {
		addSequential(new ScaleAndGetCubeAutonomous(isLeft));
		addSequential(new WaitCommand(0.3));
		addSequential(new SpinIntakeOutCommand(), 0.5);
	}
}