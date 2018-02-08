package org.usfirst.frc.team2175.command;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.ArcadeDriveDefaultCommand;
import org.usfirst.frc.team2175.command.single.ElevatorDefaultCommand;
import org.usfirst.frc.team2175.command.single.IntakeDefaultCommand;
import org.usfirst.frc.team2175.command.single.SpinClimberDefaultCommand;
import org.usfirst.frc.team2175.subsystem.ClimberSubsystem;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class DefaultCommandFactory {
	private DrivetrainSubsystem drivetrainSubsystem;
	private ClimberSubsystem climberSubsystem;
	private IntakeSubsystem intakeSubsystem;
	private ElevatorSubsystem elevatorSubsystem;

	public DefaultCommandFactory() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		climberSubsystem = ServiceLocator.get(ClimberSubsystem.class);
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		constructDefaultCommands();
	}

	private void constructDefaultCommands() {
		drivetrainSubsystem.setDefaultCommand(new ArcadeDriveDefaultCommand());
		climberSubsystem.setDefaultCommand(new SpinClimberDefaultCommand());
		intakeSubsystem.setDefaultCommand(new IntakeDefaultCommand());
		elevatorSubsystem.setDefaultCommand(new ElevatorDefaultCommand());
	}

}
