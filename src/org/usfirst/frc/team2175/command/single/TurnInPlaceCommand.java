package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class TurnInPlaceCommand extends BaseCommand {
	private final DrivetrainSubsystem drivetrainSubsystem;
	private double degrees;
	private double accelerationRate;
	private boolean accelerate, decelerate, resetGyro;
	private double speed;
	private final SmartDashboardInfo smartDashboardInfo;
	private final double PROPORTIONAL = 2.0 / 12.0;
	private boolean useAdjusted;

	public TurnInPlaceCommand(double degrees, double maxSpeed, boolean accelerate, boolean decelerate) {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		this.degrees = degrees;
		this.speed = maxSpeed;
		this.accelerate = accelerate;
		this.decelerate = decelerate;
		useAdjusted = true;

		requires(drivetrainSubsystem);
	}

	public TurnInPlaceCommand(double degrees, double maxSpeed, boolean accelerate, boolean decelerate,
		boolean resetGyro) {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		this.degrees = degrees;
		this.speed = maxSpeed;
		this.accelerate = accelerate;
		this.decelerate = decelerate;
		this.resetGyro = resetGyro;
		useAdjusted = true;

		requires(drivetrainSubsystem);
	}

	public TurnInPlaceCommand(boolean useAdjusted, double degrees, double maxSpeed, boolean accelerate,
		boolean decelerate, boolean resetGyro) {
		this(degrees, maxSpeed, accelerate, decelerate, resetGyro);
		this.useAdjusted = useAdjusted;
	}

	@Override
	public void init() {
		if (Boolean.valueOf(resetGyro) != null || resetGyro) {
			drivetrainSubsystem.resetAllSensors();
		}
		accelerationRate = smartDashboardInfo.getNumber(SmartDashboardInfo.DRIVE_STRAIGHT_ACCELERATION_RATE);
	}

	@Override
	public void execute() {
		double turnVal;
		if (decelerate) {
			turnVal = clamp(decelerate() * speed, 0.5, speed);
		} else {
			turnVal = speed;
		}
		if (accelerate) {
			turnVal *= accelerate();
		}
		drivetrainSubsystem.blendedDrive(0, Math.signum(degrees) * turnVal);
	}

	@Override
	public boolean isFinished() {
		double gyro = (useAdjusted) ? drivetrainSubsystem.getGyroValueAdjusted()
			: drivetrainSubsystem.getGyroValueUnadjusted();
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
