package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.SolenoidWrapper;
import org.usfirst.frc.team2175.info.RobotInfo;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;

public class IntakeSubsystem extends BaseSubsystem {
	private WPI_TalonSRX rollerBar;
	private WPI_TalonSRX leftIntakeWheel;
	private WPI_TalonSRX rightIntakeWheel;
	private SolenoidWrapper actuationPiston1;
	private SolenoidWrapper actuationPiston2;
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
	public void moveUp() {
		actuationPiston1.set(false);
		actuationPiston2.set(false);
	}
	public void moveHalf() {
		actuationPiston1.set(false);
		actuationPiston2.set(true);

	}
	public void moveDown() {
		actuationPiston1.set(true);
		actuationPiston2.set(true);
	}
}
