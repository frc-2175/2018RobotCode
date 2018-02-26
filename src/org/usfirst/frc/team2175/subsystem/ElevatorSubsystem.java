package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class ElevatorSubsystem extends BaseSubsystem {
	private final MotorWrapper masterMotor;
	private final MotorWrapper slaveMotor;
	private final RobotInfo robotInfo;
	private final SmartDashboardInfo smartDashboardInfo;
	public static final double SPROCKET_DIAMETER_INCHES = 2.688;
	public static final double TICKS_PER_REVOLUTION = 1024;
	public static final double INCHES_PER_TICK = Math.PI * SPROCKET_DIAMETER_INCHES / TICKS_PER_REVOLUTION * 2;
	public static final double MAX_ELEVATOR_TRAVEL = 78.0357;

	public ElevatorSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		masterMotor = robotInfo.get(RobotInfo.ELEVATOR_MASTER_MOTOR);
		slaveMotor = robotInfo.get(RobotInfo.ELEVATOR_SLAVE_MOTOR);
		slaveMotor.follow(masterMotor);
		masterMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		masterMotor.setSelectedSensorPosition(0, 0, 0);
	}

	public void runElevator(double axisValue) {
		if (axisValue < 0) {
			axisValue *= smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_MAX_DOWN_SPEED);
		} else {
			axisValue *= smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_MAX_UP_SPEED);
		}
		masterMotor.set(axisValue);
	}

	public void stopElevator() {
		masterMotor.set(0);
	}

	public double getInchesTraveled() {
		return -INCHES_PER_TICK * masterMotor.getSelectedSensorPosition(0) / 35;
	}
}
