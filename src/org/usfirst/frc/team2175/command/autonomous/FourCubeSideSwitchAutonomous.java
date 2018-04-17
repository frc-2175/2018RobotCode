package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.HighGearDriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeDownCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeUpCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInDriverCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class FourCubeSideSwitchAutonomous extends BaseCommandGroup {
	private final double HALF_THE_WIDTH_OF_BOT = DrivetrainSubsystem.WIDTH_OF_BOT / 2;
	private final double HALF_THE_LENGTH_OF_BOT = DrivetrainSubsystem.LENGTH_OF_BOT_WITH_BUMPERS / 2;

	public FourCubeSideSwitchAutonomous(boolean left) {
		double sign = left ? -1 : 1;
		addSequential(new HighGearDriveStraightCommand(1,
			140 + (24 - HALF_THE_WIDTH_OF_BOT - HALF_THE_LENGTH_OF_BOT) - 10, true, true));
		addSequential(new DriveCurve(HALF_THE_WIDTH_OF_BOT * -sign, 90, 1, false, false), 4);
		addSequential(new WaitCommand(0.2));
		addSequential(new DriveStraightCommand(0.8, 9, false, false), 0.6);
		addParallel(new MoveIntakeMiddleCommand());
		// addSequential(new BlendedDriveForAutonCommand(-0.8), 0.18);
		addSequential(new SpinIntakeOutCommand(), 0.3);
		addParallel(new MoveIntakeUpCommand());
		addSequential(new DriveCurve(HALF_THE_WIDTH_OF_BOT * sign, -90, 0.87, false, true), 4);
		addSequential(new WaitCommand(0.2));
		addSequential(new DriveStraightCommand(-1, -48, false, false));
		addParallel(new MoveIntakeDownCommand());
		addSequential(new TurnInPlaceCommand(47, 0.9, false, true));
		addSequential(new WaitCommand(0.2));
		addParallel(new SpinIntakeInDriverCommand(), 2);
		addSequential(new DriveStraightCommand(0.9, 24, false, false), 2);
		addParallel(new MoveIntakeUpCommand());
		addSequential(new DriveStraightCommand(-1, 4, false, false));
		addSequential(new DriveStraightCommand(0.8, 12, false, false));
		// addParallel(new MoveIntakeMiddleCommand());
		addSequential(new SpinIntakeOutCommand(), 0.4);
		addSequential(new WaitCommand(0.2));
		addSequential(new DriveStraightCommand(-0.8, 6, false, false));
		addSequential(new TurnInPlaceCommand(35, 0.9, false, true));
		addParallel(new DriveStraightCommand(0.8, 18, false, false));
		addSequential(new SpinIntakeInDriverCommand(), 2);
		// addSequential(new DriveStraightCommand(0.9, 10, true, true));
		// addParallel(new MoveIntakeMiddleCommand());
		// addSequential(new SpinIntakeOutSlowCommand(), 0.4);
	}
}
