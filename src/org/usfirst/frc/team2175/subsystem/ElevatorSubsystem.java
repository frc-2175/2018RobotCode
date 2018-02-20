package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class ElevatorSubsystem extends BaseSubsystem {
	private MotorWrapper elevatorMotor;
	private RobotInfo robotInfo;
	private SmartDashboardInfo smartDashboardInfo;
	public static final double SPROCKET_DIAMETER_INCHES = 2.688;
	public static final double TICKS_PER_REVOLUTION = 1024;
	public static final double INCHES_PER_TICK = Math.PI * SPROCKET_DIAMETER_INCHES / TICKS_PER_REVOLUTION * 2;
	public static final double MAX_ELEVATOR_TRAVEL = 78.0357;

	public ElevatorSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		elevatorMotor = robotInfo.get(RobotInfo.ELEVATOR_MOTOR);
		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		elevatorMotor.setSelectedSensorPosition(0, 0, 0);
	}

	public void runElevator(double axisValue) {
		if (axisValue < 0) {
			axisValue *= smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_MAX_DOWN_SPEED);
		} else {
			axisValue *= smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_MAX_UP_SPEED);
		}
		elevatorMotor.set(axisValue);
	}

	public void stopElevator() {
		elevatorMotor.set(0);
	}

	public double getInchesTraveled() {
		return INCHES_PER_TICK * elevatorMotor.getSelectedSensorPosition(0);
	}
}
