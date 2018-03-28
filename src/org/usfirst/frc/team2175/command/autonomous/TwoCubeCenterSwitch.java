package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeDownCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInDriverCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class TwoCubeCenterSwitch extends BaseCommandGroup {
	public TwoCubeCenterSwitch(boolean isLeft) {
		double sign = isLeft ? -1 : 1;
		addSequential(new CenterSwitchAutonomous(isLeft));
		addParallel(new ElevatorAutonCommand(-0.8, 0, false, false), 0.2);
		addSequential(new DriveStraightCommand(-0.9, 60, true, true));
		addSequential(new TurnInPlaceCommand(sign * -47, 0.8, true, true));
		addSequential(new WaitCommand(0.3));
		addSequential(new MoveIntakeDownCommand());
		addParallel(new DriveStraightCommand(0.9, 78, true, true));
		addSequential(new SpinIntakeInDriverCommand(), 2);
		addSequential(new DriveStraightCommand(-0.9, 52, true, true));
		addSequential(new TurnInPlaceCommand(sign * 50, 0.8, true, true));
		addSequential(new WaitCommand(0.3));
		addSequential(new DriveStraightCommand(1, 72, true, true), 1.8);
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new SpinIntakeOutCommand(), 1);

	}
}