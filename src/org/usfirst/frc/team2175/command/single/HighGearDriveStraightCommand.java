package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

/**
 * Untested! Don't use at comp!
 */
public class HighGearDriveStraightCommand extends BaseCommand {
	private double speed, distance, accelerationRate;
	private boolean accelerate, decelerate, justTurned;
	public static final double PROPORTIONAL = 1.0 / 60.0;
	private DrivetrainSubsystem drivetrainSubsystem;
	private SmartDashboardInfo smartDashboardInfo;

	public HighGearDriveStraightCommand(double speed, double distance, boolean accelerate, boolean decelerate) {
		this(speed, distance, accelerate, decelerate, false);
	}

	public HighGearDriveStraightCommand(double speed, double distance, boolean accelerate, boolean decelerate,
		boolean justTurned) {
		this.speed = speed;
		this.distance = distance;
		this.accelerate = accelerate;
		this.decelerate = decelerate;
		this.justTurned = justTurned;
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);

		requires(drivetrainSubsystem);
	}

	@Override
	protected void init() {
		accelerationRate = smartDashboardInfo.getNumber(SmartDashboardInfo.DRIVE_STRAIGHT_ACCELERATION_RATE);
		drivetrainSubsystem.resetAllSensors();
		drivetrainSubsystem.shift(true);
	}

	@Override
	protected void execute() {
		double moveValue;
		if (decelerate) {
			moveValue = clamp(decelerate() * speed, 0.3, speed);
		} else {
			moveValue = speed;
		}
		if (accelerate) {
			moveValue *= accelerate();
		}
		if (justTurned) {
			drivetrainSubsystem.gyroAssumptionDrive(moveValue);
		} else {
			drivetrainSubsystem.straightArcadeDrive(moveValue);
		}
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
	}

	private double accelerate() {
		return clamp(timeSinceInitialized() * accelerationRate, 0, 1);
	}

	private double decelerate() {
		double error = distance - drivetrainSubsystem.getAverageDistance();
		return clamp(PROPORTIONAL * error, 0, 1);
	}
}
