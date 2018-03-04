package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.GyroDriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class ExperimentalOtherScaleAutonomous extends BaseCommandGroup {
	public ExperimentalOtherScaleAutonomous() {
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(0.1));
		addSequential(new GyroDriveStraightCommand(1, 175, true, false));
		addSequential(new DriveCurve(20, 90, 1, false, false));
		addSequential(new GyroDriveStraightCommand(1, 250, false, true));
		// addSequential(new TurnInPlaceCommand(90, 1, false, true), 2);
		// addSequential(new GyroDriveStraightCommand(1, 160, true, true));
		// addSequential(new TurnInPlaceCommand(90, 1, false, true), 2);
		// addSequential(new WaitCommand(0.2));
		// addSequential(new ElevatorAutonCommand(1, 60, true, true));
		// addSequential(new DriveCurve(-20, 20, .7, false, false));
		// addSequential(new SpinIntakeOutCommand(), .4);
		// addSequential(new DriveCurve(20, 20, -.7, false, false));
		// addSequential(new ElevatorAutonCommand(-1, -61, false, true));
	}
}
