package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;

public class ElevatorAutonCommand extends BaseCommand {
	private double speed, distance, accelerationRate;
	private boolean accelerate, decelerate;
	public static final double PROPORTIONAL = 1.0 / 30.0;
	private final ElevatorSubsystem elevatorSubsystem;
	private final SmartDashboardInfo smartDashboardInfo;

	public ElevatorAutonCommand(double speed, double distance, boolean accelerate, boolean decelerate) {
		this.speed = speed;
		this.distance = distance;
		this.accelerate = accelerate;
		this.decelerate = decelerate;
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);

		requires(elevatorSubsystem);
	}

	@Override
	protected void init() {
		accelerationRate = smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_ACCELERATION_RATE);
		elevatorSubsystem.resetAllSensors();
	}

	@Override
	protected void execute() {
		double moveValue;
		if (decelerate) {
			double min = (speed < 0) ? speed : .2;
			double max = (speed < 0) ? -.9 : speed;
			moveValue = clamp(decelerate() * speed, min, max);
		} else {
			moveValue = speed;
		}
		if (accelerate) {
			moveValue *= accelerate();
		}
		elevatorSubsystem.runElevator(moveValue);
	}

	@Override
	protected boolean isFinished() {
		return timeSinceInitialized() > 0.2 && Math.abs(elevatorSubsystem.getInchesTraveled()) >= Math.abs(distance);
	}

	@Override
	protected void onEnd() {
		if (decelerate) {
			elevatorSubsystem.runElevator(0);
		}
	}

	private double accelerate() {
		return clamp(timeSinceInitialized() * accelerationRate, 0, speed);
	}

	private double decelerate() {
		double error = distance - elevatorSubsystem.getInchesTraveled();
		return clamp(PROPORTIONAL * error, 0, 1);
	}
}
