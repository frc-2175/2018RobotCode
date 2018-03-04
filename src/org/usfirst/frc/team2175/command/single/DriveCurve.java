package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class DriveCurve extends BaseCommand {
	private final DrivetrainSubsystem drivetrainSubsystem;
	private double radians;
	private double maxLeftSpeed;
	private double maxRightSpeed;
	private double accelerationRate;
	private double radius;
	private boolean accelerate, decelerate;
	private final SmartDashboardInfo smartDashboardInfo;
	private final double PROPORTIONAL = 1.0 / 12.0;

	public DriveCurve(double radius, double degrees, double maxSpeed, boolean accelerate, boolean decelerate) {
		super();
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		radians = Math.toRadians(degrees);
		this.radius = radius;
		this.accelerate = accelerate;
		this.decelerate = decelerate;
		double r2 = (2 * radius - DrivetrainSubsystem.WIDTH_OF_BOT);
		double r1 = (2 * radius + DrivetrainSubsystem.WIDTH_OF_BOT);
		boolean radiusPositive = radius > 0;
		if (radiusPositive) {
			double wheelRatio = r2 / r1;
			maxLeftSpeed = maxSpeed;
			maxRightSpeed = wheelRatio * maxSpeed;
		} else {
			double wheelRatio = r1 / r2;
			maxLeftSpeed = wheelRatio * maxSpeed;
			maxRightSpeed = maxSpeed;
		}

		requires(drivetrainSubsystem);
	}

	@Override
	public void initialize() {
		super.initialize();
		drivetrainSubsystem.resetAllSensors();
		accelerationRate = smartDashboardInfo.getNumber(SmartDashboardInfo.DRIVE_STRAIGHT_ACCELERATION_RATE);
	}

	@Override
	public void execute() {
		double leftSpeed;
		double rightSpeed;
		if (decelerate) {
			double decel = clamp(decelerate(), 0.7, 1);
			leftSpeed = maxLeftSpeed * decel;
			rightSpeed = maxRightSpeed * decel;
		} else {
			leftSpeed = maxLeftSpeed;
			rightSpeed = maxRightSpeed;
		}
		if (accelerate) {
			leftSpeed = maxLeftSpeed * accelerate();
			rightSpeed = maxRightSpeed * accelerate();
		}
		if (radians > 0) {
			drivetrainSubsystem.tankDrive(leftSpeed, rightSpeed);
		} else {
			drivetrainSubsystem.tankDrive(-leftSpeed, -rightSpeed);
		}
	}

	@Override
	public boolean isFinished() {
		double gyro = (decelerate) ? drivetrainSubsystem.getGyroValueUnadjusted()
			: drivetrainSubsystem.getGyroValueAdjusted();
		double targetDiff = Math.toRadians(Math.abs(gyro)) - Math.abs(radians);
		return targetDiff > 0 && timeSinceInitialized() > .3;
	}

	private double accelerate() {
		return clamp(timeSinceInitialized() * accelerationRate, 0, 1);
	}

	private double decelerate() {
		double error = (Math.abs(radians * radius)) - Math.abs(drivetrainSubsystem.getAverageDistance());
		return clamp(PROPORTIONAL * error, 0, 1);
	}
}
