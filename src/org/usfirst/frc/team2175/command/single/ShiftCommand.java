package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftCommand extends Command {
	private DrivetrainSubsystem drivetrainSubsystem;

	public ShiftCommand() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
	}

	@Override
	protected void initialize() {
		drivetrainSubsystem.shift(true);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrainSubsystem.shift(false);
	}

}
