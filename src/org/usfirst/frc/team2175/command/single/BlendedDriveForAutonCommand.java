package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class BlendedDriveForAutonCommand extends BaseCommand {
	private final DrivetrainSubsystem drivetrainSubsystem;
	private final double move;
	private final double turn;

	public BlendedDriveForAutonCommand(double move, double turn) {
		this.move = move;
		this.turn = turn;
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);

		requires(drivetrainSubsystem);
	}

	public BlendedDriveForAutonCommand(double move) {
		this(move, 0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void execute() {
		drivetrainSubsystem.blendedDrive(move, turn);
	}

	@Override
	protected void onEnd() {
		drivetrainSubsystem.stopAllMotors();
	}
}
