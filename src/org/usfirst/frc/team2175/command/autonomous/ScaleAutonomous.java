package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.GyroDriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class ScaleAutonomous extends BaseCommandGroup {
	public ScaleAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(0.1));
		addSequential(new GyroDriveStraightCommand(1, 262, true, true));
		addSequential(new WaitCommand(0.2));
		addSequential(new ElevatorAutonCommand(1, 60, true, true), 3);
		addSequential(new WaitCommand(0.5));
		addSequential(new DriveCurve(sign * 20, 45, .7, false, false));
		addSequential(new SpinIntakeOutCommand(), .5);
		addSequential(new DriveCurve(sign * 20, 35, -.7, false, false));
		addSequential(new ElevatorAutonCommand(-1, -61, false, true));
	}
}
