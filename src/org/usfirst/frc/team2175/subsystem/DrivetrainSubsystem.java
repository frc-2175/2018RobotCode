package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.ServiceLocator;
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

	}

	public void robotDrive(double xSpeed, double zRotation) {
		robotDrive.arcadeDrive(xSpeed, zRotation, false);
	}
	
	public void stopAllMotors() {
		robotDrive(0, 0);
	}
}
