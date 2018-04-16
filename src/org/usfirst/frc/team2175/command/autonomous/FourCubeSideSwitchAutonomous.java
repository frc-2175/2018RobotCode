package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.BlendedDriveForAutonCommand;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeUpCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutSlowCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class FourCubeSideSwitchAutonomous extends BaseCommandGroup {
	private final double HALF_THE_WIDTH_OF_BOT = DrivetrainSubsystem.WIDTH_OF_BOT / 2;
	private final double HALF_THE_LENGTH_OF_BOT = DrivetrainSubsystem.LENGTH_OF_BOT_WITH_BUMPERS / 2;

	public FourCubeSideSwitchAutonomous(boolean left) {
		double sign = left ? -1 : 1;
		addSequential(new DriveStraightCommand(0.9, 140 + (24 - HALF_THE_WIDTH_OF_BOT - HALF_THE_LENGTH_OF_BOT) - 10,
			true, false));
		addSequential(new DriveCurve(HALF_THE_WIDTH_OF_BOT * -sign, 90, 1, false, false), 4);
		addParallel(new MoveIntakeMiddleCommand());
		addSequential(new BlendedDriveForAutonCommand(-0.8), 0.18);
		addSequential(new SpinIntakeOutSlowCommand(), 1);
		addParallel(new MoveIntakeUpCommand());
		addSequential(new DriveCurve(HALF_THE_WIDTH_OF_BOT * sign, -90, 1, false, false), 4);
		addSequential(new WaitCommand(0.2));
		// addSequential(new TurnInPlaceCommand(45, 0.8, false, true));
		// addSequential(new WaitCommand(0.2));
		// addSequential(new DriveStraightCommand(0.9, 10, true, true));
	}
}
