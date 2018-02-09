package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.SolenoidWrapper;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

public class IntakeSubsystem extends BaseSubsystem {
	private MotorWrapper rollerBar;
	private MotorWrapper leftIntakeWheel;
	private MotorWrapper rightIntakeWheel;
	private SolenoidWrapper actuationPiston1;
	private SolenoidWrapper actuationPiston2;
	private RobotInfo robotInfo;
	private SmartDashboardInfo smartDashboardInfo;
	private double leftSpeed;
	private double rightSpeed;
	private DryverStation driverStation;

	public IntakeSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		rollerBar = robotInfo.get(RobotInfo.ROLLER_BAR_MOTOR);
		leftIntakeWheel = robotInfo.get(RobotInfo.INTAKE_LEFT_MOTOR);
		rightIntakeWheel = robotInfo.get(RobotInfo.INTAKE_RIGHT_MOTOR);
		actuationPiston1 = robotInfo.get(RobotInfo.INTAKE_PISTON1);
		actuationPiston2 = robotInfo.get(RobotInfo.INTAKE_PISTON2);
		driverStation = ServiceLocator.get(DryverStation.class);
	}

	public void turnCube(double axisValue) {
		if (axisValue < 0) {
			leftSpeed = axisValue;
			rightSpeed = axisValue / 2;
		} else {
			leftSpeed = axisValue / 2;
			rightSpeed = axisValue;
		}
		leftIntakeWheel.set(leftSpeed);
		rightIntakeWheel.set(rightSpeed);
	}

	public void runRollerBarIn() {
		rollerBar.set(smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_ROLLER_IN_SPEED));
	}

	public void runRollerBarOut() {
		rollerBar.set(smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_ROLLER_OUT_SPEED));
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

	public void runIntakeIn() {
		runRollerBarIn();
		leftIntakeWheel.set(-smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_INTAKE_IN_SPEED));
		rightIntakeWheel.set(smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_INTAKE_IN_SPEED));
	}

	public void runIntakeOut() {
		runRollerBarOut();
		leftIntakeWheel.set(-smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_INTAKE_OUT_SPEED));
		rightIntakeWheel.set(smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_INTAKE_OUT_SPEED));
	}

	public void stopAllMotors() {
		stopRollerBar();
		leftIntakeWheel.set(0);
		rightIntakeWheel.set(0);
	}
}
