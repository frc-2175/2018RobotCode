package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.ElevatorAutonCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.UltrasonicDriveStraightCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleAutonomous extends CommandGroup {
	public ScaleAutonomous(boolean isLeft) {
		int sign = isLeft ? 1 : -1;
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new UltrasonicDriveStraightCommand(1, 230, 22, isLeft, false, false));
		addSequential(new DriveCurve(DrivetrainSubsystem.WIDTH_OF_BOT / 2 * sign, 40, 0.8, false, false));
		addSequential(new ElevatorAutonCommand(1, 60, true, true));
		addSequential(new SpinIntakeOutCommand(), 2);
	}
}
