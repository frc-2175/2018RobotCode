package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class SpinIntakeOutSlowCommand extends BaseCommand {
	private final IntakeSubsystem intakeSubsystem;

	public SpinIntakeOutSlowCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}

	@Override
	protected void execute() {
		intakeSubsystem.runIntakeOut(true);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
