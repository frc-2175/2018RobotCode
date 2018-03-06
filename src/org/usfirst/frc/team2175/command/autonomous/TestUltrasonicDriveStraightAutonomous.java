package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.UltrasonicDriveStraightCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class TestUltrasonicDriveStraightAutonomous extends BaseCommandGroup {
	public TestUltrasonicDriveStraightAutonomous(boolean isLeft) {
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new UltrasonicDriveStraightCommand(1, 217, 22, isLeft, false, false));
		addSequential(new DriveCurve(DrivetrainSubsystem.WIDTH_OF_BOT / 2, 45, 1, false, true));
		addSequential(new ElevatorAutonCommand(1, 60, true, true));
		addSequential(new SpinIntakeOutCommand(), 2);
	}
}
