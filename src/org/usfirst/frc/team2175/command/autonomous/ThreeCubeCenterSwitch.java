package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeDownCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeUpCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInDriverCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class ThreeCubeCenterSwitch extends BaseCommandGroup {
	public ThreeCubeCenterSwitch(boolean isLeft) {
		double sign = isLeft ? -1 : 1;
		double distance = 78;
		double secondCubeTurn = -73;
		double thirdCubeTurn = -110;
		secondCubeTurn += isLeft ? -5 : 0;
		distance += isLeft ? -6 : 0;
		addSequential(new CenterSwitchAutonomous(isLeft));
		addParallel(new ElevatorAutonCommand(-0.8, 0, false, false), 0.2);
		addSequential(new DriveStraightCommand(-0.9, 6, true, true));
		addSequential(new TurnInPlaceCommand(sign * secondCubeTurn, 0.8, true, true));
		addSequential(new MoveIntakeDownCommand());
		addSequential(new WaitCommand(0.18));
		addParallel(new DriveStraightCommand(0.9, distance, true, true));
		addSequential(new SpinIntakeInDriverCommand(), 1.5);
		addParallel(new SpinIntakeInCommand(), 2);
		addParallel(new MoveIntakeUpCommand());
		addSequential(new DriveStraightCommand(-0.9, 24, true, true));
		secondCubeTurn += isLeft ? -5 : 0;
		addSequential(new TurnInPlaceCommand(-sign * secondCubeTurn, 0.8, true, true));
		addSequential(new WaitCommand(0.15));
		addParallel(new MoveIntakeMiddleCommand());
		addSequential(new SpinIntakeOutCommand(), 1);
		addParallel(new ElevatorAutonCommand(-0.8, 0, false, false), 0.2);
		addSequential(new TurnInPlaceCommand(sign * thirdCubeTurn, 0.8, true, true));
		addParallel(new DriveStraightCommand(1, distance, true, true));
		addSequential(new SpinIntakeInDriverCommand(), 2);
		addParallel(new SpinIntakeInCommand(), 2);
		addParallel(new MoveIntakeUpCommand());
		addSequential(new DriveStraightCommand(-0.9, distance, true, true));
		addSequential(new TurnInPlaceCommand(-sign * thirdCubeTurn, 0.8, true, true));
		addSequential(new WaitCommand(0.15));
		addParallel(new MoveIntakeMiddleCommand());
		addSequential(new SpinIntakeOutCommand(), 1);

	}
}