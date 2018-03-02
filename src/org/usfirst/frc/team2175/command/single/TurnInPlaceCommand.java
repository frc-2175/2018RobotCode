package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class TurnInPlaceCommand extends BaseCommand {
	private final DrivetrainSubsystem drivetrainSubsystem;
	private double degrees;
	private double maxLeftSpeed;
	private double maxRightSpeed;
	private double accelerationRate;
	private boolean accelerate, decelerate;
	private final SmartDashboardInfo smartDashboardInfo;
	private final double PROPORTIONAL = 2.0 / 12.0;

	public TurnInPlaceCommand(double degrees, double maxSpeed, boolean accelerate, boolean decelerate) {
		super();
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		this.degrees = degrees;
		this.accelerate = accelerate;
		this.decelerate = decelerate;

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
			double decel = clamp(decelerate(), 0.5, 1);
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
		if (degrees > 0) {
			drivetrainSubsystem.tankDrive(leftSpeed, -rightSpeed);
		} else {
			drivetrainSubsystem.tankDrive(-leftSpeed, rightSpeed);
		}
	}

	@Override
	public boolean isFinished() {
		double gyro = (decelerate) ? drivetrainSubsystem.getGyroValueUnadjusted()
			: drivetrainSubsystem.getGyroValueAdjusted();
		double targetDiff = Math.abs(gyro) - Math.abs(degrees);
		return targetDiff > 0 && timeSinceInitialized() > .3;
	}

	private double accelerate() {
		return clamp(timeSinceInitialized() * accelerationRate, 0, 1);
	}

	private double decelerate() {
		double error = (Math.abs(degrees)) - Math.abs(drivetrainSubsystem.getGyroValueAdjusted());
		return clamp(PROPORTIONAL * error, 0, 1);
	}
}
