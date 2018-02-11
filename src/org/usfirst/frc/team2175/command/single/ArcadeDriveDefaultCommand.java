package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;

public class ArcadeDriveDefaultCommand extends BaseCommand {
	private DrivetrainSubsystem drivetrainSubsystem;
	private DryverStation driverStation;
	private ElevatorSubsystem elevatorSubsystem;
	private double possibleChangePerSecond;
	private final double MIN_CHANGE_PER_SECOND = 1;
	private final double MAX_CHANGE_PER_SECOND = 2;
	private final double MAX_TOP_SPEED = 1;
	private final double MIN_TOP_SPEED = 0.7;
	private double topSpeed = 1;
	private double lastTime;
	private double currentMoveValue;

	public ArcadeDriveDefaultCommand() {
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		driverStation = ServiceLocator.get(DryverStation.class);
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		requires(drivetrainSubsystem);
	}

	@Override
	protected void initialize() {
		lastTime = System.currentTimeMillis();
		currentMoveValue = 0;
	}

	@Override
	protected void execute() {
		// Set the possible change per second to the lerped value of the max change per
		// second
		// and the min change per second based on the T value from the ElevatorSubsystem
		possibleChangePerSecond = DrivetrainSubsystem.lerp(MAX_CHANGE_PER_SECOND, MIN_CHANGE_PER_SECOND,
			elevatorSubsystem.getElevatorTValue());

		topSpeed = DrivetrainSubsystem.lerp(MAX_TOP_SPEED, MIN_TOP_SPEED, elevatorSubsystem.getElevatorTValue());

		double possibleChangeFromTime = (System.currentTimeMillis() - lastTime) / 1000 * possibleChangePerSecond;

		double targetSpeed = DrivetrainSubsystem.clamp(driverStation.getMoveValue(), -topSpeed, topSpeed);

		double valueChange = targetSpeed - currentMoveValue;
		double wantedMoveValue;
		if (Math.abs(valueChange) > possibleChangeFromTime) {
			wantedMoveValue = currentMoveValue + Math.signum(valueChange) * possibleChangeFromTime;
		} else {
			wantedMoveValue = targetSpeed;
		}

		// If acceleration should be slow, then use the wantedMoveValue instead of the
		// DriverStation moveValue
		if (elevatorSubsystem.getShouldAccelerateSlowly()) {
			drivetrainSubsystem.robotDrive(wantedMoveValue, driverStation.getTurnValue());
		} else {
			drivetrainSubsystem.robotDrive(driverStation.getMoveValue(), driverStation.getTurnValue());
		}

		currentMoveValue = wantedMoveValue;
		lastTime = System.currentTimeMillis();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrainSubsystem.stopAllMotors();
	}
}
