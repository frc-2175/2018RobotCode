package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.SolenoidWrapper;
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
	private double barSpeed;
	private double leftTurn;
	private double rightTurn;

	public IntakeSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		rollerBar = robotInfo.get(RobotInfo.ROLLER_BAR_MOTOR);
		leftIntakeWheel = robotInfo.get(RobotInfo.INTAKE_LEFT_MOTOR);
		rightIntakeWheel = robotInfo.get(RobotInfo.INTAKE_RIGHT_MOTOR);
		actuationPiston1 = robotInfo.get(RobotInfo.INTAKE_PISTON1);
		actuationPiston2 = robotInfo.get(RobotInfo.INTAKE_PISTON2);

		clearValues();
	}

	@Override
	public void periodic() {
		runSystems();
		clearValues();
	}

	public void turnCube(boolean isLeft) {
		if (isLeft) {
			leftTurn = -0.5;
		} else {
			rightTurn = 0.5;
		}
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
		leftSpeed = -smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_INTAKE_IN_SPEED);
		rightSpeed = smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_INTAKE_IN_SPEED);
		barSpeed = smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_ROLLER_IN_SPEED);
	}

	public void runIntakeOut() {
		leftSpeed = -smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_INTAKE_OUT_SPEED);
		rightSpeed = smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_INTAKE_OUT_SPEED);
		barSpeed = smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_ROLLER_OUT_SPEED);
	}

	public void runSystems() {
		leftIntakeWheel.set(leftSpeed + leftTurn + rightTurn);
		rightIntakeWheel.set(rightSpeed + leftTurn + rightTurn);
		rollerBar.set(barSpeed);
	}

	public void clearValues() {
		leftSpeed = 0;
		rightSpeed = 0;
		barSpeed = 0;
		leftTurn = 0;
		rightTurn = 0;
	}
}
