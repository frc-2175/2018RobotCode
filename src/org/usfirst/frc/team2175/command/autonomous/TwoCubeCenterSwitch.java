package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeDownCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInDriverCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class TwoCubeCenterSwitch extends BaseCommandGroup {
	public TwoCubeCenterSwitch(boolean isLeft) {
		double sign = isLeft ? -1 : 1;
		double distance = 78;
		double secondCubeTurn = -47;
		secondCubeTurn += isLeft ? -5 : 0;
		distance += isLeft ? -6 : 0;
		addSequential(new CenterSwitchAutonomous(isLeft));
		addSequential(new DriveStraightCommand(-0.9, 52, true, true));
		addParallel(new ElevatorAutonCommand(-0.8, 0, false, false), 0.2);
		addSequential(new TurnInPlaceCommand(sign * secondCubeTurn, 0.8, true, true));
		addSequential(new WaitCommand(0.3));
		addSequential(new MoveIntakeDownCommand());
		addParallel(new DriveStraightCommand(0.9, distance, true, true));
		addSequential(new SpinIntakeInDriverCommand(), 2.5);
		addParallel(new SpinIntakeInCommand(), 1);
		addSequential(new DriveStraightCommand(-0.9, 48, true, true));
		addSequential(new TurnInPlaceCommand(sign * 50, 0.8, true, true));
		addSequential(new WaitCommand(0.3));
		addSequential(new DriveStraightCommand(1, 65, true, true), 1.8);
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new SpinIntakeOutCommand(), 1);

	}
}