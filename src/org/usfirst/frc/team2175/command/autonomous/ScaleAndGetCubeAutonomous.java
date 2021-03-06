package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInDriverCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class ScaleAndGetCubeAutonomous extends BaseCommandGroup {
	public ScaleAndGetCubeAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		double secondCubeTurnAngle = 87;
		secondCubeTurnAngle += isLeft ? 4 : 0;
		addSequential(new ScaleAutonomous(isLeft));
		addParallel(new TurnInPlaceCommand(secondCubeTurnAngle * sign, 0.85, true, true, true));
		addSequential(new ElevatorAutonCommand(-1, 0, false, true));
		addSequential(new WaitCommand(0.2));
		addParallel(new DriveStraightCommand(1, 76, true, true), 2);
		addSequential(new SpinIntakeInDriverCommand(), 2.3);
	}
}
