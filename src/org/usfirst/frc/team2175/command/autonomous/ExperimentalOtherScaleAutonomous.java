package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class ExperimentalOtherScaleAutonomous extends BaseCommandGroup {
	public ExperimentalOtherScaleAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(0.1));
		addSequential(new DriveStraightCommand(1, 177, true, false));
		addSequential(new DriveCurve(sign * 20, 90, 1, false, false));
		addSequential(new DriveStraightCommand(1, 150, false, false));
		addSequential(new DriveCurve(sign * -16, 60, 1, false, true));
		addSequential(new TurnInPlaceCommand(-60, 1, true, true));
		addSequential(new WaitCommand(5));
		// addSequential(new ElevatorAutonCommand(1, 60, true, true));
		// addSequential(new DriveCurve(-20, 20, .7, false, false));
		// addSequential(new SpinIntakeOutCommand(), .4);
	}
}
