package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.BaseCommand;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class LowerIntakeCommand extends BaseCommand {
	private IntakeSubsystem intakeSubsystem;
		
	public LowerIntakeCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}
		
	@Override 
	protected void initialize() {
		intakeSubsystem.moveDown();
	}
		
	@Override
	protected boolean isFinished() {
		return false;
	}
}