package org.usfirst.frc.team2175.command;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.ArcadeDriveDefaultCommand;
import org.usfirst.frc.team2175.command.single.ElevatorDefaultCommand;
import org.usfirst.frc.team2175.command.single.IntakeDefaultCommand;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class DefaultCommandFactory {
	private final DrivetrainSubsystem drivetrainSubsystem;
	private final IntakeSubsystem intakeSubsystem;
	private final ElevatorSubsystem elevatorSubsystem;

	public DefaultCommandFactory() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		constructDefaultCommands();
	}

	private void constructDefaultCommands() {
		drivetrainSubsystem.setDefaultCommand(new ArcadeDriveDefaultCommand());
		elevatorSubsystem.setDefaultCommand(new ElevatorDefaultCommand());
		intakeSubsystem.setDefaultCommand(new IntakeDefaultCommand());
	}

}
