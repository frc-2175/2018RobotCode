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
		super();
		this.speed = speed;
		this.distance = distance;
		this.accelerate = accelerate;
		this.decelerate = decelerate;
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);

		requires(elevatorSubsystem);
	}

	@Override
	protected void initialize() {
		super.initialize();
		accelerationRate = smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_ACCELERATION_RATE);
		elevatorSubsystem.resetAllSensors();
	}

	@Override
	protected void execute() {
		double moveValue;
		if (decelerate) {
			moveValue = clamp(decelerate() * speed, 0.2, speed);
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
	protected void end() {
		super.end();
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
