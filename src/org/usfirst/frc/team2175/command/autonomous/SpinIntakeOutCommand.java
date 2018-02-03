package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class SpinIntakeOutCommand {
	private IntakeSubsystem intakeSubsystem;
	
	public SpinIntakeOutCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}
	protected void execute() {
		intakeSubsystem.runIntakeOut();
	}
	protected boolean isFinished() {
		return false;
	}
}