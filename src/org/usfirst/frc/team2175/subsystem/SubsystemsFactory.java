package org.usfirst.frc.team2175.subsystem;

public class SubsystemsFactory {
	public static void makeAllSubsystems() {
		new DrivetrainSubsystem();
		new IntakeSubsystem();
	}
}
