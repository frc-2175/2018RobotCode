package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class OtherSideScaleAutonomous extends BaseCommandGroup {
	public OtherSideScaleAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		double halfWidthOfBot = DrivetrainSubsystem.WIDTH_OF_BOT / 2;
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new WaitCommand(0.1));
		addSequential(new DriveStraightCommand(1, 198 - halfWidthOfBot, true, false));
		addSequential(new DriveCurve(halfWidthOfBot * sign, 90, 1, false, true));
		addSequential(new WaitCommand(0.4));
		double val = (isLeft) ? 180 : 200;
		addSequential(new DriveStraightCommand(1, val, true, false));
		addSequential(new DriveCurve(-halfWidthOfBot * sign, 90, 1, false, false));
		addSequential(new WaitCommand(0.4));
		addSequential(new DriveCurve(48 * -sign, 45, 1, false, true));
		// addSequential(new TurnInPlaceCommand(-sign * 90, 0.7, true, true, false));
		// addSequential(new ElevatorAutonCommand(1, 60, true, true));
		// double val2 = (isLeft) ? 46 : 20;
		// addSequential(new DriveStraightCommand(0.7, val2, true, true));
		// addSequential(new SpinIntakeOutCommand(), .4);
	}
}