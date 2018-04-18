package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
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

		// First Cube
		addSequential(new HighGearDriveStraightCommand(1,
			140 + (24 - HALF_THE_WIDTH_OF_BOT - HALF_THE_LENGTH_OF_BOT) - 10, true, true));
		addSequential(new DriveCurve(HALF_THE_WIDTH_OF_BOT * -sign, 90, 1, false, false), 4);
		addSequential(new DriveStraightCommand(0.8, 9, false, false), 0.6);
		addParallel(new MoveIntakeMiddleCommand());
		addSequential(new SpinIntakeOutCommand(), 0.3);

		// Second Cube
		addParallel(new MoveIntakeUpCommand());
		addParallel(new ElevatorAutonCommand(-0.6, 0, false, false), 1);
		addSequential(new DriveCurve(HALF_THE_WIDTH_OF_BOT * sign, -90, 0.87, true, true), 4);
		addSequential(new WaitCommand(0.2));
		addSequential(new DriveStraightCommand(-1, -48, false, false));
		addParallel(new MoveIntakeDownCommand());
		addSequential(new WaitCommand(0.4));
		addSequential(new TurnInPlaceCommand(45, 0.9, false, true));
		addSequential(new WaitCommand(0.2));
		addParallel(new SpinIntakeInDriverCommand(), 2);
		addSequential(new DriveStraightCommand(0.8, 24, false, false), 2);
		addParallel(new MoveIntakeUpCommand());
		addSequential(new DriveStraightCommand(-1, 4, false, false));
		addSequential(new DriveStraightCommand(0.8, 12, false, false));
		addSequential(new SpinIntakeOutCommand(), 0.8);

		// Third Cube
		addParallel(new MoveIntakeDownCommand());
		addSequential(new DriveStraightCommand(-0.8, 12, false, false));
		addParallel(new ElevatorAutonCommand(-0.6, 0, false, false), 0.5);
		addSequential(new TurnInPlaceCommand(28, 0.9, false, true));
		addSequential(new WaitCommand(0.3));
		addParallel(new DriveStraightCommand(0.9, 27, false, false), 1);
		addSequential(new SpinIntakeInDriverCommand(), 1);
		addParallel(new MoveIntakeUpCommand());
		addSequential(new DriveStraightCommand(-1, 4, false, false));
		addSequential(new DriveStraightCommand(0.8, 4, false, false));
		addSequential(new TurnInPlaceCommand(-50, 0.9, false, true));
		addSequential(new DriveStraightCommand(0.8, 6, false, false));
		addSequential(new SpinIntakeOutCommand(), 0.6);
		//
		// // Fourth Cube
		// addParallel(new MoveIntakeDownCommand());
		// addSequential(new TurnInPlaceCommand(35, 0.9, false, true));
		// addParallel(new DriveStraightCommand(0.8, 12, false, false));
		// addSequential(new SpinIntakeInDriverCommand(), 1.5);
		// addParallel(new MoveIntakeUpCommand());
		// addSequential(new DriveStraightCommand(-1, 4, false, false));
		// addSequential(new DriveStraightCommand(0.8, 4, false, false));
		// addSequential(new TurnInPlaceCommand(45, 0.9, false, true));

		// addSequential(new DriveStraightCommand(-0.8, 6, false, false));
		// addSequential(new TurnInPlaceCommand(35, 0.9, false, true));
		// addParallel(new DriveStraightCommand(0.8, 18, false, false));
		// addSequential(new SpinIntakeInDriverCommand(), 2);

		// addSequential(new DriveStraightCommand(0.9, 10, true, true));
		// addParallel(new MoveIntakeMiddleCommand());
		// addSequential(new SpinIntakeOutSlowCommand(), 0.4);
	}
}
