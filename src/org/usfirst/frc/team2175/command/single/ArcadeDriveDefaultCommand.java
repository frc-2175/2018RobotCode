package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class ArcadeDriveDefaultCommand extends BaseCommand {
	private DrivetrainSubsystem drivetrainSubsystem;
	private DryverStation driverStation;
	private double possibleChangePerSecond = .2;
	private double topSpeed = 1;
	private double lastTime;
	private double currentMoveValue;

	public ArcadeDriveDefaultCommand() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		driverStation = ServiceLocator.get(DryverStation.class);
		requires(drivetrainSubsystem);
	}

	@Override
	protected void initialize() {
		lastTime = System.currentTimeMillis();
		currentMoveValue = 0;
	}

	@Override
	protected void execute() {
		double possibleChangeFromTime = (System.currentTimeMillis() - lastTime) / 1000 * possibleChangePerSecond;

		double targetSpeed = driverStation.getMoveValue() * topSpeed;

		double valueChange = targetSpeed - currentMoveValue;
		double wantedMoveValue;
		if (Math.abs(valueChange) > possibleChangeFromTime) {
			wantedMoveValue = currentMoveValue + Math.signum(valueChange) * possibleChangeFromTime;
		} else {
			wantedMoveValue = targetSpeed;
		}

		drivetrainSubsystem.robotDrive(wantedMoveValue, driverStation.getTurnValue());

		currentMoveValue = wantedMoveValue;
		lastTime = System.currentTimeMillis();
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
