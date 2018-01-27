package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ElevatorSubsystem extends BaseSubsystem {
	private WPI_TalonSRX elevatorOne;
	private WPI_TalonSRX elevatorTwo;
	private RobotInfo robotInfo;

	public ElevatorSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		elevatorOne = robotInfo.get(RobotInfo.ELEVATOR_MOTOR);
		elevatorTwo = robotInfo.get(RobotInfo.ELEVATOR_MOTOR2);
		
		elevatorTwo.follow(elevatorOne);
	}

	public void runElevatorUp() {
		elevatorOne.set(.5);
	}

	public void runElevatorDown() {
		elevatorOne.set(-.5);
	}
}
