package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.SolenoidWrapper;
import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

// Positive values are running a motor in, negative values are running a motor out
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
	private double turnSpeed;

	public IntakeSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		rollerBar = robotInfo.get(RobotInfo.ROLLER_BAR_MOTOR);
		leftIntakeWheel = robotInfo.get(RobotInfo.INTAKE_LEFT_MOTOR);
		leftIntakeWheel.setInverted(true);
		rightIntakeWheel = robotInfo.get(RobotInfo.INTAKE_RIGHT_MOTOR);
		actuationPiston1 = robotInfo.get(RobotInfo.INTAKE_PISTON1);
		actuationPiston2 = robotInfo.get(RobotInfo.INTAKE_PISTON2);

		clearValues();
	}

	@Override
	public void periodic() {
		if (turnSpeed < 0) {
			leftIntakeWheel.set(turnSpeed * -1);
			rightIntakeWheel.set(turnSpeed * 0.5);
		} else if (turnSpeed > 0) {
			leftIntakeWheel.set(turnSpeed * 0.5);
			rightIntakeWheel.set(turnSpeed * -1);
		} else {
			leftIntakeWheel.set(leftSpeed);
			rightIntakeWheel.set(rightSpeed);
		}
		rollerBar.set(barSpeed);
		clearValues();
	}

	public void turnCube(boolean isLeft) {
		if (isLeft) {
			turnSpeed = smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_TURN_CUBE_SPEED);
		} else {
			turnSpeed = -smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_TURN_CUBE_SPEED);
		}
	}

	public void turnCube(double axisValue) {
		turnSpeed = axisValue * smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_TURN_CUBE_SPEED);
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
		leftSpeed = smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_IN_SPEED);
		rightSpeed = smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_IN_SPEED);
		barSpeed = smartDashboardInfo.getNumber(SmartDashboardInfo.ROLLER_IN_SPEED);
	}

	public void runIntakeOut(boolean slow) {
		leftSpeed = slow ? smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_SLOW_SPEED)
			: smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_OUT_SPEED);
		rightSpeed = slow ? smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_SLOW_SPEED)
			: smartDashboardInfo.getNumber(SmartDashboardInfo.INTAKE_OUT_SPEED);
		barSpeed = smartDashboardInfo.getNumber(SmartDashboardInfo.ROLLER_OUT_SPEED);
	}

	public void clearValues() {
		leftSpeed = 0;
		rightSpeed = 0;
		barSpeed = 0;
		turnSpeed = 0;
	}

	public void clearTurnSpeed() {
		turnSpeed = 0;
	}
}
