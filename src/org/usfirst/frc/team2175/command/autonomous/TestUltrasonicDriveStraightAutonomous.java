package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SCurveCommandGroup;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.UltrasonicDriveStraightCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestUltrasonicDriveStraightAutonomous extends CommandGroup {
	public TestUltrasonicDriveStraightAutonomous(boolean isLeft) {
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new SCurveCommandGroup(-12, 72, 48, true, false));
		addSequential(new UltrasonicDriveStraightCommand(1, 158, 12, isLeft, false, false));
		addSequential(new DriveCurve(DrivetrainSubsystem.WIDTH_OF_BOT / 2, 55, 1, false, true));
		addSequential(new ElevatorAutonCommand(1, 60, true, true));
		addSequential(new SpinIntakeOutCommand(), 2);
	}
}
