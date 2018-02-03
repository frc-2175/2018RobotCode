package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class SpinIntakeInCommand extends BaseCommand {
	private IntakeSubsystem intakeSubsystem;
	
	public SpinIntakeInCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}
	
	@Override 
	protected void initialize() {
		intakeSubsystem.runIntakeIn();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
