package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class UltrasonicDriveStraightCommand extends BaseCommand {
	private double speed, distance, accelerationRate, distanceFromWall;
	private boolean accelerate, decelerate, isLeftSide;
	public static final double DECELERATE_PROPORTIONAL = 1 / 36.0;
	public static final double CORRECTION_PROPORTIONAL = 0.15 / 12.0;
	public static final double GYRO_CORRECTION_RANGE = 3;
	private final DrivetrainSubsystem drivetrainSubsystem;
	private final SmartDashboardInfo smartDashboardInfo;

	public UltrasonicDriveStraightCommand(double speed, double distance, double distanceFromWall, boolean isLeftSide,
		boolean accelerate, boolean decelerate) {
		this.speed = speed;
		this.distance = distance;
		this.accelerate = accelerate;
		this.decelerate = decelerate;
		this.distanceFromWall = distanceFromWall;
		this.isLeftSide = isLeftSide;
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);

		requires(drivetrainSubsystem);
	}

	@Override
	protected void init() {
		accelerationRate = smartDashboardInfo.getNumber(SmartDashboardInfo.DRIVE_STRAIGHT_ACCELERATION_RATE);
		drivetrainSubsystem.resetAllSensors();
	}

	@Override
	protected void execute() {
		double moveValue;
		if (decelerate) {
			moveValue = clamp(decelerate() * speed, 0.5, speed);
		} else {
			moveValue = speed;
		}
		if (accelerate) {
			moveValue *= accelerate();
		}
		double wantedDirection;
		if (isLeftSide) {
			wantedDirection = (distanceFromWall - drivetrainSubsystem.getLeftUltraVal());
		} else {
			wantedDirection = (distanceFromWall - drivetrainSubsystem.getRightUltraVal());
		}

		if (drivetrainSubsystem.getAverageDistance() < 24) {
			wantedDirection = 0;
		}

		if (drivetrainSubsystem.getGyroValueUnadjusted() > GYRO_CORRECTION_RANGE && wantedDirection > 0) {
			wantedDirection = 0;
		} else if (drivetrainSubsystem.getGyroValueUnadjusted() < -GYRO_CORRECTION_RANGE && wantedDirection < 0) {
			wantedDirection = 0;
		}
		drivetrainSubsystem.blendedDrive(moveValue, clamp(wantedDirection * CORRECTION_PROPORTIONAL, -0.15, 0.15));
	}

	@Override
	protected boolean isFinished() {
		return timeGreatEnough() && Math.abs(drivetrainSubsystem.getAverageDistance()) >= Math.abs(distance);
	}

	@Override
	protected void onEnd() {
		if (decelerate) {
			drivetrainSubsystem.stopAllMotors();
		}
	}

	private double accelerate() {
		return clamp(timeSinceInitialized() * accelerationRate, 0, 1);
	}

	private double decelerate() {
		double error = distance - drivetrainSubsystem.getAverageDistance();
		return clamp(DECELERATE_PROPORTIONAL * error, 0, 1);
	}
}
