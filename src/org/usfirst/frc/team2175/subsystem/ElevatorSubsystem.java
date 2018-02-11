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
	/**
	 * The ratio of inches that the second elevator stage travels to the number of
	 * encoder occurred
	 */
	public static final double INCHES_PER_TICK = Math.PI * SPROCKET_DIAMETER_INCHES / TICKS_PER_REVOLUTION * 2;
	public static final double MAX_ELEVATOR_TRAVEL = 78.0357;
	public static final double MIN_ELEVATOR_HEIGHT_SLOW_ACCELERATION = 45;

	public ElevatorSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		elevatorMotor = robotInfo.get(RobotInfo.ELEVATOR_MOTOR);
		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		elevatorMotor.setSelectedSensorPosition(0, 0, 0);
	}

	public void runElevatorUp() {
		if (getInchesTraveled() < MAX_ELEVATOR_TRAVEL) {
			elevatorMotor.set(smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_ELEVATOR_UP_SPEED));
		} else {
			stopElevator();
		}
	}

	public void runElevatorDown() {
		elevatorMotor.set(smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_ELEVATOR_DOWN_SPEED));
	}

	public void runElevator(double axisValue) {
		if (axisValue < 0) {
			axisValue *= smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_MAX_DOWN_SPEED);
		} else {
			if (getInchesTraveled() < MAX_ELEVATOR_TRAVEL) {
				axisValue *= smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_MAX_UP_SPEED);
			} else {
				axisValue = 0;
			}
		}
		elevatorMotor.set(axisValue);
	}

	public void stopElevator() {
		elevatorMotor.set(0);
	}

	/**
	 * @return the inches traveled based on the {@link #INCHES_PER_TICK} value
	 */
	private double getInchesTraveled() {
		return INCHES_PER_TICK * elevatorMotor.getSelectedSensorPosition(0);
	}

	/**
	 * @return a usable t value in
	 *         <li>{@link org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem#lerp(double, double, double)
	 *         lerp(a, b, t)}</li>
	 */
	public double getElevatorTValue() {
		double t = DrivetrainSubsystem.clamp(getInchesTraveled(), MIN_ELEVATOR_HEIGHT_SLOW_ACCELERATION,
			MAX_ELEVATOR_TRAVEL);
		return (t - MIN_ELEVATOR_HEIGHT_SLOW_ACCELERATION)
			/ (MAX_ELEVATOR_TRAVEL - MIN_ELEVATOR_HEIGHT_SLOW_ACCELERATION);
	}

	/**
	 * @return whether the inches traveled is above the minimum elevator height for
	 *         slow acceleration
	 */
	public boolean getShouldAccelerateSlowly() {
		return getInchesTraveled() > MIN_ELEVATOR_HEIGHT_SLOW_ACCELERATION;
	}
}
