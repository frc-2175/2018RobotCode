package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeSubsystem extends BaseSubsystem {
	private WPI_TalonSRX rollerBar;
	private WPI_TalonSRX leftIntakeWheel;
	private WPI_TalonSRX rightIntakeWheel;
	private RobotInfo robotInfo;
	private double leftSpeed;
	private double rightSpeed;
	public IntakeSubsystem () {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		rollerBar = robotInfo.get(RobotInfo.ROLLER_BAR_MOTOR);
		leftIntakeWheel = robotInfo.get(RobotInfo.INTAKE_LEFT_MOTOR);
		rightIntakeWheel = robotInfo.get(RobotInfo.INTAKE_RIGHT_MOTOR);
	}
	public void turnCube(double axisValue) {
		if (axisValue < 0) {
			leftSpeed = axisValue;
			rightSpeed = axisValue/2;
		} else {
			leftSpeed = axisValue/2;
			rightSpeed = axisValue;
		}
		leftIntakeWheel.set(leftSpeed);
		rightIntakeWheel.set(rightSpeed);
	}
	public void runRollerBarIn() {
		rollerBar.set(0.7);
	}
	public void runRollerBarOut() {
		rollerBar.set(-0.5);
	} 
	public void stopRollerBar() {
		rollerBar.set(0);
	}

}
