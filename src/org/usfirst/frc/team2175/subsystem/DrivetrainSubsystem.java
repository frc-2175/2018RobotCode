package org.usfirst.frc.team2175.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DrivetrainSubsystem extends Base_S{
	private WPI_TalonSRX leftMaster;
	private WPI_TalonSRX leftSlaveOne;
	private WPI_TalonSRX leftSlaveTwo;
	private WPI_TalonSRX rightMaster;
	private WPI_TalonSRX rightSlaveOne;
	private WPI_TalonSRX rightSlaveTwo;
	private DifferentialDrive robotDrive;

	public DrivetrainSubsystem() {
		leftMaster = new WPI_TalonSRX(0);
		leftSlaveOne = new WPI_TalonSRX(1);
		leftSlaveTwo = new WPI_TalonSRX(2);
		rightMaster = new WPI_TalonSRX(3);
		rightSlaveOne = new WPI_TalonSRX(4);
		rightSlaveTwo = new WPI_TalonSRX(5);
		leftSlaveOne.follow(leftMaster);
		leftSlaveTwo.follow(leftMaster);
		rightSlaveOne.follow(rightMaster);
		rightSlaveTwo.follow(rightMaster);
		robotDrive = new DifferentialDrive(leftMaster, rightMaster);

	}

	public void robotDrive(double xSpeed, double zRotation) {
		robotDrive.arcadeDrive(xSpeed, zRotation, false);
	}
}
