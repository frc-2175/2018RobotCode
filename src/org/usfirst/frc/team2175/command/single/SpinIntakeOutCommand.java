package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class SpinIntakeOutCommand extends BaseCommand {
	private final IntakeSubsystem intakeSubsystem;

	public SpinIntakeOutCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}

	@Override
	protected void execute() {
		intakeSubsystem.runIntakeOut(false);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void onEnd() {
		intakeSubsystem.clearValues();
	}
}
