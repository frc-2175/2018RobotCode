package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.GyroDriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class ExperimentalOtherScaleAutonomous extends BaseCommandGroup {
	public ExperimentalOtherScaleAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(0.1));
		addSequential(new GyroDriveStraightCommand(1, 223, true, true));
		addSequential(new TurnInPlaceCommand(sign * 76, 0.7, true, true, false));
		addSequential(new WaitCommand(0.5));
		double val = (isLeft) ? 180 : 210;
		addSequential(new GyroDriveStraightCommand(1, val, true, true));
		addSequential(new TurnInPlaceCommand(-sign * 85, 0.7, true, true, false));
		// addSequential(new ElevatorAutonCommand(1, 60, true, true));
		double val2 = (isLeft) ? 46 : 20;
		addSequential(new GyroDriveStraightCommand(0.7, val2, true, true));
		// addSequential(new SpinIntakeOutCommand(), .4);
		// addSequential(new DriveCurve(sign * 20, 90, 0.8, false, false));
		// addSequential(new DriveCurve(sign * -16, 66, 1, false, true));
	}
}
