package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.control.Driverstation;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class ArcadeDriveDefaultCommand extends BaseCommand {
	private DrivetrainSubsystem drivetrainSubsystem;
	private Driverstation driverstation;
	
	public ArcadeDriveDefaultCommand() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		driverstation = ServiceLocator.get(Driverstation.class);
	}
	@Override
	protected void execute() {
		drivetrainSubsystem.robotDrive(driverstation.getMoveValue(), driverstation.getTurnValue());
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
