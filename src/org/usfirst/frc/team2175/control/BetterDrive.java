package org.usfirst.frc.team2175.control;

public class BetterDrive {

	public static double[] getMotorValues(double xSpeed, double zRotation) {
		xSpeed = limit(xSpeed);
		xSpeed = applyDeadband(xSpeed, 0.02);

		zRotation = limit(zRotation);
		zRotation = applyDeadband(zRotation, 0.02);

		double angularPower = Math.abs(xSpeed) * zRotation;

		double leftSpeed = xSpeed + angularPower;
		double rightSpeed = xSpeed - angularPower;

		double absLeft = Math.abs(leftSpeed);
		double absRight = Math.abs(rightSpeed);

		if (absLeft > 1 || absRight > 1) {
			if (absRight < absLeft) {
				rightSpeed /= absLeft;
				leftSpeed /= absLeft;
			} else {
				leftSpeed /= absRight;
				rightSpeed /= absRight;
			}
		}
		double[] speed = new double[2];
		speed[0] = leftSpeed;
		speed[1] = rightSpeed;

		return speed;
	}

	protected static double limit(double value) {
		if (value > 1.0) {
			return 1.0;
		}
		if (value < -1.0) {
			return -1.0;
		}
		return value;
	}

	protected static double applyDeadband(double value, double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
		} else {
			return 0.0;
		}
	}
}
