package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.BaseCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class SimpleMoveStraightCommand extends BaseCommand {

	private DrivetrainSubsystem drivetrainSubsystem;
	private double distance;

	public SimpleMoveStraightCommand() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		distance = drivetrainSubsystem.getDistance();
	}

	@Override
	protected void initialize() {
		drivetrainSubsystem.resetAllSensors();
	}

	@Override
	protected void execute() {
		drivetrainSubsystem.straightArcadeDrive(0.85);
	}

	@Override
	protected boolean isFinished() {
		return drivetrainSubsystem.getLeftEncoderValues() >= distance;
	}

	@Override
	protected void end() {
	}

}
