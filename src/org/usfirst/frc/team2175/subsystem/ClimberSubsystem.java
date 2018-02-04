package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ClimberSubsystem extends BaseSubsystem {
	private WPI_TalonSRX masterMotor;
	private WPI_TalonSRX slaveMotor;
	private RobotInfo robotInfo;
	public ClimberSubsystem () {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		masterMotor = robotInfo.get(RobotInfo.CLIMBER_MASTER);
		slaveMotor = robotInfo.get(RobotInfo.CLIMBER_SLAVE);
		
		slaveMotor.follow(masterMotor);
	}
	public void turnClimberOn () {
		masterMotor.set(0.7);
	}
	public void turnClimberOff () {
		masterMotor.set(0);
	}
	public void spinClimber(double axisValue) {
		masterMotor.set(axisValue);
	}
	
}
