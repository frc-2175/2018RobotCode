package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.GyroDriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class OtherSideScaleAutonomous extends BaseCommandGroup {
	public OtherSideScaleAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(0.1));
		addSequential(new GyroDriveStraightCommand(1, 228, true, true));
		addSequential(new TurnInPlaceCommand(sign * 90, 0.7, true, true, true));
		addSequential(new WaitCommand(0.5));
		double val = (isLeft) ? 180 : 200;
		addSequential(new GyroDriveStraightCommand(1, val, true, true));
		addSequential(new TurnInPlaceCommand(-sign * 90, 0.7, true, true, false));
		addSequential(new ElevatorAutonCommand(1, 60, true, true));
		double val2 = (isLeft) ? 52 : 20;
		addSequential(new GyroDriveStraightCommand(0.7, val2, true, true));
		addSequential(new SpinIntakeOutCommand(), .4);
	}
}
