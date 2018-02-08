package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class ArcadeDriveForAutonCommand extends BaseCommand {
	private DrivetrainSubsystem drivetrainsubsystem;
	private final double move;
	private final double turn;

	public ArcadeDriveForAutonCommand(double move, double turn) {
		this.move = move;
		this.turn = turn;
		drivetrainsubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
	}

	public ArcadeDriveForAutonCommand(double move) {
		this(move, 0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void initialize() {
		drivetrainsubsystem.robotDrive(move, turn);
	}

	@Override
	protected void end() {
		drivetrainsubsystem.stopAllMotors();
	}
}
