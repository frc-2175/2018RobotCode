package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.BaseCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class KurveDriveCommand extends BaseCommand {
	private DrivetrainSubsystem drivetrainSubsystem;
	private boolean radiusDetermined;
	private double radius;

	private double thetaNeeded;

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
		if (secondTime) {
			radiusDetermined = true;
			thetaNeeded = drivetrainSubsystem.getThetaNeeded();
		} else {
			radiusDetermined = false;
		}
	}

	@Override
	protected void execute() {
		double leftEnc = drivetrainSubsystem.getLeftEncoderValues();
		double rightEnc = drivetrainSubsystem.getRightEncoderValues();
		double gyroVal = drivetrainSubsystem.getGryroValue() * Math.PI / 180;

		if (secondTime) {
			if (dx > 0) {
				drivetrainSubsystem.autonDrive(.7 / ratio, .7);
			} else {
				drivetrainSubsystem.autonDrive(.7, .7 / ratio);
			}
		} else if (!radiusDetermined && (gyroVal > Math.PI / 4 || abs(leftEnc) > 600 || abs(rightEnc) > 600)) {
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
					drivetrainSubsystem.setNDY(nDy);
					drivetrainSubsystem.setNDX(nDx);
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
		return abs(abs(drivetrainSubsystem.getGryroValue()) - abs(thetaNeeded)) < Math.PI / 6;
	}

	@Override
	protected void end() {
	}
}
