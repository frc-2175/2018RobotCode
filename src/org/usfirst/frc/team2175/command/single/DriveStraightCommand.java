package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class DriveStraightCommand extends BaseCommand {
	private double speed, distance, accelerationRate;
	private boolean accelerate, decelerate;
	public static final double PROPORTIONAL = 1.0 / 36.0;
	private final DrivetrainSubsystem drivetrainSubsystem;
	private final SmartDashboardInfo smartDashboardInfo;
	private final boolean useTurnCorrection;

	public DriveStraightCommand(double speed, double distance, boolean accelerate, boolean decelerate) {
		this(speed, distance, accelerate, decelerate, true);
	}

	public DriveStraightCommand(double speed, double distance, boolean accelerate, boolean decelerate,
		boolean useTurnCorrection) {
		this.speed = speed;
		this.distance = distance;
		this.accelerate = accelerate;
		this.decelerate = decelerate;
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		this.useTurnCorrection = useTurnCorrection;
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
			moveValue = Math.signum(speed) * clamp(decelerate() * Math.abs(speed), 0.5, Math.abs(speed));
		} else {
			moveValue = speed;
		}
		if (accelerate) {
			moveValue *= accelerate();
		}
		drivetrainSubsystem.gyroCorrectAssumptionDrive(moveValue, timeSinceInitialized(), useTurnCorrection);
	}

	@Override
	protected boolean isFinished() {
		return timeSinceInitialized() > 0.5 && Math.abs(drivetrainSubsystem.getAverageDistance()) >= Math.abs(distance);
	}

	@Override
	protected void onEnd() {
		if (decelerate) {
			drivetrainSubsystem.stopAllMotors();
		}
		drivetrainSubsystem.turned(false);
	}

	private double accelerate() {
		return clamp(timeSinceInitialized() * accelerationRate, 0, 1);
	}

	private double decelerate() {
		double error = distance - drivetrainSubsystem.getAverageDistance();
		return clamp(PROPORTIONAL * error, 0, 1);
	}
}
