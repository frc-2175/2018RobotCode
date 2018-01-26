package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ClimberSubsystem extends BaseSubsystem {
	private WPI_TalonSRX leftMotor;
	private WPI_TalonSRX rightMotor;
	private RobotInfo robotInfo;
	public ClimberSubsystem () {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		leftMotor = robotInfo.get(RobotInfo.CLIMBER_LEFT_MOTOR);
		rightMotor = robotInfo.get(RobotInfo.CLIMBER_RIGHT_MOTOR);
	}
	public void turnClimberOn () {
		leftMotor.set(0.7);
		rightMotor.set(0.7);
	}
	public void turnClimberOff () {
		leftMotor.set(0);
		rightMotor.set(0);
	}
	
}
