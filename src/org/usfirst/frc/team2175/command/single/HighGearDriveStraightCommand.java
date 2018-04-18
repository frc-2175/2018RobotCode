package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

/**
 * Untested! Don't use at comp!
 */
public class HighGearDriveStraightCommand extends BaseCommand {
	private double speed, distance;
	private boolean accelerate, decelerate;
	public static final double ACCEL_PROPORTIONAL = 5;
	public static final double DECEL_PROPORTIONAL = 1.0 / 60.0;
	private DrivetrainSubsystem drivetrainSubsystem;

	public HighGearDriveStraightCommand(double speed, double distance, boolean accelerate, boolean decelerate) {
		this.speed = speed;
		this.distance = distance;
		this.accelerate = accelerate;
		this.decelerate = decelerate;
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);

		requires(drivetrainSubsystem);
	}

	@Override
	protected void init() {
		drivetrainSubsystem.resetAllSensors();
		drivetrainSubsystem.shift(true);
	}

	@Override
	protected void execute() {
		double moveValue;
		if (decelerate) {
			moveValue = clamp(decelerate() * speed, 0.15, speed);
		} else {
			moveValue = speed;
		}
		if (accelerate) {
			moveValue *= accelerate();
		}
		drivetrainSubsystem.gyroCorrectAssumptionDrive(moveValue, timeSinceInitialized(), true);
	}

	@Override
	protected boolean isFinished() {
		return timeSinceInitialized() > 0.3 && Math.abs(drivetrainSubsystem.getAverageDistance()) >= Math.abs(distance);
	}

	@Override
	protected void onEnd() {
		drivetrainSubsystem.shift(false);
		if (decelerate) {
			drivetrainSubsystem.stopAllMotors();
		}
		drivetrainSubsystem.turned(false);
	}

	private double accelerate() {
		return clamp(timeSinceInitialized() * ACCEL_PROPORTIONAL, 0, 1);
	}

	private double decelerate() {
		double error = distance - drivetrainSubsystem.getAverageDistance();
		return clamp(DECEL_PROPORTIONAL * error, 0, 1);
	}
}
