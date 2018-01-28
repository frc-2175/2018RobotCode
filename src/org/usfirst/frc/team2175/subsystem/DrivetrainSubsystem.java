package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.VirtualSpeedController;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.info.RobotInfo;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DrivetrainSubsystem extends BaseSubsystem{
	private RobotInfo robotInfo;
	private WPI_TalonSRX leftMaster;
	private WPI_TalonSRX leftSlaveOne;
	private WPI_TalonSRX leftSlaveTwo;
	private WPI_TalonSRX rightMaster;
	private WPI_TalonSRX rightSlaveOne;
	private WPI_TalonSRX rightSlaveTwo;
	private DifferentialDrive robotDrive;
	private DryverStation driverStation;

	public DrivetrainSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		leftMaster = robotInfo.get(RobotInfo.LEFT_MOTOR_MASTER);
		leftSlaveOne = robotInfo.get(RobotInfo.LEFT_MOTOR_SLAVE1);
		leftSlaveTwo = robotInfo.get(RobotInfo.LEFT_MOTOR_SLAVE2);
		rightMaster = robotInfo.get(RobotInfo.RIGHT_MOTOR_MASTER);
		rightSlaveOne = robotInfo.get(RobotInfo.RIGHT_MOTOR_SLAVE1);
		rightSlaveTwo = robotInfo.get(RobotInfo.RIGHT_MOTOR_SLAVE2);
		leftSlaveOne.follow(leftMaster);
		leftSlaveTwo.follow(leftMaster);
		rightSlaveOne.follow(rightMaster);
		rightSlaveTwo.follow(rightMaster);
		robotDrive = new DifferentialDrive(leftMaster, rightMaster);
		driverStation = ServiceLocator.get(DryverStation.class);
	}

	public void robotDrive(double xSpeed, double zRotation) {
		robotDrive.arcadeDrive(xSpeed, zRotation, false);
	}
	
	public void stopAllMotors() {
		robotDrive(0, 0);
	}
	
	/**
	 * Returns the lerp of the arcade and curvature
	 * values for the left and right virtual speed
	 * controllers
	 * @return {left, right} 
	 */
	public double[] getBlendedMotorValues() {
		final double INPUT_THRESHOLD = 0.1;
		VirtualSpeedController leftSpeedController = new VirtualSpeedController();
		VirtualSpeedController rightSpeedController = new VirtualSpeedController();
		
		robotDrive.arcadeDrive(driverStation.getMoveValue(),  driverStation.getTurnValue());
		double leftArcadeValue = leftSpeedController.get();
		double rightArcadeValue = rightSpeedController.get();
		
		robotDrive.curvatureDrive(driverStation.getMoveValue(),  driverStation.getTurnValue(), false);
		double leftCurvatureValue = leftSpeedController.get();
		double rightCurvatureValue = rightSpeedController.get();
		
		double lerpT = Math.abs(driverStation.getMoveValue()) / INPUT_THRESHOLD;
		lerpT = clamp(lerpT, 0, INPUT_THRESHOLD);
		double leftBlend = lerp(leftArcadeValue, leftCurvatureValue, lerpT);
		double rightBlend = lerp(rightArcadeValue, rightCurvatureValue, lerpT);
		
		double[] blends = {leftBlend, rightBlend};
		return blends;
	}
	
	/**
	 * Clamps a double value based on a minimum and a maximum
	 * @param val the value to clamp
	 * @param min the minimum to clamp on
	 * @param max the maximum to clamp on
	 * @return min if val is less than min or max if val is greater than max
	 */
	public double clamp(double val, double min, double max) {
		return val >= min && val <= max ? val : (val < min ? min : max);
	}
	
	/**
	 * Linearly interpolates between two points based on a t value
	 * @param a the point to interpolate from
	 * @param b the point to interpolate to
	 * @param t the value to interpolate on
	 * @return an output based on the formula lerp(a, b, t) = 
	 * (1-t)a + tb
	 */
	public double lerp(double a, double b, double t) {
		return (1 - t) * a + t * b;
	}
}
