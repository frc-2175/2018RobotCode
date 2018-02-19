package org.usfirst.frc.team2175.factory;

import org.usfirst.frc.team2175.subsystem.ClimberSubsystem;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class SubsystemsFactory {
	public static void makeAllSubsystems() {
		new DrivetrainSubsystem();
		new IntakeSubsystem();
		new ClimberSubsystem();
		new ElevatorSubsystem();
	}
}
