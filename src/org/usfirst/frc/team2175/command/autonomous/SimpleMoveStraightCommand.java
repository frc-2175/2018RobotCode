package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.BaseCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class SimpleMoveStraightCommand extends BaseCommand {
	private DrivetrainSubsystem drivetrainSubsystem;
	private double nDy;
	private double nDx;

	public SimpleMoveStraightCommand() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		nDy = drivetrainSubsystem.getNDY();
		nDx = drivetrainSubsystem.getNDX();
	}

	@Override
	protected void initialize() {
		drivetrainSubsystem.resetAllSensors();
		drivetrainSubsystem.autonDrive(.7, .7);
	}

	@Override
	protected boolean isFinished() {
		return drivetrainSubsystem.getLeftEncoderValues() > Math.sqrt(nDy * nDy + nDx * nDx);
	}

	@Override
	protected void end() {
	}

}
