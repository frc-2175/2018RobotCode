package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

// TODO: Do we need this or SpinIntakeRightCommand?
public class SpinIntakeLeftCommand extends BaseCommand {
	private final IntakeSubsystem intakeSubsystem;

	public SpinIntakeLeftCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}

	@Override
	protected void execute() {
		intakeSubsystem.turnCube(false);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		intakeSubsystem.clearTurnSpeed();
	}
}
