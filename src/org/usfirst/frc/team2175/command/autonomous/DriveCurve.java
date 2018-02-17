package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.BaseCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class DriveCurve extends BaseCommand {
	private DrivetrainSubsystem drivetrainSubsystem;
	private double radians;
	private double leftSpeed;
	private double rightSpeed;
	private double widthOfBot = 26.125;

	public DriveCurve(double radius, double degrees, double maxSpeed) {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		radians = Math.toRadians(degrees);
		double r2 = (2 * radius - widthOfBot);
		double r1 = (2 * radius + widthOfBot);
		boolean radiusPositive = radius > 0;
		if (radiusPositive) {
			double wheelRatio = r2 / r1;
			leftSpeed = maxSpeed;
			rightSpeed = wheelRatio * maxSpeed;
		} else {
			double wheelRatio = r1 / r2;
			leftSpeed = wheelRatio * maxSpeed;
			rightSpeed = maxSpeed;
		}

		requires(drivetrainSubsystem);
	}

	@Override
	public void initialize() {
		super.initialize();
		drivetrainSubsystem.resetAllSensors();
	}

	@Override
	public void execute() {
		if (radians > 0) {
			drivetrainSubsystem.tankDrive(leftSpeed, rightSpeed);
		} else {
			drivetrainSubsystem.tankDrive(-leftSpeed, -rightSpeed);
		}
	}

	@Override
	public boolean isFinished() {
		double targetDiff = Math.toRadians(Math.abs(drivetrainSubsystem.getGyroValue())) - Math.abs(radians);
		return targetDiff > 0;
	}
}
