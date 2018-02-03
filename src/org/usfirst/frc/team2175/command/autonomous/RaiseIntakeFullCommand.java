package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.BaseCommand;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class RaiseIntakeFullCommand extends BaseCommand {
	private IntakeSubsystem intakeSubsystem;
		
	public RaiseIntakeFullCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}
		
	@Override 
	protected void initialize() {
		intakeSubsystem.moveUp();
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