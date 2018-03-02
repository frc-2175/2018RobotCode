package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class UltrasonicDriveStraightCommand extends BaseCommand {
	private double speed, distance, accelerationRate, distanceFromWall;
	private boolean accelerate, decelerate, isLeftSide;
	public static final double PROPORTIONAL = 1.0 / 12.0;
	private final DrivetrainSubsystem drivetrainSubsystem;
	private final SmartDashboardInfo smartDashboardInfo;

	public UltrasonicDriveStraightCommand(double speed, double distance, double distanceFromWall, boolean isLeftSide,
		boolean accelerate, boolean decelerate) {
		super();
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
	protected void initialize() {
		super.initialize();
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
		drivetrainSubsystem.blendedDrive(moveValue, (wantedDirection) / 30);
	}

	@Override
	protected boolean isFinished() {
		return timeSinceInitialized() > 0.5 && Math.abs(drivetrainSubsystem.getAverageDistance()) >= Math.abs(distance);
	}

	@Override
	protected void end() {
		super.end();
		if (decelerate) {
			drivetrainSubsystem.stopAllMotors();
		}
	}

	private double accelerate() {
		return clamp(timeSinceInitialized() * accelerationRate, 0, 1);
	}

	private double decelerate() {
		double error = distance;
		return clamp(PROPORTIONAL * error, 0, 1);
	}
}
