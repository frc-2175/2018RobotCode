package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.GyroDriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeDownCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;
import org.usfirst.frc.team2175.command.single.TurnViaLimeLight;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class ExperimentalScaleAutonomous extends BaseCommandGroup {
	public ExperimentalScaleAutonomous() {
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(0.1));
		addSequential(new GyroDriveStraightCommand(1, 224, true, true));
		addSequential(new WaitCommand(0.2));
		addSequential(new ElevatorAutonCommand(1, 60, true, true));
		addSequential(new DriveCurve(20, 20, .7, false, false));
		addSequential(new SpinIntakeOutCommand(), .4);
		addSequential(new DriveCurve(20, 20, -.7, false, false));
		addSequential(new ElevatorAutonCommand(-1, -61, false, true));
		addSequential(new TurnInPlaceCommand(125, 1, false, true), 2);
		addSequential(new MoveIntakeDownCommand());
		addSequential(new TurnViaLimeLight(), .5);
		addParallel(new GyroDriveStraightCommand(1, 18, false, false), 2);
		addSequential(new SpinIntakeInCommand(), 1.3);
		addParallel(new GyroDriveStraightCommand(-.8, -5, false, false));
		addParallel(new SpinIntakeInCommand(), .3);
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(1.5));
		addSequential(new GyroDriveStraightCommand(.9, 7, false, false));
		addSequential(new SpinIntakeOutCommand(), .5);
	}
}
