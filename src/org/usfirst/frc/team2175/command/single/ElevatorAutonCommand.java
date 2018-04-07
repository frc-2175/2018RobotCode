package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;

public class ElevatorAutonCommand extends BaseCommand {
	private double speed, distance, accelerationRate;
	private boolean accelerate, decelerate;
	public static final double PROPORTIONAL = 1.0 / 12.0;
	private final ElevatorSubsystem elevatorSubsystem;
	private final SmartDashboardInfo smartDashboardInfo;
	private double delay;
	private double startTime;

	public ElevatorAutonCommand(double speed, double distance, boolean accelerate, boolean decelerate) {
		this(speed, distance, accelerate, decelerate, 0);
	}

	public ElevatorAutonCommand(double speed, double distance, boolean accelerate, boolean decelerate, double delay) {
		this.speed = speed;
		this.distance = distance;
		this.accelerate = accelerate;
		this.delay = delay;
		this.decelerate = decelerate;
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);

		requires(elevatorSubsystem);
	}

	@Override
	protected void init() {
		accelerationRate = smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_ACCELERATION_RATE);
		startTime = System.currentTimeMillis();
		// elevatorSubsystem.resetAllSensors();
	}

	@Override
	protected void execute() {
		double moveValue;
		if (decelerate) {
			double min = (speed < 0) ? speed : .5;
			double max = (speed < 0) ? -.9 : speed;
			moveValue = clamp(decelerate() * speed, min, max);
		} else {
			moveValue = speed;
		}
		if (accelerate) {
			moveValue *= accelerate();
		}
		if (System.currentTimeMillis() - startTime > delay * 1000) {
			elevatorSubsystem.runElevator(moveValue);
			System.out.println("delay is finished");
		}
	}

	@Override
	protected boolean isFinished() {
		if (speed > 0) {
			return timeSinceInitialized() > 0.2 && elevatorSubsystem.getInchesTraveled() >= distance;
		} else if (speed < 0) {
			return timeSinceInitialized() > 0.2 && distance >= elevatorSubsystem.getInchesTraveled();
		} else {
			return true;
		}
	}

	@Override
	protected void onEnd() {
		elevatorSubsystem.runElevator(0);
	}

	private double accelerate() {
		return clamp(timeSinceInitialized() * accelerationRate, 0, speed);
	}

	private double decelerate() {
		double error = distance - elevatorSubsystem.getInchesTraveled();
		return clamp(PROPORTIONAL * error, 0, 1);
	}
}
