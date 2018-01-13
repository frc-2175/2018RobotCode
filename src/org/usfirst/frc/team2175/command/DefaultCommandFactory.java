package org.usfirst.frc.team2175.command;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class DefaultCommandFactory {
	private DrivetrainSubsystem drivetrainSubsystem;
	
	public DefaultCommandFactory() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		constructDefaultCommands();
	}
	
	private void constructDefaultCommands() {

	}
}
