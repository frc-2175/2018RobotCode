package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class ArcadeDriveDefaultCommand extends BaseCommand {
	private DrivetrainSubsystem drivetrainSubsystem;
	private DryverStation driverStation;
	
	public ArcadeDriveDefaultCommand() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		driverStation = ServiceLocator.get(DryverStation.class);
		requires(drivetrainSubsystem);
	}
	@Override
	protected void execute() {
		drivetrainSubsystem.robotDrive(driverStation.getMoveValue(), driverStation.getTurnValue());
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	@Override
	protected void end() {
		drivetrainSubsystem.stopAllMotors();
	}
}
