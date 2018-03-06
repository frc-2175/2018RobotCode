package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SCurveCommandGroup;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutSlowCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CenterSwitchAutonomous extends CommandGroup {
	private final double WIDTH_OF_SWITCH = 153.5;
	private final double X = (WIDTH_OF_SWITCH / 2 - DrivetrainSubsystem.WIDTH_OF_BOT / 2) - 5;

	// TODO (ben): Put diagrams in the project so we remember the math we did.
	public CenterSwitchAutonomous(boolean isLeft) {
		double sign = (isLeft) ? -1 : 1;
		addSequential(
			new SCurveCommandGroup(sign * X, 139 - DrivetrainSubsystem.LENGTH_OF_BOT_WITH_BUMPERS, 30, true, true), 6);
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(0.2));
		addSequential(new SpinIntakeOutSlowCommand(), 2);
	}
}
