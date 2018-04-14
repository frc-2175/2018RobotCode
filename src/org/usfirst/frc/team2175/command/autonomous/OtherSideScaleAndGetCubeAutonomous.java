package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInDriverCommand;
import org.usfirst.frc.team2175.command.single.TurnInPlaceCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class OtherSideScaleAndGetCubeAutonomous extends BaseCommandGroup {
	public OtherSideScaleAndGetCubeAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		addSequential(new OtherSideScaleAutonomous(isLeft));
		addSequential(new DriveStraightCommand(-0.8, 12, true, true));
		addParallel(new TurnInPlaceCommand(-sign * 145, 0.9, true, true, false));
		addSequential(new ElevatorAutonCommand(-1, 0, false, true));
		addSequential(new WaitCommand(0.2));
		addParallel(new DriveStraightCommand(0.9, 37, true, true));
		addSequential(new SpinIntakeInDriverCommand(), 1.5);
	}
}
