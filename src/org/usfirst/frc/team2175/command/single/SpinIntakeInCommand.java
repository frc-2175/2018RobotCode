package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class SpinIntakeInCommand extends BaseCommand {
	private final IntakeSubsystem intakeSubsystem;

	public SpinIntakeInCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}

	@Override
	protected void execute() {
		intakeSubsystem.runIntakeIn();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		intakeSubsystem.clearValues();
	}
}
