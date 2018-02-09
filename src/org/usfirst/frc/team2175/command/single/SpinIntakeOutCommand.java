package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class SpinIntakeOutCommand extends BaseCommand {
	private IntakeSubsystem intakeSubsystem;

	public SpinIntakeOutCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
		requires(intakeSubsystem);
	}

	@Override
	protected void execute() {
		intakeSubsystem.runIntakeOut();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		intakeSubsystem.stopAllMotors();
	}
}
