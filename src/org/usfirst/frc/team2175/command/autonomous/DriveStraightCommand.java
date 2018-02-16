package org.usfirst.frc.team2175.command.autonomous;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.BaseCommand;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class DriveStraightCommand extends BaseCommand {
	private double speed, distance, distanceTraveled, accelerationRate;
	private boolean accelerate, decelerate;
	public static final double PROPORTIONAL = 1.0 / 12.0;
	private DrivetrainSubsystem drivetrainSubsystem;
	private SmartDashboardInfo smartDashboardInfo;

	public DriveStraightCommand(double speed, double distance, boolean accelerate, boolean decelerate) {
		this.speed = speed;
		this.distance = distance;
		this.accelerate = accelerate;
		this.decelerate = decelerate;
		distanceTraveled = 0;
		accelerationRate = 0;
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);

		requires(drivetrainSubsystem);
	}

	@Override
	protected void initialize() {
		distanceTraveled = 0;
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
		drivetrainSubsystem.autonDrive(moveValue, moveValue);
		distanceTraveled = drivetrainSubsystem.getDistance();
	}

	@Override
	protected boolean isFinished() {
		return distanceTraveled >= distance;
	}

	@Override
	protected void end() {
		if (decelerate) {
			drivetrainSubsystem.stopAllMotors();
		}
	}

	private double accelerate() {
		return clamp(timeSinceInitialized() * accelerationRate, 0, 1);
	}

	private double decelerate() {
		double error = distance - drivetrainSubsystem.getDistance();
		return clamp(PROPORTIONAL * error, 0, 1);
	}
}
