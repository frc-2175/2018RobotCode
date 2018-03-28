package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.BaseCommandGroup;
import org.usfirst.frc.team2175.command.single.BlendedDriveForAutonCommand;
import org.usfirst.frc.team2175.command.single.DriveCurve;
import org.usfirst.frc.team2175.command.single.DriveStraightCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutSlowCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class TwoCubeSideSwitch extends BaseCommandGroup {
	private final double HALF_THE_WIDTH_OF_BOT = DrivetrainSubsystem.WIDTH_OF_BOT / 2;

	public TwoCubeSideSwitch(boolean left) {
		double sign = left ? 1 : -1;
		addSequential(new SideSwitchAutonomous(left));
		addSequential(new DriveStraightCommand(-0.9, 20, true, false));
		addSequential(new DriveCurve(-HALF_THE_WIDTH_OF_BOT * sign, -90, 1, false, false), 4);
		addSequential(new MoveIntakeMiddleCommand());
		addSequential(new BlendedDriveForAutonCommand(-0.6), 0.25);
		addSequential(new WaitCommand(0.2));
		addSequential(new SpinIntakeOutSlowCommand(), 2);
	}
}
