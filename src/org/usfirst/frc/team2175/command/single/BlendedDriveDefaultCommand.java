package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class BlendedDriveDefaultCommand extends BaseCommand {
	private final DrivetrainSubsystem drivetrainSubsystem;
	private final DryverStation driverStation;

	public BlendedDriveDefaultCommand() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		driverStation = ServiceLocator.get(DryverStation.class);
		requires(drivetrainSubsystem);
	}

	@Override
	protected void execute() {
		drivetrainSubsystem.blendedDrive(driverStation.getMoveValue(), driverStation.getTurnValue());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void onEnd() {
		drivetrainSubsystem.stopAllMotors();
	}
}
