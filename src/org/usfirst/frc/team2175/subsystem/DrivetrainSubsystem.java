package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.SolenoidWrapper;
import org.usfirst.frc.team2175.VirtualSpeedController;
import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;

public class DrivetrainSubsystem extends BaseSubsystem {
	private final RobotInfo robotInfo;
	private final SmartDashboardInfo smartDashboardInfo;
	private final MotorWrapper leftMaster;
	private final MotorWrapper leftSlaveOne;
	private final MotorWrapper leftSlaveTwo;
	private final MotorWrapper rightMaster;
	private final MotorWrapper rightSlaveOne;
	private final MotorWrapper rightSlaveTwo;
	private final SolenoidWrapper driveShifters;
	private final DifferentialDrive robotDrive;
	private static VirtualSpeedController leftVirtualSpeedController = new VirtualSpeedController();
	private static VirtualSpeedController rightVirtualSpeedController = new VirtualSpeedController();
	private static DifferentialDrive virtualRobotDrive = new DifferentialDrive(leftVirtualSpeedController,
		rightVirtualSpeedController);

	private static final double INCHES_PER_TICK = (Math.PI * 6.25) / (15.32 * 1024) / 2;

	private AHRS navx;
	private AnalogInput psiSensor;

	public DrivetrainSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		leftMaster = robotInfo.get(RobotInfo.LEFT_MOTOR_MASTER);
		leftSlaveOne = robotInfo.get(RobotInfo.LEFT_MOTOR_SLAVE1);
		leftSlaveTwo = robotInfo.get(RobotInfo.LEFT_MOTOR_SLAVE2);
		rightMaster = robotInfo.get(RobotInfo.RIGHT_MOTOR_MASTER);
		rightSlaveOne = robotInfo.get(RobotInfo.RIGHT_MOTOR_SLAVE1);
		rightSlaveTwo = robotInfo.get(RobotInfo.RIGHT_MOTOR_SLAVE2);
		driveShifters = robotInfo.get(RobotInfo.DRIVE_SHIFTERS);

		leftSlaveOne.follow(leftMaster);
		leftSlaveTwo.follow(leftMaster);
		rightSlaveOne.follow(rightMaster);
		rightSlaveTwo.follow(rightMaster);

		robotDrive = new DifferentialDrive(leftMaster.getMotor(), rightMaster.getMotor());

		leftVirtualSpeedController = new VirtualSpeedController();
		rightVirtualSpeedController = new VirtualSpeedController();
		virtualRobotDrive = new DifferentialDrive(leftVirtualSpeedController, rightVirtualSpeedController);

		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		leftMaster.setSelectedSensorPosition(0, 0, 0);
		rightMaster.setSelectedSensorPosition(0, 0, 0);

		navx = new AHRS(SPI.Port.kMXP);
		navx.reset();

		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);

		psiSensor = robotInfo.get(RobotInfo.PSI_SENSOR);
	}

	public void stopAllMotors() {
		blendedDrive(0, 0);
	}

	/**
	 * Returns the lerp of the arcade and curvature values for the left and right
	 * virtual speed controllers
	 *
	 * @return {left, right}
	 */
	public static double[] getBlendedMotorValues(double moveValue, double turnValue) {
		final double INPUT_THRESHOLD = 0.1;
		virtualRobotDrive.arcadeDrive(moveValue, turnValue, false);
		double leftArcadeValue = leftVirtualSpeedController.get();
		double rightArcadeValue = rightVirtualSpeedController.get();

		virtualRobotDrive.curvatureDrive(moveValue, turnValue, false);
		double leftCurvatureValue = leftVirtualSpeedController.get();
		double rightCurvatureValue = rightVirtualSpeedController.get();

		double lerpT = Math.abs(deadband(moveValue, RobotDriveBase.kDefaultDeadband)) / INPUT_THRESHOLD;
		lerpT = clamp(lerpT, 0, 1);
		double leftBlend = lerp(leftArcadeValue, leftCurvatureValue, lerpT);
		double rightBlend = lerp(rightArcadeValue, rightCurvatureValue, lerpT);

		double[] blends = { leftBlend, -rightBlend };
		return blends;
	}

	public void blendedDrive(double xSpeed, double zRotation) {
		double[] blendedValues = getBlendedMotorValues(-xSpeed, -zRotation);
		robotDrive.tankDrive(blendedValues[0], blendedValues[1]);
	}

	/**
	 * Clamps a double value based on a minimum and a maximum
	 *
	 * @param val the value to clamp
	 * @param min the minimum to clamp on
	 * @param max the maximum to clamp on
	 * @return min if val is less than min or max if val is greater than max
	 */
	public static double clamp(double val, double min, double max) {
		return val >= min && val <= max ? val : (val < min ? min : max);
	}

	/**
	 * Linearly interpolates between two points based on a t value
	 *
	 * @param a the point to interpolate from
	 * @param b the point to interpolate to
	 * @param t the value to interpolate on
	 * @return an output based on the formula lerp(a, b, t) = (1-t)a + tb
	 */
	public static double lerp(double a, double b, double t) {
		return (1 - t) * a + t * b;
	}

	// Copied from RobotDriveBase
	public static double deadband(double value, double deadband) {
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

	public static double undeadband(double value, double deadband) {
		if (value < 0) {
			double t = -value;
			return DrivetrainSubsystem.lerp(-deadband, -1, t);
		} else if (value > 0) {
			double t = value;
			return DrivetrainSubsystem.lerp(deadband, 1, t);
		} else {
			return 0;
		}
	}

	public double getLeftEncoderDistance() {
		return -leftMaster.getSelectedSensorPosition(0) * INCHES_PER_TICK;
	}

	public double getRightEncoderDistance() {
		return rightMaster.getSelectedSensorPosition(0) * INCHES_PER_TICK;
	}

	public double getAverageDistance() {
		return (getRightEncoderDistance() + getLeftEncoderDistance()) / 2;
	}

	/**
	 * Positive values = clockwise
	 */
	public double getGyroValueAdjusted() {
		double latency = smartDashboardInfo.getNumber(SmartDashboardInfo.GYRO_LATENCY);
		return navx.getAngle() + latency * navx.getRate() * navx.getActualUpdateRate();
	}

	public double getGyroValueUnadjusted() {
		return navx.getAngle();
	}

	public void resetAllSensors() {
		leftMaster.setSelectedSensorPosition(0, 0, 0);
		rightMaster.setSelectedSensorPosition(0, 0, 0);
		navx.reset();
	}

	double thetaNeeded;

	public void setThetaNeeded(double theta) {
		thetaNeeded = theta;
	}

	public double getThetaNeeded() {
		return thetaNeeded;
	}

	public void shift(boolean isHigh) {
		driveShifters.set(isHigh);
	}

	public double getPSIValue() {
		// Equation from http://www.revrobotics.com/content/docs/REV-11-1107-DS.pdf
		return 250 * psiSensor.getVoltage() / 5 - 25;
	}

	public void arcadeDrive(double moveValue, double turnValue) {
		robotDrive.arcadeDrive(moveValue, turnValue);
	}

	public void straightArcadeDrive(double moveValue) {
		double turnCorrection = smartDashboardInfo.getNumber(SmartDashboardInfo.TURN_CORRECTION);
		arcadeDrive(moveValue, -(getGyroValueUnadjusted() / turnCorrection));
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		robotDrive.tankDrive(-leftSpeed, -rightSpeed);
	}

	private double distance;

	public void setDistance(double d) {
		distance = d;
	}

	public double getDistance() {
		return distance;
	}
}
