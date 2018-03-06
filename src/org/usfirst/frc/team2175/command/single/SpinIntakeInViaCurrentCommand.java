package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

// TODO: What is this, and how is it different from the SpinIntakeInCommand?
public class SpinIntakeInViaCurrentCommand extends BaseCommand {
	private final IntakeSubsystem intakeSubsystem;

	public SpinIntakeInViaCurrentCommand() {
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
