package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.BaseCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class KurveDriveCommand extends BaseCommand {
	private DrivetrainSubsystem drivetrainSubsystem;
	private boolean radiusDetermined;
	private double radius;

	private double thetaNeeded;

	private final double maxSpeed = 0.9;
	private boolean secondTime;
	private double ratio;
	private double dy;
	private double dx;

	public KurveDriveCommand(double dy, double dx, double ratio) {
		kurve(dy, dx, ratio, false);
	}

	public KurveDriveCommand(double dy, double dx, double ratio, boolean secondTime) {
		kurve(dy, dx, ratio, secondTime);
	}

	private void kurve(double dy, double dx, double ratio, boolean secondTime) {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		this.dy = dy;
		this.dx = dx;
		this.ratio = ratio;
		this.secondTime = secondTime;
	}

	@Override
	protected void initialize() {
		drivetrainSubsystem.resetAllSensors();
		radiusDetermined = secondTime;
		if (secondTime) {
			thetaNeeded = drivetrainSubsystem.getThetaNeeded();
		}
	}

	@Override
	protected void execute() {
		double leftEnc = drivetrainSubsystem.getLeftEncoderDistance();
		double rightEnc = drivetrainSubsystem.getRightEncoderDistance();
		double gyroVal = Math.toRadians(drivetrainSubsystem.getGyroValue());

		if (secondTime) {
			if (dx > 0) {
				drivetrainSubsystem.autonDrive(maxSpeed / ratio, maxSpeed);
			} else {
				drivetrainSubsystem.autonDrive(maxSpeed, maxSpeed / ratio);
			}

		} else if (!radiusDetermined && (gyroVal > Math.PI / 6)) {
			radius = (leftEnc > rightEnc) ? leftEnc / gyroVal : rightEnc / gyroVal;
			radiusDetermined = true;
			double pi = Math.PI;
			for (double theta = 0; theta <= 2 * pi; theta += pi / 12) {
				double nDy = dy - 2 * radius * Math.sin(Math.signum(rightEnc) * abs(gyroVal));
				double nDx = dx - 2 * radius * Math.signum(abs(rightEnc) - abs(leftEnc)) * (Math.cos(theta) - 1);
				if (abs(abs(nDy / nDx) - abs((Math.sin(theta + pi / 24) - Math.sin(theta))
					/ (-Math.cos(theta + pi / 24) + Math.cos(theta)))) < pi / 12) {
					thetaNeeded = theta;
					drivetrainSubsystem.setThetaNeeded(theta);
					drivetrainSubsystem.setDistance(Math.sqrt(nDy * nDy + nDx + nDx));
					break;
				}
			}
		}

		if (dx > 0) {
			drivetrainSubsystem.autonDrive(.7, .7 / ratio);
		} else {
			drivetrainSubsystem.autonDrive(.7 / ratio, .7);
		}

	}

	@Override
	protected boolean isFinished() {
		return abs(abs(drivetrainSubsystem.getGyroValue()) - abs(thetaNeeded)) < Math.PI / 6;
	}

	@Override
	protected void end() {
	}
}
