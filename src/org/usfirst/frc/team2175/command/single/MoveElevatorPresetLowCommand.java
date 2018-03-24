package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;

public class MoveElevatorPresetLowCommand extends BaseCommand {
	private final ElevatorSubsystem elevatorSubsystem;
	private final double height = 38;
	private double speed = 0.8;

	public MoveElevatorPresetLowCommand() {
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		requires(elevatorSubsystem);
	}

	@Override
	protected void init() {
		speed *= elevatorSubsystem.getInchesTraveled() <= height ? 1 : -1;
		elevatorSubsystem.runElevator(speed);
	}

	@Override
	protected boolean isFinished() {
		return speed > 0 ? elevatorSubsystem.getInchesTraveled() >= height
			: elevatorSubsystem.getInchesTraveled() <= height;
	}

	@Override
	protected void onEnd() {
		elevatorSubsystem.stopElevator();
	}
}
